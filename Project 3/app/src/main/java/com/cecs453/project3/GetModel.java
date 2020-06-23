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
    private ArrayList<HashMap<String,String>> models;
    private String url;

    public GetModel(String url){
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        models = new ArrayList<>();

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray cars = new JSONArray(jsonStr);

                for (int i = 0; i < cars.length(); i++) {
                    JSONObject c = cars.getJSONObject(i);

                    String id = c.getString("id");
                    String model = c.getString("model");
                    String vehicle_make_id = c.getString("vehicle_make_id");

                    HashMap<String, String> between = new HashMap<>();

                    between.put("id", id);
                    between.put("model", model);
                    between.put("vehicle_make_id", vehicle_make_id);

                    MainActivity.carModelHashList.add(between);
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
        MainActivity.carModelHashList = models;
        for(HashMap carMakeHash : MainActivity.carModelHashList)
            for(Object key : carMakeHash.keySet()){
                String value = (String) carMakeHash.get(key);
                Log.e(TAG, "Car Make Hash to Array: " + key + " : " + value);
            }
    }
}

