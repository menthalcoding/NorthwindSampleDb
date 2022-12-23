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

import HttpClient.DataTransferObjects.OrderDto;
import Util.Constant;
import Util.Converter;
import View.UI.OrderListActivity;
import View.ViewHolder.OrderListViewHolder;

public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<OrderListViewHolder> {

    List<OrderDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public OrderListRecyclerViewAdapter(List<OrderDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_row_layout, parent, false);
        OrderListViewHolder holder = new OrderListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        OrderDto item = list.get(position);
		holder.tvOrderId.setText((item.getOrderId() != null ? String.valueOf(item.getOrderId()) : ""));
		holder.tvCustomerID.setText((item.getCustomerID() != null ? String.valueOf(item.getCustomerID()) : ""));
		holder.tvEmployeeID.setText((item.getEmployeeID() != null ? String.valueOf(item.getEmployeeID()) : ""));
		holder.tvOrderDate.setText((item.getOrderDate() != null ? Converter.formatDate(item.getOrderDate()) : ""));
		holder.tvRequiredDate.setText((item.getRequiredDate() != null ? Converter.formatDate(item.getRequiredDate()) : ""));
		holder.tvShippedDate.setText((item.getShippedDate() != null ? Converter.formatDate(item.getShippedDate()) : ""));
		holder.tvShipName.setText((item.getShipName() != null ? String.valueOf(item.getShipName()) : ""));
		holder.tvShipVia.setText((item.getShipVia() != null ? String.valueOf(item.getShipVia()) : ""));
		holder.tvFreight.setText((item.getFreight() != null ? String.valueOf(item.getFreight()) : ""));
		holder.tvShipAddress.setText((item.getShipAddress() != null ? String.valueOf(item.getShipAddress()) : ""));
		holder.tvShipCity.setText((item.getShipCity() != null ? String.valueOf(item.getShipCity()) : ""));
		holder.tvShipRegion.setText((item.getShipRegion() != null ? String.valueOf(item.getShipRegion()) : ""));
		holder.tvShipPostalCode.setText((item.getShipPostalCode() != null ? String.valueOf(item.getShipPostalCode()) : ""));
		holder.tvShipCountry.setText((item.getShipCountry() != null ? String.valueOf(item.getShipCountry()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
