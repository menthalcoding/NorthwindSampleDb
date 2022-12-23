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

import HttpClient.DataTransferObjects.CategoriesDto;
import Util.Constant;
import Util.Converter;
import View.UI.CategoriesListActivity;
import View.ViewHolder.CategoriesListViewHolder;

public class CategoriesListRecyclerViewAdapter extends RecyclerView.Adapter<CategoriesListViewHolder> {

    List<CategoriesDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public CategoriesListRecyclerViewAdapter(List<CategoriesDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public CategoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_row_layout, parent, false);
        CategoriesListViewHolder holder = new CategoriesListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        CategoriesDto item = list.get(position);
		holder.tvCategoryID.setText((item.getCategoryID() != null ? String.valueOf(item.getCategoryID()) : ""));
		holder.tvCategoryName.setText((item.getCategoryName() != null ? String.valueOf(item.getCategoryName()) : ""));
		holder.tvDescription.setText((item.getDescription() != null ? String.valueOf(item.getDescription()) : ""));
		holder.tvPicture.setText((item.getPicture() != null ? String.valueOf(item.getPicture()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
