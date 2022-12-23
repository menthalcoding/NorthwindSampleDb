package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class OrderDetailsListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvOrderId;
	public TextView tvProductId;
	public TextView tvUnitPrice;
	public TextView tvQuantity;
	public TextView tvDiscount;

    public OrderDetailsListViewHolder(View itemView) {
        super(itemView);
		tvOrderId = (TextView)itemView.findViewById(R.id.tvOrderId);
		tvProductId = (TextView)itemView.findViewById(R.id.tvProductId);
		tvUnitPrice = (TextView)itemView.findViewById(R.id.tvUnitPrice);
		tvQuantity = (TextView)itemView.findViewById(R.id.tvQuantity);
		tvDiscount = (TextView)itemView.findViewById(R.id.tvDiscount);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
