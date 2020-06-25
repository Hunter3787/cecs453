package com.cecs453.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

public class CarDetailFragment extends Fragment {
    private static final String TAG = CarDetailFragment.class.getSimpleName();

    public CarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        if(MainActivity.mTwoPane)
            rootView = inflater.inflate(R.layout.activity_car_list_details_tablet,
                            container, false);
        else
            rootView = inflater.inflate(R.layout.activity_car_list_details,
                    container, false);

        if(MainActivity.carDetails != null){
            String vehicleModel = "";
            for(HashMap<String,String> map : MainActivity.carModelHashList)
                for(String key : map.keySet())
                    if(key == "id")
                        vehicleModel = map.get("model");

            ((TextView)rootView.findViewById(R.id.txtMake))
                    .setText(MainActivity.carMakeHash
                            .get(MainActivity.carDetails.get("vehicle_make_id"))
                            + " - " + vehicleModel);
            ((TextView)rootView.findViewById(R.id.txtPrice))
                    .setText("$" + MainActivity.carDetails.get("price") + "0");
            ((TextView)rootView.findViewById(R.id.txtDetails))
                    .setText(MainActivity.carDetails.get("veh_description"));
            ((TextView)rootView.findViewById(R.id.txtLastUpdate)).
                    setText("Last updated: " + MainActivity.carDetails.get("updated_at"));
        }
        return rootView;
    }

    public static CarDetailFragment newInstance() {
        CarDetailFragment fragment = new CarDetailFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }
}