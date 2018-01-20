package com.example.tuananhle.movingcompany;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tuananhle on 18.01.2018.
 */

public class NewCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_customer);
        try{

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton delete = (FloatingActionButton) findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Delete item
                }
            });

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton update = (FloatingActionButton) findViewById(R.id.update);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Update item
                }
            });
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
                e.printStackTrace();
        }
    }
}

