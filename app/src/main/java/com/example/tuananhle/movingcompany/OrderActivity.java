package com.example.tuananhle.movingcompany;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
 * Created by tuananhle on 18.01.2018.
 */

public class OrderActivity extends AppCompatActivity {
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getIntExtra("Id", -1);
        try{
            setContentView(R.layout.activity_order);
            if (id > 0) {
                ((EditText) findViewById(R.id.name)).setEnabled(false);
                ((EditText) findViewById(R.id.addfrom)).setEnabled(false);
                ((EditText) findViewById(R.id.addto)).setEnabled(false);
                ((EditText) findViewById(R.id.servicetypes)).setEnabled(false);
                ((EditText) findViewById(R.id.date)).setEnabled(false);
                ((EditText) findViewById(R.id.freetxt)).setEnabled(false);
                OrderManager.getOrder(this, id);
            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Test dropdwon
    /*        //get the spinner from the xml.
            Spinner dropdown = findViewById(R.id.spinner1);
            //create a list of items for the spinner.
            String[] items = new String[]{"1", "2", "three"};
            //create an adapter to describe how the items are displayed, adapters are used in several places in android.
            //There are multiple variations of this, but this is the basic variant.
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            //set the spinners adapter to the previously created one.
            dropdown.setAdapter(adapter);
    */
            // End test

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save new Customer
                    if(id == -1){
                        new OrderActivity.HttpCreateTask().execute();
                    }
                    else {
                        new OrderActivity.HttpUpdateTask().execute();
                    }
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onServiceTypeLoaded(Order order) {
        ((EditText) findViewById(R.id.name)).setText(order.getCustomName());
        ((EditText) findViewById(R.id.name)).setEnabled(true);

        ((EditText) findViewById(R.id.addfrom)).setText(order.getAddressFrom());
        ((EditText) findViewById(R.id.addfrom)).setEnabled(true);

        ((EditText) findViewById(R.id.addto)).setText(order.getAddresseTo());
        ((EditText) findViewById(R.id.addto)).setEnabled(true);

        ((EditText) findViewById(R.id.servicetypes)).setText(order.getServiceTypes());
        ((EditText) findViewById(R.id.servicetypes)).setEnabled(true);

        ((EditText) findViewById(R.id.date)).setText(order.getDate());
        ((EditText) findViewById(R.id.date)).setEnabled(true);

        ((EditText) findViewById(R.id.freetxt)).setText(order.getFreeText());
        ((EditText) findViewById(R.id.freetxt)).setEnabled(true);
    }

    private class HttpCreateTask extends AsyncTask<Void, Void, Order> {
        @Override
        protected Order doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String addfrom = ((EditText) findViewById(R.id.addfrom)).getText().toString();
            String addto = ((EditText) findViewById(R.id.addto)).getText().toString();
            String servicetypes = ((EditText) findViewById(R.id.servicetypes)).getText().toString();
            String date = ((EditText) findViewById(R.id.date)).getText().toString();
            String freetxt = ((EditText) findViewById(R.id.freetxt)).getText().toString();
            Order order = new Order(1, name, addfrom, addto,servicetypes,date, freetxt);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("customName", order.getCustomName());
            jsonParams.put("addrFrom", order.getAddressFrom());
            jsonParams.put("addrTo", order.getAddresseTo());
            jsonParams.put("serviceTypes", order.getServiceTypes());
            jsonParams.put("date", order.getDate());
            jsonParams.put("freeText", order.getFreeText());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, ConstantsUrl.ORDER,
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
    private class HttpUpdateTask extends AsyncTask<Void, Void, Order> {
        @Override
        protected Order doInBackground(Void... params) {
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String addfrom = ((EditText) findViewById(R.id.addfrom)).getText().toString();
            String addto = ((EditText) findViewById(R.id.addto)).getText().toString();
            String servicetypes = ((EditText) findViewById(R.id.servicetypes)).getText().toString();
            String date = ((EditText) findViewById(R.id.date)).getText().toString();
            String freetxt = ((EditText) findViewById(R.id.freetxt)).getText().toString();
            Order order = new Order(1, name, addfrom, addto,servicetypes,date, freetxt);

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("addrFrom", order.getAddressFrom());
            jsonParams.put("addrTo", order.getAddresseTo());
            jsonParams.put("serviceTypes", order.getServiceTypes());
            jsonParams.put("date", order.getDate());
            jsonParams.put("freeText", order.getFreeText());
            JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.PUT, ConstantsUrl.ORDER + "/" +id,
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