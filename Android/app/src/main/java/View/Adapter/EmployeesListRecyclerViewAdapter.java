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

import HttpClient.DataTransferObjects.EmployeesDto;
import Util.Constant;
import Util.Converter;
import View.UI.EmployeesListActivity;
import View.ViewHolder.EmployeesListViewHolder;

public class EmployeesListRecyclerViewAdapter extends RecyclerView.Adapter<EmployeesListViewHolder> {

    List<EmployeesDto> list = Collections.emptyList();
    Context context;
    Activity activity;
    boolean isLive;

    public EmployeesListRecyclerViewAdapter(List<EmployeesDto> data, Application application, boolean isLive) {
        this.list = data;
        this.context = application;
        this.isLive = isLive;
    }

    @NonNull
    @Override
    public EmployeesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employees_list_row_layout, parent, false);
        EmployeesListViewHolder holder = new EmployeesListViewHolder(v);
        //context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesListViewHolder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        EmployeesDto item = list.get(position);
		holder.tvEmployeeID.setText((item.getEmployeeID() != null ? String.valueOf(item.getEmployeeID()) : ""));
		holder.tvLastName.setText((item.getLastName() != null ? String.valueOf(item.getLastName()) : ""));
		holder.tvFirstName.setText((item.getFirstName() != null ? String.valueOf(item.getFirstName()) : ""));
		holder.tvTitle.setText((item.getTitle() != null ? String.valueOf(item.getTitle()) : ""));
		holder.tvTitleOfCourtesy.setText((item.getTitleOfCourtesy() != null ? String.valueOf(item.getTitleOfCourtesy()) : ""));
		holder.tvBirthDate.setText((item.getBirthDate() != null ? Converter.formatDate(item.getBirthDate()) : ""));
		holder.tvHireDate.setText((item.getHireDate() != null ? Converter.formatDate(item.getHireDate()) : ""));
		holder.tvAddress.setText((item.getAddress() != null ? String.valueOf(item.getAddress()) : ""));
		holder.tvCity.setText((item.getCity() != null ? String.valueOf(item.getCity()) : ""));
		holder.tvRegion.setText((item.getRegion() != null ? String.valueOf(item.getRegion()) : ""));
		holder.tvPostalCode.setText((item.getPostalCode() != null ? String.valueOf(item.getPostalCode()) : ""));
		holder.tvCountry.setText((item.getCountry() != null ? String.valueOf(item.getCountry()) : ""));
		holder.tvHomePhone.setText((item.getHomePhone() != null ? String.valueOf(item.getHomePhone()) : ""));
		holder.tvExtension.setText((item.getExtension() != null ? String.valueOf(item.getExtension()) : ""));
		holder.tvPhoto.setText((item.getPhoto() != null ? String.valueOf(item.getPhoto()) : ""));
		holder.tvNotes.setText((item.getNotes() != null ? String.valueOf(item.getNotes()) : ""));
		holder.tvReportsTo.setText((item.getReportsTo() != null ? String.valueOf(item.getReportsTo()) : ""));
		holder.tvPhotoPath.setText((item.getPhotoPath() != null ? String.valueOf(item.getPhotoPath()) : ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
