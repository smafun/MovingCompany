package com.example.tuananhle.movingcompany;

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

/**
 * Created by tuananhle on 18.01.2018.
 */

public class CustomerActivity extends AppCompatActivity {
    private ArrayAdapter<Custom> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_customer);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), CustomActivity.class);
                    startActivity(intent);
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Get all customer from http://localhost/api/customer
            List<Custom> myStringArray = new ArrayList<>();
            adapter = new ArrayAdapter<Custom>(this,
                android.R.layout.simple_list_item_1, myStringArray);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);
            // Click on item
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                    Intent intent = new Intent(view.getContext(), CustomActivity.class);
                    int id = myStringArray.get(position).getId();
                    intent.putExtra("Id", id);
                    startActivity(intent);
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

    public void onItemsLoaded(List<Custom> customer) {
        adapter.clear();
        adapter.addAll(customer);
    }
}



