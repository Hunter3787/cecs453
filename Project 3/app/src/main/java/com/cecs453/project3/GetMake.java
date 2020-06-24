package com.cecs453.project3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GetMake extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetMake.class.getSimpleName();
    private HashMap<String, String> makes;
    private WeakReference<MainActivity> weakReference;
    private String url;

    public GetMake(String url, MainActivity activity){
        this.url = url;
        weakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
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
        ArrayList<String> arrayList = new ArrayList<>();

        for(String key : makes.keySet()){
            String value = makes.get(key);
            arrayList.add(value);
        }

        Spinner mMake = weakReference.get().findViewById(R.id.spnMake);
        mMake.setOnItemSelectedListener(weakReference.get());
        ArrayAdapter<String> makeA =
                new ArrayAdapter<>(weakReference.get(),
                        R.layout.spinner_item, arrayList);
        makeA.setDropDownViewResource(R.layout.spinner_item);
        mMake.setAdapter(makeA);
    }
}
