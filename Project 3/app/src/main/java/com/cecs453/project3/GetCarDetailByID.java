package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCarDetailByID extends AsyncTask<Void,Void,Void> {

    private static final String TAG = GetListings.class.getSimpleName();
    private ArrayList<HashMap<String,String>> carListing;
    private String url;

    public GetCarDetailByID(String url){
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        carListing = new ArrayList<>();

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray cars = new JSONArray(jsonStr);

                for (int i = 0; i < cars.length(); i++) {
                    JSONObject c = cars.getJSONObject(i);

                    String id = c.getString("id");
                    String color_id = c.getString("color_id");
                    String content_local_url = c.getString("content_local_url");
                    String content_url = c.getString("content_url");
                    String created_at = c.getString("created_at");
                    String currency_id = c.getString("currency_id");
                    String image_local_url = c.getString("image_local_url");
                    String image_url = c.getString("image_url");
                    String is_active = c.getString("is_active");
                    String mileage = c.getString("mileage");
                    String onlinecardealer_id = c.getString("onlinecardealer_id");
                    String price = c.getString("price");
                    String seller_address = c.getString("seller_address");
                    String seller_address_locality = c.getString("seller_address_locality");
                    String seller_address_region = c.getString("seller_address_locality");
                    String seller_name = c.getString("seller_name");
                    String seller_telnumber = c.getString("seller_telnumber");
                    String updated_at = c.getString("updated_at");
                    String veh_description = c.getString("veh_description");
                    String vehicle_make_id = c.getString("vehicle_make_id");
                    String vehicle_model_id = c.getString("vehicle_model_id");
                    String vehicle_url = c.getString("vehicle_url");
                    String vin_number = c.getString("vin_number");
                    String zipcode_id = c.getString("zipcode_id");

                    HashMap<String, String> listingDetails = new HashMap<>();

                    listingDetails.put("id", id);
                    listingDetails.put("color_id", color_id);
                    listingDetails.put("content_local_url", content_local_url);
                    listingDetails.put("content_url", content_url);
                    listingDetails.put("created_at", created_at);
                    listingDetails.put("currency_id", currency_id);
                    listingDetails.put("image_local_url", image_local_url);
                    listingDetails.put("image_url", image_url);
                    listingDetails.put("is_active", is_active);
                    listingDetails.put("mileage", mileage);
                    listingDetails.put("onlinecardealer_id", onlinecardealer_id);
                    listingDetails.put("price", price);
                    listingDetails.put("seller_address", seller_address);
                    listingDetails.put("seller_address_locality", seller_address_locality);
                    listingDetails.put("seller_address_region", seller_address_region);
                    listingDetails.put("seller_name", seller_name);
                    listingDetails.put("seller_telnumber", seller_telnumber);
                    listingDetails.put("updated_at", updated_at);
                    listingDetails.put("veh_description", veh_description);
                    listingDetails.put("vehicle_make_id", vehicle_make_id);
                    listingDetails.put("vehicle_model_id", vehicle_model_id);
                    listingDetails.put("vehicle_url", vehicle_url);
                    listingDetails.put("vin_number", vin_number);
                    listingDetails.put("zipcode_id", zipcode_id);

                    carListing.add(listingDetails);
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
    protected  void onPostExecute(Void aVoid){

    }
}

