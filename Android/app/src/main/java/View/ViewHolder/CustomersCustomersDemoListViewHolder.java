package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class CustomersCustomersDemoListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvCustomerID;
	public TextView tvCustomersTypeId;

    public CustomersCustomersDemoListViewHolder(View itemView) {
        super(itemView);
		tvCustomerID = (TextView)itemView.findViewById(R.id.tvCustomerID);
		tvCustomersTypeId = (TextView)itemView.findViewById(R.id.tvCustomersTypeId);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
