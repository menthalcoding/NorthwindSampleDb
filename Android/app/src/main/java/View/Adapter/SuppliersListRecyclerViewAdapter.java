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

import HttpClient.DataTransferObjects.SuppliersDto;
import Util.Constant;
import Util.Converter;
import View.UI.SuppliersListActivity;
import View.ViewHolder.SuppliersListViewHolder;

public class SuppliersListRecyclerViewAdapter extends RecyclerView.Adapter<SuppliersListViewHolder> {

    List<SuppliersDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public SuppliersListRecyclerViewAdapter(List<SuppliersDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public SuppliersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.suppliers_list_row_layout, parent, false);
        SuppliersListViewHolder holder = new SuppliersListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuppliersListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        SuppliersDto item = list.get(position);
		holder.tvSupplierID.setText((item.getSupplierID() != null ? String.valueOf(item.getSupplierID()) : ""));
		holder.tvCompanyName.setText((item.getCompanyName() != null ? String.valueOf(item.getCompanyName()) : ""));
		holder.tvContactName.setText((item.getContactName() != null ? String.valueOf(item.getContactName()) : ""));
		holder.tvContactTitle.setText((item.getContactTitle() != null ? String.valueOf(item.getContactTitle()) : ""));
		holder.tvAddress.setText((item.getAddress() != null ? String.valueOf(item.getAddress()) : ""));
		holder.tvCity.setText((item.getCity() != null ? String.valueOf(item.getCity()) : ""));
		holder.tvRegion.setText((item.getRegion() != null ? String.valueOf(item.getRegion()) : ""));
		holder.tvPostalCode.setText((item.getPostalCode() != null ? String.valueOf(item.getPostalCode()) : ""));
		holder.tvCountry.setText((item.getCountry() != null ? String.valueOf(item.getCountry()) : ""));
		holder.tvPhone.setText((item.getPhone() != null ? String.valueOf(item.getPhone()) : ""));
		holder.tvFax.setText((item.getFax() != null ? String.valueOf(item.getFax()) : ""));
		holder.tvHomePage.setText((item.getHomePage() != null ? String.valueOf(item.getHomePage()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
