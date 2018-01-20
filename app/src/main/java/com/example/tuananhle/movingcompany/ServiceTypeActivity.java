package com.example.tuananhle.movingcompany;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tuananhle on 18.01.2018.
 */

public class ServiceTypeActivity extends AppCompatActivity {
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getIntExtra("Id", -1);

        try{
            setContentView(R.layout.activity_service_type);

            if (id > 0) {
                ((EditText) findViewById(R.id.name)).setEnabled(false);
                ServiceTypeService.getServiceType(this, id);
            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save new ServiceTypes
                    if(id == -1){
                        new HttpCreateTask().execute();
                    }
                    else {
                        new HttpUpdateTask().execute();
                    }

                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setServiceType(ServiceType serviceType) {
        ((EditText) findViewById(R.id.name)).setText(serviceType.getName());
        ((EditText) findViewById(R.id.name)).setEnabled(true);
    }

    private class HttpCreateTask extends AsyncTask<Void, Void, ServiceType> {
        @Override
        protected ServiceType doInBackground(Void... params) {
            String text = ((EditText) findViewById(R.id.name)).getText().toString();
            ServiceType serviceType = new ServiceType(1, text);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("name", serviceType.getName());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, ConstantsUrl.SERVICETYPES,
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
    private class HttpUpdateTask extends AsyncTask<Void, Void, ServiceType> {
        @Override
        protected ServiceType doInBackground(Void... params) {
            String text = ((EditText) findViewById(R.id.name)).getText().toString();
            ServiceType serviceType = new ServiceType(id, text);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("name", serviceType.getName());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.PUT, ConstantsUrl.SERVICETYPES + "/" +id,
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

    /*    @Override
        protected void onPostExecute(ServiceType greeting) {
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(greeting.getId());
            greetingContentText.setText(greeting.getContent());
        }*/

    }

}




