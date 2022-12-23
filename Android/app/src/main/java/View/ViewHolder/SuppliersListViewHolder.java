package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class SuppliersListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvSupplierID;
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
	public TextView tvHomePage;

    public SuppliersListViewHolder(View itemView) {
        super(itemView);
		tvSupplierID = (TextView)itemView.findViewById(R.id.tvSupplierID);
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
		tvHomePage = (TextView)itemView.findViewById(R.id.tvHomePage);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
