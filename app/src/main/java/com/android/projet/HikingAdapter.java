package com.android.projet;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HikingAdapter extends RecyclerView.Adapter<HikingAdapter.HikingViewHolder> {

    private List<HikingTrail> hikingTrailList;

    public HikingAdapter(List<HikingTrail> hikingTrailList, MainActivity mainActivity) {
        this.hikingTrailList = hikingTrailList;
    }

    @NonNull
    @Override
    public HikingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hiking, parent, false);
        return new HikingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikingViewHolder holder, int position) {
        HikingTrail currentHikingTrail = hikingTrailList.get(position);
        holder.hikingName.setText(currentHikingTrail.getName());
        holder.hikingLocation.setText(currentHikingTrail.getLocation());
        holder.hikingImage.setImageResource(currentHikingTrail.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return hikingTrailList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHikingTrailList(List<HikingTrail>hikingTrailList) {
        this.hikingTrailList = hikingTrailList;
        notifyDataSetChanged();
    }

    public static class HikingViewHolder extends RecyclerView.ViewHolder {
        public ImageView hikingImage;
        public TextView hikingName;
        public TextView hikingLocation;

        public HikingViewHolder(@NonNull View itemView) {
            super(itemView);
            hikingImage = itemView.findViewById(R.id.hikingImage);
            hikingName = itemView.findViewById(R.id.hikingName);
            hikingLocation = itemView.findViewById(R.id.hikingLocation);
        }
    }
}

