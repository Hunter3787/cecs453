package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetListings
        extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetListings.class.getSimpleName();
    private String url;

    public GetListings(String url){
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

                    HashMap<String, String> details = new HashMap<>();


                    //carsList.add(details);
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
       // MainActivity.cars = carsList;
    }
}

