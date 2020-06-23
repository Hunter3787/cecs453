package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetModel extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetCars.class.getSimpleName();
    private HashMap<String,String> models;
    private String url;

    public GetModel(String url){
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray cars = new JSONArray(jsonStr);

                for (int i = 0; i < cars.length(); i++) {
                    JSONObject c = cars.getJSONObject(i);

                    String color_id = c.getString("color_id");
                    String content_local_url = c.getString("content_local_url");
                    String content_url = c.getString("content_url");
                    String created_at = c.getString("created_at");
                    String currency_id = c.getString("currency_id");
                    String id = c.getString("id");
                    String image_local_url = c.getString("image_local_url");
                    String image_url = c.getString("image_url");
                    String is_active = c.getString("is_active");
                    String mileage = c.getString("mileage");
                    String onlinecarddealer_id = c.getString("onlinecaddealer_id");
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

                    models = new HashMap<>();

                    models.put("color_id", color_id);
                    models.put("content_local_url", content_local_url);
                    models.put("content_url", content_url);
                    models.put("created_at", created_at);
                    models.put("currency_id", currency_id);
                    models.put("id", id);
                    models.put("image_local_url", image_local_url);
                    models.put("image_url", image_url);
                    models.put("is_active", is_active);
                    models.put("mileage", mileage);
                    models.put("onlinecarddealer_id", onlinecarddealer_id);
                    models.put("price", price);
                    models.put("seller_address", seller_address);
                    models.put("seller_address_locality", seller_address_locality);

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
        MainActivity.carModelList = models;
    }
}

