package com.cecs453.project3;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class GetModel extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetModel.class.getSimpleName();
    private ArrayList<HashMap<String,String>> models;
    private WeakReference<MainActivity> weakReference;
    private String url;

    public GetModel(String url, MainActivity activity){
        this.url = url;
        weakReference = new WeakReference<>(activity);
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

                    models.add(between);
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
        ArrayList<String> modelNames = new ArrayList<>();

        for(HashMap<String, String> read : models) {
            modelNames.add(read.get("model"));
            Log.e(TAG, read.get("model"));
        }

        Spinner mModel = weakReference.get().findViewById(R.id.spnModel);
        mModel.setOnItemSelectedListener(weakReference.get());
        ArrayAdapter<String> modelA =
                new ArrayAdapter<>(weakReference.get(),
                        R.layout.spinner_item, modelNames);
        modelA.setDropDownViewResource(R.layout.spinner_item);
        mModel.setAdapter(modelA);
    }
}

