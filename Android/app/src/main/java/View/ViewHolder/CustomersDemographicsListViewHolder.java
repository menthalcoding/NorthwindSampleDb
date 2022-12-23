package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class CustomersDemographicsListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvCustomersTypeId;
	public TextView tvCustomersDesc;

    public CustomersDemographicsListViewHolder(View itemView) {
        super(itemView);
		tvCustomersTypeId = (TextView)itemView.findViewById(R.id.tvCustomersTypeId);
		tvCustomersDesc = (TextView)itemView.findViewById(R.id.tvCustomersDesc);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
