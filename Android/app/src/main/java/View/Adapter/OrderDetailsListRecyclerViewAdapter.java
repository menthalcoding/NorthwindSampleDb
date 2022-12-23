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

import HttpClient.DataTransferObjects.OrderDetailsDto;
import Util.Constant;
import Util.Converter;
import View.UI.OrderDetailsListActivity;
import View.ViewHolder.OrderDetailsListViewHolder;

public class OrderDetailsListRecyclerViewAdapter extends RecyclerView.Adapter<OrderDetailsListViewHolder> {

    List<OrderDetailsDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public OrderDetailsListRecyclerViewAdapter(List<OrderDetailsDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public OrderDetailsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderdetails_list_row_layout, parent, false);
        OrderDetailsListViewHolder holder = new OrderDetailsListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        OrderDetailsDto item = list.get(position);
		holder.tvOrderId.setText((item.getOrderId() != null ? String.valueOf(item.getOrderId()) : ""));
		holder.tvProductId.setText((item.getProductId() != null ? String.valueOf(item.getProductId()) : ""));
		holder.tvUnitPrice.setText((item.getUnitPrice() != null ? String.valueOf(item.getUnitPrice()) : ""));
		holder.tvQuantity.setText((item.getQuantity() != null ? String.valueOf(item.getQuantity()) : ""));
		holder.tvDiscount.setText((item.getDiscount() != null ? String.valueOf(item.getDiscount()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
