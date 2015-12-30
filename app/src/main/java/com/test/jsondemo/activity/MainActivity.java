package com.test.jsondemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.jsondemo.R;
import com.test.jsondemo.bean.JsonBean;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button sendRequest;
    private TextView textView;
    private Button con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button) findViewById(R.id.send_request);
        textView = (TextView) findViewById(R.id.response);
        con = (Button) findViewById(R.id.context);

        sendRequest.setOnClickListener(this);
        con.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                Intent intent = new Intent(MainActivity.this,SQLiteActivity.class);
                startActivity(intent);
                try {
                    String s = setJsonData();
                    JsonBean jsonB = getJsonBean(s);

                    String na = jsonB.getName();
                    Log.d("4 lpp", String.valueOf(jsonB.getName()));
                    int ag = jsonB.getAge();
                    Log.d("1 lpp", String.valueOf(jsonB.getAge()));
                    textView.setText("name : " + na + "\n" + "age : " + ag);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.context:
                Intent intent1 = new Intent(MainActivity.this,ConDemoActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public String setJsonData() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "lpp");
        jsonObject.put("age", "18");

        Log.d("2 lpp", jsonObject.toString());
        return jsonObject.toString();
    }

    public JsonBean getJsonBean(String jsonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonData);
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");

        JsonBean jb = new JsonBean();
        jb.setName(name);
        jb.setAge(age);

        Log.d("3 lpp", String.valueOf(jb));
        return jb;
    }
}
