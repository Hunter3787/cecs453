package com.cecs453.project3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CustomCarAdapter
        extends RecyclerView.Adapter <CustomCarAdapter.ViewHolder> {

    private static final String TAG = CustomCarAdapter.class.getSimpleName();
    private ArrayList<Car> mCars;
    private Context context;
    private WeakReference<MainActivity> weakReference;
    private boolean mTwoPane;

    public CustomCarAdapter(ArrayList<Car> inCars,
                            boolean twoPane, MainActivity activity){
        mCars = inCars;
        this.context = activity.getApplicationContext();
        mTwoPane = twoPane;
        weakReference = new WeakReference<>(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        new GetImageTask(mCars.get(position).imgURL, holder.mImgView);

        holder.mItem = mCars.get(position);
        holder.mPriceView.setText("$" + mCars.get(position).price + "0");
        holder.mDetail.setText(mCars.get(position).mileage + " miles");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTwoPane){
                    String selectedCar = mCars.get(holder.getAdapterPosition()).carID;
                    CarDetailFragment fragment = CarDetailFragment.newInstance(selectedCar);
                    weakReference.get()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.car_detail_container,fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else{
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CarDetailActivity.class);
                    intent.putExtra("item_id", mCars.get(holder.getAdapterPosition()).carID);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImgView;
        final TextView mDetail;
        final TextView mPriceView;
        Car mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mImgView = view.findViewById(R.id.imgCar);
            mPriceView =  view.findViewById(R.id.price);
            mDetail=  view.findViewById(R.id.detail);
        }
    }
}
