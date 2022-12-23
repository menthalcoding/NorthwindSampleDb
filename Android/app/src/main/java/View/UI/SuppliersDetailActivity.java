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

import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.SuppliersRepository;
import Util.Converter;
import Util.SessionManager;

public class SuppliersDetailActivity extends AppCompatActivity {
	TextView tvSupplierID;
	TextView tvCompanyName;
	TextView tvContactName;
	TextView tvContactTitle;
	TextView tvAddress;
	TextView tvCity;
	TextView tvRegion;
	TextView tvPostalCode;
	TextView tvCountry;
	TextView tvPhone;
	TextView tvFax;
	TextView tvHomePage;
    private SessionManager session;
    private String access_token;
    SuppliersRepository _SuppliersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_detail);
        getSupportActionBar().setTitle("Suppliers Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _SuppliersRepository = new SuppliersRepository(access_token);

		tvSupplierID = (TextView) findViewById(R.id.tvSupplierID);
		tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
		tvContactName = (TextView) findViewById(R.id.tvContactName);
		tvContactTitle = (TextView) findViewById(R.id.tvContactTitle);
		tvAddress = (TextView) findViewById(R.id.tvAddress);
		tvCity = (TextView) findViewById(R.id.tvCity);
		tvRegion = (TextView) findViewById(R.id.tvRegion);
		tvPostalCode = (TextView) findViewById(R.id.tvPostalCode);
		tvCountry = (TextView) findViewById(R.id.tvCountry);
		tvPhone = (TextView) findViewById(R.id.tvPhone);
		tvFax = (TextView) findViewById(R.id.tvFax);
		tvHomePage = (TextView) findViewById(R.id.tvHomePage);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keySupplierID = (Integer)extras.get("SupplierID");
            GetSuppliers(keySupplierID);
        }
    }

    private void GetSuppliers(Integer SupplierID) {
        SuppliersCriteria c = new SuppliersCriteria();
		c.SupplierID = SupplierID;
        SuppliersDto data = _SuppliersRepository.Get(c);
        if (data != null) {
			tvSupplierID.setText(String.valueOf(data.getSupplierID()));
			tvCompanyName.setText(String.valueOf(data.getCompanyName()));
			tvContactName.setText(String.valueOf(data.getContactName()));
			tvContactTitle.setText(String.valueOf(data.getContactTitle()));
			tvAddress.setText(String.valueOf(data.getAddress()));
			tvCity.setText(String.valueOf(data.getCity()));
			tvRegion.setText(String.valueOf(data.getRegion()));
			tvPostalCode.setText(String.valueOf(data.getPostalCode()));
			tvCountry.setText(String.valueOf(data.getCountry()));
			tvPhone.setText(String.valueOf(data.getPhone()));
			tvFax.setText(String.valueOf(data.getFax()));
			tvHomePage.setText(String.valueOf(data.getHomePage()));
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