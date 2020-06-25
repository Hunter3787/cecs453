package com.cecs453.project3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImageTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = GetImageTask.class.getSimpleName();
    private String url;
    private ImageView view;
    private Bitmap bImage;

    public GetImageTask(String url, ImageView view){
        this.url = url;
        Log.e(TAG, "URL " + url);
        this.view = view;
    }

    @Override
    protected Void doInBackground(Void ... voids){
        bImage = null;
        try{
            URL urlActual = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlActual.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new java.net.URL(url).openStream();
            if(in != null)
                bImage = BitmapFactory.decodeStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void avoid){
        super.onPostExecute(avoid);
        if(bImage != null)
            view.setImageBitmap(bImage);
        else
            view.setImageResource(R.drawable.car_no_park);
    }
}
