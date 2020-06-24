package com.cecs453.project3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class GetImageTask extends AsyncTask<Void, Void, Void> {
    private String url;
    private ImageView view;
    private Bitmap bImage;

    public GetImageTask(String url, ImageView view){
        this.url = url;
        this.view = view;
    }

    @Override
    protected Void doInBackground(Void ... voids){
        bImage = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
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
        view.setImageBitmap(bImage);
    }
}
