package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class ShippersListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvShipperId;
	public TextView tvCompanyName;
	public TextView tvPhone;

    public ShippersListViewHolder(View itemView) {
        super(itemView);
		tvShipperId = (TextView)itemView.findViewById(R.id.tvShipperId);
		tvCompanyName = (TextView)itemView.findViewById(R.id.tvCompanyName);
		tvPhone = (TextView)itemView.findViewById(R.id.tvPhone);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
