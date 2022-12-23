package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class ProductsListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvProductId;
	public TextView tvProductName;
	public TextView tvSupplierID;
	public TextView tvCategoryID;
	public TextView tvQuantityPerUnit;
	public TextView tvUnitPrice;
	public TextView tvUnitsInStock;
	public TextView tvUnitsOnOrder;
	public TextView tvReorderLevel;
	public TextView tvDiscontinued;

    public ProductsListViewHolder(View itemView) {
        super(itemView);
		tvProductId = (TextView)itemView.findViewById(R.id.tvProductId);
		tvProductName = (TextView)itemView.findViewById(R.id.tvProductName);
		tvSupplierID = (TextView)itemView.findViewById(R.id.tvSupplierID);
		tvCategoryID = (TextView)itemView.findViewById(R.id.tvCategoryID);
		tvQuantityPerUnit = (TextView)itemView.findViewById(R.id.tvQuantityPerUnit);
		tvUnitPrice = (TextView)itemView.findViewById(R.id.tvUnitPrice);
		tvUnitsInStock = (TextView)itemView.findViewById(R.id.tvUnitsInStock);
		tvUnitsOnOrder = (TextView)itemView.findViewById(R.id.tvUnitsOnOrder);
		tvReorderLevel = (TextView)itemView.findViewById(R.id.tvReorderLevel);
		tvDiscontinued = (TextView)itemView.findViewById(R.id.tvDiscontinued);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
