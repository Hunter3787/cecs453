package com.cecs453.project3;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class GetListings extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetListings.class.getSimpleName();
    private WeakReference<MainActivity> weakReference;
    private ArrayList<HashMap<String,String>> carListing;
    private ArrayList<Car> carArrayList;
    private boolean twoPane;
    private String url;

    public GetListings(String url,boolean twoPane ,MainActivity activity){
        this.url = url;
        weakReference = new WeakReference<>(activity);
        this.twoPane = twoPane;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        carListing = new ArrayList<>();
        carArrayList = new ArrayList<>();

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray cars = jsonObj.getJSONArray("lists");

                for (int i = 0; i < cars.length(); i++) {
                    JSONObject c = cars.getJSONObject(i);

                    String color = c.getString("color");
                    String created_at = c.getString("created_at");
                    String id = c.getString("id");
                    String image_url = c.getString("image_url");
                    String mileage = c.getString("mileage");
                    String model = c.getString("model");
                    String price = c.getString("price");
                    String veh_description = c.getString("veh_description");
                    String vehicle_make = c.getString("vehicle_make");
                    String vehicle_url = c.getString("vehicle_url");
                    String vin_number = c.getString("vin_number");

                    HashMap<String,String> listingDetails = new HashMap<>();

                    listingDetails.put("color_id", color);
                    listingDetails.put("created_at", created_at);
                    listingDetails.put("id", id);
                    listingDetails.put("image_url", image_url);
                    listingDetails.put("mileage", mileage);
                    listingDetails.put("model", model);
                    listingDetails.put("price", price);
                    listingDetails.put("veh_description", veh_description);
                    listingDetails.put("vehicle_make", vehicle_make);
                    listingDetails.put("vehicle_url", vehicle_url);
                    listingDetails.put("vin_number", vin_number);
                    listingDetails.put("vin_number", vin_number);

                    carListing.add(listingDetails);
                    carArrayList.add(new Car(image_url, mileage, price, id));
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.carListings = carListing;

        RecyclerView recyclerView = weakReference.get().findViewById(R.id.car_list);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(weakReference.get().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter
                (new CustomCarAdapter(carArrayList, twoPane, weakReference.get()));
    }
}

