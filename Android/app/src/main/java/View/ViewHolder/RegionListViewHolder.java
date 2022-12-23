package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class RegionListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvRegionID;
	public TextView tvRegionDescription;

    public RegionListViewHolder(View itemView) {
        super(itemView);
		tvRegionID = (TextView)itemView.findViewById(R.id.tvRegionID);
		tvRegionDescription = (TextView)itemView.findViewById(R.id.tvRegionDescription);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
