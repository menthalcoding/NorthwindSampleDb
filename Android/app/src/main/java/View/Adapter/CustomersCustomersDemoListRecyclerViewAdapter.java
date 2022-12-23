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

import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import Util.Constant;
import Util.Converter;
import View.UI.CustomersCustomersDemoListActivity;
import View.ViewHolder.CustomersCustomersDemoListViewHolder;

public class CustomersCustomersDemoListRecyclerViewAdapter extends RecyclerView.Adapter<CustomersCustomersDemoListViewHolder> {

    List<CustomersCustomersDemoDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public CustomersCustomersDemoListRecyclerViewAdapter(List<CustomersCustomersDemoDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public CustomersCustomersDemoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerscustomersdemo_list_row_layout, parent, false);
        CustomersCustomersDemoListViewHolder holder = new CustomersCustomersDemoListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersCustomersDemoListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        CustomersCustomersDemoDto item = list.get(position);
		holder.tvCustomerID.setText((item.getCustomerID() != null ? String.valueOf(item.getCustomerID()) : ""));
		holder.tvCustomersTypeId.setText((item.getCustomersTypeId() != null ? String.valueOf(item.getCustomersTypeId()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
