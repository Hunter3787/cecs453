package com.cecs453.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CarDetailFragment extends Fragment {
    private static final String TAG = CarDetailFragment.class.getSimpleName();

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
        View rootView = inflater.inflate(R.layout.activity_car_list_details_tablet, container, false);

        if(MainActivity.carDetails != null){
            ((ImageView)rootView.findViewById(R.id.imgCar)).setImageBitmap(null);
            ((TextView)rootView.findViewById(R.id.txtMake))
                    .setText(MainActivity.carMakeHash
                            .get(MainActivity.carDetails.get("vehicle_make_id"))
                    + " - " + MainActivity.carMakeHash
                            .get(MainActivity.carDetails.get("vehicle_model_id")));
            ((TextView)rootView.findViewById(R.id.txtPrice))
                    .setText("$" + MainActivity.carDetails.get("price") + "0");
            ((TextView)rootView.findViewById(R.id.txtDetails))
                    .setText(MainActivity.carDetails.get("veh_description"));
            ((TextView)rootView.findViewById(R.id.txtLastUpdate)).
                    setText("Last updated: " + MainActivity.carDetails.get("updated_at"));
        }
        return rootView;
    }

    public static CarDetailFragment newInstance(String selectedCar) {
        CarDetailFragment fragment = new CarDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString("item_id", selectedCar);
        fragment.setArguments(arguments);
        return fragment;
    }
}