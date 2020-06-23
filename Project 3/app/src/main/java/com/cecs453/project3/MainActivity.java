package com.cecs453.project3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;
    private static String carListURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static String carModelsULRPrefix = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/";
    private static String carDetailsURLPrefix = "https://thawing-beach-68207.herokuapp.com/cars/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        Spinner mMake = findViewById(R.id.spnMake);
        Spinner mModel = findViewById(R.id.spnModel);
        RecyclerView recyclerView = findViewById(R.id.car_list);

        /*
        if(findViewById(R.id.car_detail_container != null))
            mTwoPane = true;
            */


    }
}