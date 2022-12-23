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

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import Util.Constant;
import Util.Converter;
import View.UI.CustomersDemographicsListActivity;
import View.ViewHolder.CustomersDemographicsListViewHolder;

public class CustomersDemographicsListRecyclerViewAdapter extends RecyclerView.Adapter<CustomersDemographicsListViewHolder> {

    List<CustomersDemographicsDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public CustomersDemographicsListRecyclerViewAdapter(List<CustomersDemographicsDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public CustomersDemographicsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customersdemographics_list_row_layout, parent, false);
        CustomersDemographicsListViewHolder holder = new CustomersDemographicsListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersDemographicsListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        CustomersDemographicsDto item = list.get(position);
		holder.tvCustomersTypeId.setText((item.getCustomersTypeId() != null ? String.valueOf(item.getCustomersTypeId()) : ""));
		holder.tvCustomersDesc.setText((item.getCustomersDesc() != null ? String.valueOf(item.getCustomersDesc()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
