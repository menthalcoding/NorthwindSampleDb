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

import HttpClient.DataTransferObjects.RegionDto;
import Util.Constant;
import Util.Converter;
import View.UI.RegionListActivity;
import View.ViewHolder.RegionListViewHolder;

public class RegionListRecyclerViewAdapter extends RecyclerView.Adapter<RegionListViewHolder> {

    List<RegionDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public RegionListRecyclerViewAdapter(List<RegionDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public RegionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.region_list_row_layout, parent, false);
        RegionListViewHolder holder = new RegionListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegionListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        RegionDto item = list.get(position);
		holder.tvRegionID.setText((item.getRegionID() != null ? String.valueOf(item.getRegionID()) : ""));
		holder.tvRegionDescription.setText((item.getRegionDescription() != null ? String.valueOf(item.getRegionDescription()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
