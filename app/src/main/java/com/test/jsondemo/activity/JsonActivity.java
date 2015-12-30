package com.test.jsondemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.jsondemo.R;
import com.test.jsondemo.bean.jsonHttpBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SoleilLun on 2015/12/25.
 */
public class JsonActivity extends Activity{
    private Button sendRequest;
    private TextView responseText;
    String URL = "http://127.0.0.1:8080/get_data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);
        sendRequest = (Button) findViewById(R.id.send_req);
        responseText = (TextView) findViewById(R.id.req_response);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.send_req) {
                    Log.d("lpp", "sendrequest");
                    //sendRequestWithHttpClient();
                }
            }
        });
    }
    public String setaJsonData() throws JSONException {
        jsonHttpBean js = new jsonHttpBean();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","10");
        jsonObject.put("name","lpp");
        jsonObject.put("version","1");

        js.setId(10);
        js.setName("lpp");
        js.setVersion(1);
        Log.d("2 lpp", jsonObject.toString());
        return jsonObject.toString();
    }
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");

                jsonHttpBean jsonHttpBean = new jsonHttpBean();
                jsonHttpBean.getId();
                jsonHttpBean.getName();
                jsonHttpBean.getVersion();

                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
