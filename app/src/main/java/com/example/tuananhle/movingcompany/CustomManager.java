package com.example.tuananhle.movingcompany;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CustomManager {
    public static void getCustom(CustomerActivity activity, int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        RequestQueue queue = Volley.newRequestQueue(activity);
        StringRequest getRequest = new StringRequest(Request.Method.GET, ConstantsUrl.CUSTOMER + "/"+ id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(response);
                        Gson gson = new Gson();
                        Customer customer = gson.fromJson(mJson, Customer.class);
                        activity.onServiceTypeLoaded(customer);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("MC", "list customers");
                    }
                }
        ) ;

        queue.add(getRequest);
    }

    public static void getAll(CustomerListActivity activity) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        StringRequest getRequest = new StringRequest(Request.Method.GET, ConstantsUrl.CUSTOMER,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(response);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Customer>>(){}.getType();
                        List<Customer> customers = (List<Customer>)gson.fromJson(mJson, listType);
                        activity.onItemsLoaded(customers);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("MC", "list customers");
                    }
                }
        ) ;
        queue.add(getRequest);
    }

}
