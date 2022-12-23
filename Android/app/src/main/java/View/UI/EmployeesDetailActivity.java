package View.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.math.BigDecimal;

import androidx.appcompat.app.AppCompatActivity;

import com.example.northwindsampledb.R;

import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.EmployeesRepository;
import Util.Converter;
import Util.SessionManager;

public class EmployeesDetailActivity extends AppCompatActivity {
	TextView tvEmployeeID;
	TextView tvLastName;
	TextView tvFirstName;
	TextView tvTitle;
	TextView tvTitleOfCourtesy;
	TextView tvBirthDate;
	TextView tvHireDate;
	TextView tvAddress;
	TextView tvCity;
	TextView tvRegion;
	TextView tvPostalCode;
	TextView tvCountry;
	TextView tvHomePhone;
	TextView tvExtension;
	TextView tvPhoto;
	TextView tvNotes;
	TextView tvReportsTo;
	TextView tvPhotoPath;
    private SessionManager session;
    private String access_token;
    EmployeesRepository _EmployeesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_detail);
        getSupportActionBar().setTitle("Employees Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _EmployeesRepository = new EmployeesRepository(access_token);

		tvEmployeeID = (TextView) findViewById(R.id.tvEmployeeID);
		tvLastName = (TextView) findViewById(R.id.tvLastName);
		tvFirstName = (TextView) findViewById(R.id.tvFirstName);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvTitleOfCourtesy = (TextView) findViewById(R.id.tvTitleOfCourtesy);
		tvBirthDate = (TextView) findViewById(R.id.tvBirthDate);
		tvHireDate = (TextView) findViewById(R.id.tvHireDate);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		tvCity = (TextView) findViewById(R.id.tvCity);
		tvRegion = (TextView) findViewById(R.id.tvRegion);
		tvPostalCode = (TextView) findViewById(R.id.tvPostalCode);
		tvCountry = (TextView) findViewById(R.id.tvCountry);
		tvHomePhone = (TextView) findViewById(R.id.tvHomePhone);
		tvExtension = (TextView) findViewById(R.id.tvExtension);
		tvPhoto = (TextView) findViewById(R.id.tvPhoto);
		tvNotes = (TextView) findViewById(R.id.tvNotes);
		tvReportsTo = (TextView) findViewById(R.id.tvReportsTo);
		tvPhotoPath = (TextView) findViewById(R.id.tvPhotoPath);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyEmployeeID = (Integer)extras.get("EmployeeID");
            GetEmployees(keyEmployeeID);
        }
    }

    private void GetEmployees(Integer EmployeeID) {
        EmployeesCriteria c = new EmployeesCriteria();
		c.EmployeeID = EmployeeID;
        EmployeesDto data = _EmployeesRepository.Get(c);
        if (data != null) {
			tvEmployeeID.setText(String.valueOf(data.getEmployeeID()));
			tvLastName.setText(String.valueOf(data.getLastName()));
			tvFirstName.setText(String.valueOf(data.getFirstName()));
			tvTitle.setText(String.valueOf(data.getTitle()));
			tvTitleOfCourtesy.setText(String.valueOf(data.getTitleOfCourtesy()));
			tvBirthDate.setText((data.getBirthDate() != null ? Converter.formatDate(data.getBirthDate()) : ""));
			tvHireDate.setText((data.getHireDate() != null ? Converter.formatDate(data.getHireDate()) : ""));
			tvAddress.setText(String.valueOf(data.getAddress()));
			tvCity.setText(String.valueOf(data.getCity()));
			tvRegion.setText(String.valueOf(data.getRegion()));
			tvPostalCode.setText(String.valueOf(data.getPostalCode()));
			tvCountry.setText(String.valueOf(data.getCountry()));
			tvHomePhone.setText(String.valueOf(data.getHomePhone()));
			tvExtension.setText(String.valueOf(data.getExtension()));
			tvPhoto.setText(String.valueOf(data.getPhoto()));
			tvNotes.setText(String.valueOf(data.getNotes()));
			tvReportsTo.setText(String.valueOf(data.getReportsTo()));
			tvPhotoPath.setText(String.valueOf(data.getPhotoPath()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}