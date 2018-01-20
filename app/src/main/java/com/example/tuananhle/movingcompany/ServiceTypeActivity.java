package com.example.tuananhle.movingcompany;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tuananhle on 18.01.2018.
 */

public class ServiceTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.activity_service_type);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save new ServiceTypes
                    new HttpRequestTask().execute();
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*    public void save()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://rest-service.guides.spring.io/greeting";
        String url = ConstantsUrl.SERVICETYPES;
        StringRequest putRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        JsonParser parser = new JsonParser();
                        JsonElement mJson =  parser.parse(response);
                        Gson gson = new Gson();
                        ServiceType object = gson.fromJson(mJson, ServiceType.class);

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

        queue.add(putRequest);
    }
*/
    private class HttpRequestTask extends AsyncTask<Void, Void, ServiceType> {
        @Override
        protected ServiceType doInBackground(Void... params) {
            ServiceType serviceType = new ServiceType(1, "ww");
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

    /*    @Override
        protected void onPostExecute(ServiceType greeting) {
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(greeting.getId());
            greetingContentText.setText(greeting.getContent());
        }*/

    }

}





