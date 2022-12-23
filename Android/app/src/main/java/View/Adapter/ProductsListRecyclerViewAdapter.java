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

import HttpClient.DataTransferObjects.ProductsDto;
import Util.Constant;
import Util.Converter;
import View.UI.ProductsListActivity;
import View.ViewHolder.ProductsListViewHolder;

public class ProductsListRecyclerViewAdapter extends RecyclerView.Adapter<ProductsListViewHolder> {

    List<ProductsDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public ProductsListRecyclerViewAdapter(List<ProductsDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public ProductsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_row_layout, parent, false);
        ProductsListViewHolder holder = new ProductsListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        ProductsDto item = list.get(position);
		holder.tvProductId.setText((item.getProductId() != null ? String.valueOf(item.getProductId()) : ""));
		holder.tvProductName.setText((item.getProductName() != null ? String.valueOf(item.getProductName()) : ""));
		holder.tvSupplierID.setText((item.getSupplierID() != null ? String.valueOf(item.getSupplierID()) : ""));
		holder.tvCategoryID.setText((item.getCategoryID() != null ? String.valueOf(item.getCategoryID()) : ""));
		holder.tvQuantityPerUnit.setText((item.getQuantityPerUnit() != null ? String.valueOf(item.getQuantityPerUnit()) : ""));
		holder.tvUnitPrice.setText((item.getUnitPrice() != null ? String.valueOf(item.getUnitPrice()) : ""));
		holder.tvUnitsInStock.setText((item.getUnitsInStock() != null ? String.valueOf(item.getUnitsInStock()) : ""));
		holder.tvUnitsOnOrder.setText((item.getUnitsOnOrder() != null ? String.valueOf(item.getUnitsOnOrder()) : ""));
		holder.tvReorderLevel.setText((item.getReorderLevel() != null ? String.valueOf(item.getReorderLevel()) : ""));
		holder.tvDiscontinued.setText((item.getDiscontinued() != null ? String.valueOf(item.getDiscontinued()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
