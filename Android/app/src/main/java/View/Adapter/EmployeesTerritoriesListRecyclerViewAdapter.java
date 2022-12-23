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

import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import Util.Constant;
import Util.Converter;
import View.UI.EmployeesTerritoriesListActivity;
import View.ViewHolder.EmployeesTerritoriesListViewHolder;

public class EmployeesTerritoriesListRecyclerViewAdapter extends RecyclerView.Adapter<EmployeesTerritoriesListViewHolder> {

    List<EmployeesTerritoriesDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public EmployeesTerritoriesListRecyclerViewAdapter(List<EmployeesTerritoriesDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public EmployeesTerritoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeesterritories_list_row_layout, parent, false);
        EmployeesTerritoriesListViewHolder holder = new EmployeesTerritoriesListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesTerritoriesListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        EmployeesTerritoriesDto item = list.get(position);
		holder.tvEmpleymontId.setText((item.getEmpleymontId() != null ? String.valueOf(item.getEmpleymontId()) : ""));
		holder.tvTerritoryID.setText((item.getTerritoryID() != null ? String.valueOf(item.getTerritoryID()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
