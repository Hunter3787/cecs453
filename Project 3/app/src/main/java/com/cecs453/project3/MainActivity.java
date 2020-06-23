package com.cecs453.project3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean mTwoPane = false;
    private static String carListURL = "https://thawing-beach-68207.herokuapp.com/carmakes";
    private static String carModelsULRPrefix = "https://thawing-beach-68207.herokuapp.com/carmodelmakes/";
    private static String carDetailsURLPrefix = "https://thawing-beach-68207.herokuapp.com/cars/";
    public static HashMap<String,String> carMakeList;
    public static HashMap<String,String> carModelList;
    public static ArrayList<HashMap<String,String>> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        new GetMake(carListURL).execute();

        Spinner mMake = findViewById(R.id.spnMake);
        Spinner mModel = findViewById(R.id.spnModel);
        RecyclerView recyclerView = findViewById(R.id.car_list);

        recyclerView.setAdapter(new CustomCarAdapter(carMakeList));

        mMake.setOnItemSelectedListener(this);
        ArrayAdapter<String> makeA =
                new ArrayAdapter<>(getApplication(),
                        R.layout.spinner_item, carMakeList);
        makeA.setDropDownViewResource(R.layout.spinner_item);
        mMake.setAdapter(makeA);

        mModel.setOnItemSelectedListener(this);
        ArrayAdapter<String> modelA =
                new ArrayAdapter<>(this,
                        R.layout.spinner_item, carMakeList);
        modelA.setDropDownViewResource(R.layout.spinner_item);
        mModel.setAdapter(modelA);


        //Spinner mMake receives information and stores it in local variable.
        //Concatonate id from selected spinner one to url for option under spinner mModel.
        //Concatonate mModel to the previous URL string and get the available cars by make an model.






        if(findViewById(R.id.car_detail_container) != null)
            mTwoPane = true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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