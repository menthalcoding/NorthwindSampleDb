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

import HttpClient.DataTransferObjects.ShippersDto;
import Util.Constant;
import Util.Converter;
import View.UI.ShippersListActivity;
import View.ViewHolder.ShippersListViewHolder;

public class ShippersListRecyclerViewAdapter extends RecyclerView.Adapter<ShippersListViewHolder> {

    List<ShippersDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public ShippersListRecyclerViewAdapter(List<ShippersDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public ShippersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shippers_list_row_layout, parent, false);
        ShippersListViewHolder holder = new ShippersListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShippersListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        ShippersDto item = list.get(position);
		holder.tvShipperId.setText((item.getShipperId() != null ? String.valueOf(item.getShipperId()) : ""));
		holder.tvCompanyName.setText((item.getCompanyName() != null ? String.valueOf(item.getCompanyName()) : ""));
		holder.tvPhone.setText((item.getPhone() != null ? String.valueOf(item.getPhone()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
