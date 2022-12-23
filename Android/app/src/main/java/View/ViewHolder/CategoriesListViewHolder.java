package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class CategoriesListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvCategoryID;
	public TextView tvCategoryName;
	public TextView tvDescription;
	public TextView tvPicture;

    public CategoriesListViewHolder(View itemView) {
        super(itemView);
		tvCategoryID = (TextView)itemView.findViewById(R.id.tvCategoryID);
		tvCategoryName = (TextView)itemView.findViewById(R.id.tvCategoryName);
		tvDescription = (TextView)itemView.findViewById(R.id.tvDescription);
		tvPicture = (TextView)itemView.findViewById(R.id.tvPicture);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
