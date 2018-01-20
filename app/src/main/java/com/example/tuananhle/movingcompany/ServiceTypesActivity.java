package com.example.tuananhle.movingcompany;
//import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class ServiceTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_types);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServiceTypeActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Instantiate the RequestQueue.

    // Start testing

        List<ServiceType> myStringArray = new ArrayList<ServiceType>();
        ArrayAdapter<ServiceType> adapter = new ArrayAdapter<ServiceType>(this,
                android.R.layout.simple_list_item_1, myStringArray);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // Click on item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ServiceTypeActivity.class);
                id = myStringArray.get(position).getId();
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        Activity activity = this;
        StringRequest getRequest = new StringRequest(Request.Method.GET, ConstantsUrl.SERVICETYPES,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(response);
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ServiceType>>(){}.getType();
                        List<ServiceType> serviceTypes = (List<ServiceType>)gson.fromJson(mJson, listType);
                        adapter.clear();
                        adapter.addAll(serviceTypes);
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

        // End testing

        // start testing

    }



    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return stringBuilder.toString();
    }
}
