package com.cecs453.project3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean mTwoPane = false;
    private static final String carListURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static final String carModelsULRPrefix = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/";
    private static final String carDetailsURLPrefix = "https://thawing-beach-68207.herokuapp.com/cars/";

    private ArrayList<String> modelNames;
    public static HashMap<String,String> carMakeHash;
    public static ArrayList<HashMap<String,String>> carModelHashList;
    public static ArrayList<HashMap<String,String>> carListings;
    public static ArrayList<HashMap<String,String>> carDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carMakeHash = new HashMap<>();
        modelNames = new ArrayList<>();
        carModelHashList = new ArrayList<>();
        carListings = new ArrayList<>();
        carDetails = new ArrayList<>();

        new GetMake(carListURL, this).execute();

        String carListTestURL = carDetailsURLPrefix + "10/20/92603";
        new GetListings(carListTestURL).execute();

        String carByIDTestURL = carDetailsURLPrefix +"3169";
        new GetCarDetailByID(carByIDTestURL).execute();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        RecyclerView recyclerView = findViewById(R.id.car_list);
        
        if(findViewById(R.id.car_detail_container) != null)
            mTwoPane = true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String affix = "";
        switch(parent.getId()){
            case R.id.spnMake:
                for(String comp : carMakeHash.keySet())
                    if( carMakeHash.get(comp) == parent.getItemAtPosition(position).toString())
                        affix = comp;
                String modelURL = carModelsULRPrefix + affix;

                new GetModel(modelURL, MainActivity.this).execute();
                break;

            case R.id.spnModel:


                break;

            default:
                break;
        }


        Log.e(TAG, "Picked: " + parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class CustomCarAdapter extends RecyclerView.Adapter
            <CustomCarAdapter.ViewHolder> {

        private List<String> mCars;

        public CustomCarAdapter(List<String> inCars){ mCars = inCars; }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position){
            /*
            holder.mView = mCars.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mContentView.setText(mValues.get(position).song_title);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mTwoPane){
                        int selectedSong = holder.getAdapterPosition();
                        SongDetailFragment fragment = SongDetailFragment.newInstance(selectedSong);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.song_detail_container,fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                    else{
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                SongDetailActivity.class);
                        intent.putExtra(SongUtils.SONG_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
            */
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final ImageView mImgView;
            final TextView mIdView;
            final TextView mContentView;
           // SongUtils.Song mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mImgView = view.findViewById(R.id.imgCar);
                mIdView =  view.findViewById(R.id.name);
                mContentView =  view.findViewById(R.id.detail);
            }
        }
    }
}