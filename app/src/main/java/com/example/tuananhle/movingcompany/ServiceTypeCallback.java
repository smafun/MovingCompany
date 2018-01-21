package com.example.tuananhle.movingcompany;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

public abstract class ServiceTypeCallback extends AppCompatActivity {
    public abstract void onItemsLoaded(List<ServiceType> serviceTypes);
}
