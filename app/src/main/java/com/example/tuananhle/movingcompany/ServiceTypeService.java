package com.example.tuananhle.movingcompany;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by tuananhle on 20.01.2018.
 */

class ServiceTypeService {
    public static void getServiceType(ServiceTypeActivity activity, int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        RequestQueue queue = Volley.newRequestQueue(activity);
        StringRequest getRequest = new StringRequest(Request.Method.GET, ConstantsUrl.SERVICETYPES + "/"+ id,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(response);
                        Gson gson = new Gson();
                        ServiceType serviceType = gson.fromJson(mJson, ServiceType.class);
                        activity.setServiceType(serviceType);
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
