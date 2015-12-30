package com.test.jsondemo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.test.jsondemo.R;
import com.test.jsondemo.help.MyDatabaseHelper;

/**
 * Created by SoleilLun on 2015/12/25.
 */
public class SQLiteActivity extends Activity implements View.OnClickListener {
    private final static String SWORD = "SWORD";
    //声明五个控件对象
    Button createDatabase = null;
    Button updateDatabase = null;
    Button insert = null;
    Button update = null;
    Button query = null;
    Button delete = null;
    Button replace = null;
    private MyDatabaseHelper databaseHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlit_test_activity);
        databaseHelper = new MyDatabaseHelper(this,"BookStore.db", null, 2);
        findViews();
    }

    private void findViews() {
        //根据控件ID得到控件
        createDatabase = (Button) this.findViewById(R.id.createDatabase);
        updateDatabase = (Button) this.findViewById(R.id.updateDatabase);
        insert = (Button) this.findViewById(R.id.insert);
        update = (Button) this.findViewById(R.id.update);
        query = (Button) this.findViewById(R.id.query);
        delete = (Button) this.findViewById(R.id.delete);
        replace = (Button) this.findViewById(R.id.replace_data);
        //添加监听器
        createDatabase.setOnClickListener(this);
        updateDatabase.setOnClickListener(this);
        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        delete.setOnClickListener(this);
        replace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //判断所触发的被监听控件，并执行命令
        switch (v.getId()) {
            //创建数据库
            case R.id.createDatabase:
                //创建一个DatabaseHelper对象
                MyDatabaseHelper dbHelper1 = new MyDatabaseHelper(SQLiteActivity.this, "BookStore", null, 2);
                //取得一个只读的数据库对象
                SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
                break;
            //更新数据库
            case R.id.updateDatabase:
                MyDatabaseHelper dbHelper2 = new MyDatabaseHelper(SQLiteActivity.this, "BookStore", 2);
                SQLiteDatabase db2 = dbHelper2.getReadableDatabase();
                break;
            //插入数据
            case R.id.insert:
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
                break;
            //更新数据信息
            case R.id.update:
                SQLiteDatabase db_up = databaseHelper.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put("price", 10.99);
                db_up.update("Book", values1, "name = ?", new String[]{"The DaVinci Code" });
                //查询信息
            case R.id.query:
                SQLiteDatabase db_qu = databaseHelper.getWritableDatabase();
                Cursor cursor = db_qu.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.
                                getColumnIndex("name"));
                        String author = cursor.getString(cursor.
                                getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex
                                ("pages"));
                        double price = cursor.getDouble(cursor.
                                getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            //删除记录
            case R.id.delete:
                SQLiteDatabase db_de = databaseHelper.getWritableDatabase();
                db_de.delete("Book", "pages > ?", new String[] { "500" });
                break;

            case R.id.replace_data:
                SQLiteDatabase db_re = databaseHelper.getWritableDatabase();
                db_re.beginTransaction();
                try {
                    db_re.delete("Book", null, null);
//                    if (true) {
//                        throw new NullPointerException();
//                    }
                    ContentValues values2 = new ContentValues();
                    values2.put("author", "lqf");
                    values2.put("price", "2");
                    values2.put("pages","300");
                    values2.put("name","GIT使用");
                    db_re.insert("Book", null, values2);
                    db_re.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db_re.endTransaction();
                }
                break;
            default:
                Log.i(SWORD, "error");
                break;
        }
    }
}
