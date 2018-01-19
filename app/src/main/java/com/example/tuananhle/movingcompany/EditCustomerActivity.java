package com.example.tuananhle.movingcompany;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tuananhle on 19.01.2018.
 */

public class EditCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            setContentView(R.layout.new_activity_customer);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Bundle b = getIntent().getExtras();
            int id = b.getInt("id");

            String name = "Tuan";
            String phone = "12345678";
            String email = "norway@norway.no";

            final EditText Name = (EditText) findViewById(R.id.customerName);
            final EditText Phone = (EditText) findViewById(R.id.customerPhone);
            final EditText Email = (EditText) findViewById(R.id.customerEmail);

            Name.setText(name);
            Phone.setText(phone);
            Email.setText(email);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
