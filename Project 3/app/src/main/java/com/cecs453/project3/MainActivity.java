package com.cecs453.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    public static boolean mTwoPane = false;
    private static final String carMakesURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static final String carModelsULRPrefix = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/";
    private static final String carDetailsURLPrefix = "https://thawing-beach-68207.herokuapp.com/cars/";

    public static HashMap<String,String> carMakeHash;
    public static ArrayList<HashMap<String,String>> carModelHashList;
    public static ArrayList<HashMap<String,String>> carListings;
    public static HashMap<String,String> carDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carMakeHash = new HashMap<>();
        carModelHashList = new ArrayList<>();
        carListings = new ArrayList<>();
        carDetails = new HashMap<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        new GetMake(carMakesURL, this).execute();

        if(findViewById(R.id.car_detail_container) != null)
            mTwoPane = true;

        RecyclerView recyclerView = findViewById(R.id.car_list);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String affix = "";
        switch(parent.getId()){
            case R.id.spnMake:
                for(String comp : carMakeHash.keySet())
                    if(carMakeHash.get(comp) == parent.getItemAtPosition(position).toString())
                        affix = comp;
                String modelURL = carModelsULRPrefix + affix;

                new GetModel(modelURL, this).execute();
                break;

            case R.id.spnModel:
                String vehicle_make_id = "";
                String identity = "";
                for(HashMap<String,String> map : carModelHashList)
                    for(String read : map.keySet())
                        if(map.get(read).equals(parent.getItemAtPosition(position).toString())){
                            identity = map.get("id");
                            vehicle_make_id = map.get("vehicle_make_id");
                        }

                affix = vehicle_make_id + "/" + identity + "/" + "92603";
                String listURL = carDetailsURLPrefix + affix;

                new GetListings(listURL, this).execute();
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}