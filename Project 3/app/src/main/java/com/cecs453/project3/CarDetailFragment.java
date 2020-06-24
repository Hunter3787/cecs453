package com.cecs453.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CarDetailFragment extends Fragment {

    private static final String carDetailsURLPrefix = "https://thawing-beach-68207.herokuapp.com/cars/";

    public CarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("item_id"))
            new GetCarDetailByID(carDetailsURLPrefix + getArguments()
                    .getString("item_id")).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_car_list_details, container, false);
        //if(MainActivity.carDetails != null)
           // view.
        return rootView;
    }

    public static CarDetailFragment newInstance(String selectedCar) {

        CarDetailFragment fragment = new CarDetailFragment();
        //Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putString("item_id", selectedCar);
        fragment.setArguments(arguments);
        return fragment;
    }
}