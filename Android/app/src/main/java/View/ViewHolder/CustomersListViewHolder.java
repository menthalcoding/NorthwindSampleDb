package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class CustomersListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvCustomerID;
	public TextView tvCompanyName;
	public TextView tvContactName;
	public TextView tvContactTitle;
	public TextView tvAddress;
	public TextView tvCity;
	public TextView tvRegion;
	public TextView tvPostalCode;
	public TextView tvCountry;
	public TextView tvPhone;
	public TextView tvFax;

    public CustomersListViewHolder(View itemView) {
        super(itemView);
		tvCustomerID = (TextView)itemView.findViewById(R.id.tvCustomerID);
		tvCompanyName = (TextView)itemView.findViewById(R.id.tvCompanyName);
		tvContactName = (TextView)itemView.findViewById(R.id.tvContactName);
		tvContactTitle = (TextView)itemView.findViewById(R.id.tvContactTitle);
		tvAddress = (TextView)itemView.findViewById(R.id.tvAddress);
		tvCity = (TextView)itemView.findViewById(R.id.tvCity);
		tvRegion = (TextView)itemView.findViewById(R.id.tvRegion);
		tvPostalCode = (TextView)itemView.findViewById(R.id.tvPostalCode);
		tvCountry = (TextView)itemView.findViewById(R.id.tvCountry);
		tvPhone = (TextView)itemView.findViewById(R.id.tvPhone);
		tvFax = (TextView)itemView.findViewById(R.id.tvFax);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
