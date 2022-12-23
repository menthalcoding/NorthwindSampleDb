package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class OrderListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvOrderId;
	public TextView tvCustomerID;
	public TextView tvEmployeeID;
	public TextView tvOrderDate;
	public TextView tvRequiredDate;
	public TextView tvShippedDate;
	public TextView tvShipName;
	public TextView tvShipVia;
	public TextView tvFreight;
	public TextView tvShipAddress;
	public TextView tvShipCity;
	public TextView tvShipRegion;
	public TextView tvShipPostalCode;
	public TextView tvShipCountry;

    public OrderListViewHolder(View itemView) {
        super(itemView);
		tvOrderId = (TextView)itemView.findViewById(R.id.tvOrderId);
		tvCustomerID = (TextView)itemView.findViewById(R.id.tvCustomerID);
		tvEmployeeID = (TextView)itemView.findViewById(R.id.tvEmployeeID);
		tvOrderDate = (TextView)itemView.findViewById(R.id.tvOrderDate);
		tvRequiredDate = (TextView)itemView.findViewById(R.id.tvRequiredDate);
		tvShippedDate = (TextView)itemView.findViewById(R.id.tvShippedDate);
		tvShipName = (TextView)itemView.findViewById(R.id.tvShipName);
		tvShipVia = (TextView)itemView.findViewById(R.id.tvShipVia);
		tvFreight = (TextView)itemView.findViewById(R.id.tvFreight);
		tvShipAddress = (TextView)itemView.findViewById(R.id.tvShipAddress);
		tvShipCity = (TextView)itemView.findViewById(R.id.tvShipCity);
		tvShipRegion = (TextView)itemView.findViewById(R.id.tvShipRegion);
		tvShipPostalCode = (TextView)itemView.findViewById(R.id.tvShipPostalCode);
		tvShipCountry = (TextView)itemView.findViewById(R.id.tvShipCountry);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
