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

public class CustomerActivity extends AppCompatActivity {
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getIntExtra("Id", -1);
        try{
            setContentView(R.layout.activity_custom);
            if (id > 0) {
                ((EditText) findViewById(R.id.name)).setEnabled(false);
                ((EditText) findViewById(R.id.phone)).setEnabled(false);
                ((EditText) findViewById(R.id.email)).setEnabled(false);
                CustomManager.getCustom(this, id);
            }
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save new Customer
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

    public void onServiceTypeLoaded(Customer customer) {
        ((EditText) findViewById(R.id.name)).setText(customer.getName());
        ((EditText) findViewById(R.id.name)).setEnabled(true);

        ((EditText) findViewById(R.id.phone)).setText(customer.getPhone());
        ((EditText) findViewById(R.id.phone)).setEnabled(true);

        ((EditText) findViewById(R.id.email)).setText(customer.getEmail());
        ((EditText) findViewById(R.id.email)).setEnabled(true);
    }

    private class HttpCreateTask extends AsyncTask<Void, Void, Customer> {
        @Override
        protected Customer doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            Customer customer = new Customer(1, name, phone, email);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("Name", customer.getName());
            jsonParams.put("Phone", customer.getPhone());
            jsonParams.put("Email", customer.getEmail());
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
    private class HttpUpdateTask extends AsyncTask<Void, Void, Customer> {
        @Override
        protected Customer doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            Customer customer = new Customer(1, name, phone, email);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("Name", customer.getName());
            jsonParams.put("Phone", customer.getPhone());
            jsonParams.put("Email", customer.getEmail());
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
