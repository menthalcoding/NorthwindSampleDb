package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class EmployeesTerritoriesListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvEmpleymontId;
	public TextView tvTerritoryID;

    public EmployeesTerritoriesListViewHolder(View itemView) {
        super(itemView);
		tvEmpleymontId = (TextView)itemView.findViewById(R.id.tvEmpleymontId);
		tvTerritoryID = (TextView)itemView.findViewById(R.id.tvTerritoryID);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
