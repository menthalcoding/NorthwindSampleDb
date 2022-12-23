package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class TerritoriesListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvTerritoryID;
	public TextView tvTerritoryDescription;
	public TextView tvRegionID;

    public TerritoriesListViewHolder(View itemView) {
        super(itemView);
		tvTerritoryID = (TextView)itemView.findViewById(R.id.tvTerritoryID);
		tvTerritoryDescription = (TextView)itemView.findViewById(R.id.tvTerritoryDescription);
		tvRegionID = (TextView)itemView.findViewById(R.id.tvRegionID);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
