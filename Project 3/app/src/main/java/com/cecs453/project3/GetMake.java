package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GetMake extends AsyncTask<Void, Void, Void> {
    private AsyncResponse delegate = null;
    private static final String TAG = GetCars.class.getSimpleName();
    private HashMap<String, String> makes;
    private String url;

    public GetMake(String url){
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        makes = new HashMap<>();

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray cars = new JSONArray(jsonStr);

                for (int i = 0; i < cars.length(); i++) {
                    JSONObject c = cars.getJSONObject(i);

                    String id = c.getString("id");
                    String make = c.getString("vehicle_make");

                    makes.put(id, make);
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
        MainActivity.carMakeHash = makes;
    }
}
