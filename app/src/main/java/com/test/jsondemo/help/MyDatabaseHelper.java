package com.test.jsondemo.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by SoleilLun on 2015/12/25.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String SWORD="SWORD";

    private Context mContext;
    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    public static final String CREATE_USER = "create table user ( "
            + "id integer primary key autoincrement, "
            + "name text,"
            + "sex integer )";

    public static final String CREATE_USEREXPEND = "create table uesrexpend ( "
            + "id integer primary key autoincrement, "
            + "address text )";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    //带两个参数的构造函数，调用的其实是带三个参数的构造函数
    public MyDatabaseHelper(Context context, String name){
        this(context,name,VERSION);
    }
    //带三个参数的构造函数，调用的是带所有参数的构造函数
    public MyDatabaseHelper(Context context, String name, int version){
        this(context, name, null, version);
    }
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(SWORD, "create a Database");
        //执行创建数据库操作
//        db.execSQL(CREATE_USER);
//        db.execSQL(CREATE_USEREXPEND);
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"创建新表成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//创建成功，日志输出提示
        Log.i(SWORD, "update a Database");
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
            case 2:
                db.execSQL("alter table Book add column category_id integer");
            default:
        }
    }
}
