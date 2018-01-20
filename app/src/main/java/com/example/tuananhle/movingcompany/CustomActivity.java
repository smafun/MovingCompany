package com.example.tuananhle.movingcompany;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tuananhle on 20.01.2018.
 */

public class CustomActivity extends AppCompatActivity {
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getIntExtra("Id", -1);
        try{
            setContentView(R.layout.activity_custom);
            if (id > 0) {
                ((EditText) findViewById(R.id.customName)).setEnabled(false);
                ((EditText) findViewById(R.id.customPhone)).setEnabled(false);
                ((EditText) findViewById(R.id.customPhone)).setEnabled(false);
                CustomManager.getCustom(this, id);
            }
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save new Custom
                    if(id == -1){
                        new CustomActivity.HttpCreateTask().execute();
                    }
                    else {
                        new CustomActivity.HttpUpdateTask().execute();
                    }
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onServiceTypeLoaded(Custom custom) {
        ((EditText) findViewById(R.id.customName)).setText(custom.getCustomName());
        ((EditText) findViewById(R.id.customName)).setEnabled(true);

        ((EditText) findViewById(R.id.customPhone)).setText(custom.getCustomPhone());
        ((EditText) findViewById(R.id.customPhone)).setEnabled(true);

        ((EditText) findViewById(R.id.customEmail)).setText(custom.getCustomEmail());
        ((EditText) findViewById(R.id.customEmail)).setEnabled(true);
    }

    private class HttpCreateTask extends AsyncTask<Void, Void, Custom> {
        @Override
        protected Custom doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.customName)).getText().toString();
            String phone = ((EditText) findViewById(R.id.customPhone)).getText().toString();
            String email = ((EditText) findViewById(R.id.customEmail)).getText().toString();
            Custom custom = new Custom(1, name, phone, email);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("customName", custom.getCustomName());
            jsonParams.put("customPhone", custom.getCustomPhone());
            jsonParams.put("customEmail", custom.getCustomEmail());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, ConstantsUrl.CUSTOMER,
                    new JSONObject(jsonParams),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //   Handle Error
                            Log.e("Error", "Error");
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("User-agent", System.getProperty("http.agent"));
                    return headers;
                }
            };
            requestQueue.add(postRequest);
            return null;
        }
    }
    private class HttpUpdateTask extends AsyncTask<Void, Void, Custom> {
        @Override
        protected Custom doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.customName)).getText().toString();
            String phone = ((EditText) findViewById(R.id.customPhone)).getText().toString();
            String email = ((EditText) findViewById(R.id.customEmail)).getText().toString();
            Custom custom = new Custom(1, name, phone, email);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("customName", custom.getCustomName());
            jsonParams.put("customPhone", custom.getCustomPhone());
            jsonParams.put("customEmail", custom.getCustomEmail());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.PUT, ConstantsUrl.CUSTOMER + "/" +id,
                    new JSONObject(jsonParams),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //   Handle Error
                            Log.e("Error", "Error");
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("User-agent", System.getProperty("http.agent"));
                    return headers;
                }
            };
            requestQueue.add(postRequest);
            return null;
        }
    }
}
