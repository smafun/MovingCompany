package com.example.tuananhle.movingcompany;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends ServiceTypeCallback {
    private int id;
    private int customerId;
    private String customerName;
    private ArrayAdapter<ServiceType> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getIntent().getIntExtra("Id", -1);
        this.customerId = getIntent().getIntExtra("customerId", -1);
        this.customerName = getIntent().getStringExtra("customerName");

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
            else if(customerId > 0) {
                ((EditText) findViewById(R.id.name)).setEnabled(false);
                ((EditText) findViewById(R.id.name)).setText(customerName);
            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //get the spinner from the xml.
            //create a list of items for the spinner.
            List<ServiceType> serviceTypes = new ArrayList<>();
            Spinner dropdown = findViewById(R.id.spinner1);

            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String tmpTxt = serviceTypes.get(position).getName();
                    String txt = ((EditText) findViewById(R.id.servicetypes)).getText().toString();
                    if (!txt.isEmpty())
                        txt += ", " + tmpTxt;
                    else
                        txt += tmpTxt;
                    ((EditText) findViewById(R.id.servicetypes)).setText(txt);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            //create a list of items for the spinner.
            //create an adapter to describe how the items are displayed, adapters are used in several places in android.
            //There are multiple variations of this, but this is the basic variant.
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, serviceTypes);
            //set the spinners adapter to the previously created one.
            dropdown.setAdapter(adapter);

            ServiceTypeService.getAll(this);
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemsLoaded(List<ServiceType> serviceTypes) {
        adapter.clear();
        adapter.addAll(serviceTypes);
    }

    public void onOrderLoaded(Order order) {
        ((EditText) findViewById(R.id.name)).setText(String.valueOf(order.getCustomerId()));
        ((EditText) findViewById(R.id.name)).setEnabled(false);

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

            String addfrom = ((EditText) findViewById(R.id.addfrom)).getText().toString();
            String addto = ((EditText) findViewById(R.id.addto)).getText().toString();
            String servicetypes = ((EditText) findViewById(R.id.servicetypes)).getText().toString();
            String date = ((EditText) findViewById(R.id.date)).getText().toString();
            String freetxt = ((EditText) findViewById(R.id.freetxt)).getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("CustomerId", String.valueOf(OrderActivity.this.customerId));
            jsonParams.put("AddressFrom", addfrom);
            jsonParams.put("AddressTo", addto);
            jsonParams.put("ServiceTypes", servicetypes);
            jsonParams.put("Date", date);
            jsonParams.put("TxtField", freetxt);
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
            //String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String addfrom = ((EditText) findViewById(R.id.addfrom)).getText().toString();
            String addto = ((EditText) findViewById(R.id.addto)).getText().toString();
            String servicetypes = ((EditText) findViewById(R.id.servicetypes)).getText().toString();
            String date = ((EditText) findViewById(R.id.date)).getText().toString();
            String freetxt = ((EditText) findViewById(R.id.freetxt)).getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            Map<String, String> jsonParams = new HashMap<>();

            jsonParams.put("AddressFrom", addfrom);
            jsonParams.put("AddressTo", addto);
            jsonParams.put("ServiceTypes", servicetypes);
            jsonParams.put("Date", date);
            jsonParams.put("TxtField", freetxt);
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