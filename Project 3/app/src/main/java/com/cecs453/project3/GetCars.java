package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCars extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetCars.class.getSimpleName();
    private ArrayList<HashMap<String,String>> carsList;
    private String url;

    public GetCars(){ }

    public GetCars(String url){
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

                    String id = c.getString("id");
                    String make = c.getString("vehicle make");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");

                    HashMap<String, String> details = new HashMap<>();

                    details.put("id", id);
                    details.put("make", make);
                    details.put("email", email);

                    carsList.add(details);
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
        MainActivity.cars = carsList;
    }

    public ArrayList<HashMap<String,String>> getCarsList(){ return carsList; }
}
