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


public class ServiceTypesActivity extends AppCompatActivity {
    private ArrayAdapter<ServiceType> adapter;

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

        List<ServiceType> myStringArray = new ArrayList<ServiceType>();
        adapter = new ArrayAdapter<ServiceType>(this,
                android.R.layout.simple_list_item_1, myStringArray);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // Click on item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                Intent intent = new Intent(view.getContext(), ServiceTypeActivity.class);
                int id = myStringArray.get(position).getId();
                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });
    }

    public void onResume() {
        super.onResume();
        ServiceTypeService.getAll(this);
    }

    public void onItemsLoaded(List<ServiceType> serviceTypes) {
        adapter.clear();
        adapter.addAll(serviceTypes);
    }
}
