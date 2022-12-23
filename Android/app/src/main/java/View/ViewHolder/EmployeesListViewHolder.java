package View.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.northwindsampledb.R;

public class EmployeesListViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout llContainer;
	public TextView tvEmployeeID;
	public TextView tvLastName;
	public TextView tvFirstName;
	public TextView tvTitle;
	public TextView tvTitleOfCourtesy;
	public TextView tvBirthDate;
	public TextView tvHireDate;
	public TextView tvAddress;
	public TextView tvCity;
	public TextView tvRegion;
	public TextView tvPostalCode;
	public TextView tvCountry;
	public TextView tvHomePhone;
	public TextView tvExtension;
	public TextView tvPhoto;
	public TextView tvNotes;
	public TextView tvReportsTo;
	public TextView tvPhotoPath;

    public EmployeesListViewHolder(View itemView) {
        super(itemView);
		tvEmployeeID = (TextView)itemView.findViewById(R.id.tvEmployeeID);
		tvLastName = (TextView)itemView.findViewById(R.id.tvLastName);
		tvFirstName = (TextView)itemView.findViewById(R.id.tvFirstName);
		tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
		tvTitleOfCourtesy = (TextView)itemView.findViewById(R.id.tvTitleOfCourtesy);
		tvBirthDate = (TextView)itemView.findViewById(R.id.tvBirthDate);
		tvHireDate = (TextView)itemView.findViewById(R.id.tvHireDate);
		tvAddress = (TextView)itemView.findViewById(R.id.tvAddress);
		tvCity = (TextView)itemView.findViewById(R.id.tvCity);
		tvRegion = (TextView)itemView.findViewById(R.id.tvRegion);
		tvPostalCode = (TextView)itemView.findViewById(R.id.tvPostalCode);
		tvCountry = (TextView)itemView.findViewById(R.id.tvCountry);
		tvHomePhone = (TextView)itemView.findViewById(R.id.tvHomePhone);
		tvExtension = (TextView)itemView.findViewById(R.id.tvExtension);
		tvPhoto = (TextView)itemView.findViewById(R.id.tvPhoto);
		tvNotes = (TextView)itemView.findViewById(R.id.tvNotes);
		tvReportsTo = (TextView)itemView.findViewById(R.id.tvReportsTo);
		tvPhotoPath = (TextView)itemView.findViewById(R.id.tvPhotoPath);
        llContainer = (LinearLayout) itemView.findViewById(R.id.llContainer);
    }
}
