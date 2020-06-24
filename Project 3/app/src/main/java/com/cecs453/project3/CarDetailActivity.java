package com.cecs453.project3;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CarDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_details);
        Toolbar toolbar =  findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(savedInstanceState == null) {
            String selectedCar = getIntent().getStringExtra("item_id");
            CarDetailFragment fragment = new CarDetailFragment().newInstance(selectedCar);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.car_detail_container, fragment)
                    .commit();
        }
    }
}
