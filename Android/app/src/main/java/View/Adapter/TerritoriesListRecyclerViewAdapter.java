package View.Adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.northwindsampledb.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import HttpClient.DataTransferObjects.TerritoriesDto;
import Util.Constant;
import Util.Converter;
import View.UI.TerritoriesListActivity;
import View.ViewHolder.TerritoriesListViewHolder;

public class TerritoriesListRecyclerViewAdapter extends RecyclerView.Adapter<TerritoriesListViewHolder> {

    List<TerritoriesDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public TerritoriesListRecyclerViewAdapter(List<TerritoriesDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public TerritoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.territories_list_row_layout, parent, false);
        TerritoriesListViewHolder holder = new TerritoriesListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TerritoriesListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        TerritoriesDto item = list.get(position);
		holder.tvTerritoryID.setText((item.getTerritoryID() != null ? String.valueOf(item.getTerritoryID()) : ""));
		holder.tvTerritoryDescription.setText((item.getTerritoryDescription() != null ? String.valueOf(item.getTerritoryDescription()) : ""));
		holder.tvRegionID.setText((item.getRegionID() != null ? String.valueOf(item.getRegionID()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
