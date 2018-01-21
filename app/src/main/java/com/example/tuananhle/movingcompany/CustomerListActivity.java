package com.example.tuananhle.movingcompany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
    private ArrayAdapter<Customer> adapter;
    private boolean select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.select = getIntent().getBooleanExtra("Select", false);

        try{
            setContentView(R.layout.activity_customer_list);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), CustomerActivity.class);
                    startActivity(intent);
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Get all customer from http://localhost/api/customer
            List<Customer> myStringArray = new ArrayList<>();
            adapter = new ArrayAdapter<Customer>(this,
                android.R.layout.simple_list_item_1, myStringArray);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
            // Click on item
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                    Class<? extends Activity> activityClass;

                    if (select){
                        activityClass = OrderActivity.class;
                    }
                    else {
                        activityClass = CustomerActivity.class;
                    }
                    Intent intent = new Intent(view.getContext(), activityClass);
                    Customer customer = myStringArray.get(position);
                    int id = customer.getId();
                    intent.putExtra("customerId", id);
                    intent.putExtra("customerName", customer.toString());
                    startActivity(intent);
                    if (select){
                        finish();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        CustomManager.getAll(this);
    }

    public void onItemsLoaded(List<Customer> customers) {
        adapter.clear();
        adapter.addAll(customers);
    }
}



