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

import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersRepository;
import Util.Converter;
import Util.SessionManager;

public class CustomersDeleteActivity extends AppCompatActivity {
	TextView tvCustomerID;
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
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    CustomersRepository _CustomersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_delete);
        getSupportActionBar().setTitle("Customers Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CustomersRepository = new CustomersRepository(access_token);

		tvCustomerID = (TextView) findViewById(R.id.tvCustomerID);
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
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCustomers(new String(tvCustomerID.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			String keyCustomerID = (String)extras.get("CustomerID");
            GetCustomers(keyCustomerID);
        }
    }

    private void GetCustomers(String CustomerID) {
        CustomersCriteria c = new CustomersCriteria();
		c.CustomerID = CustomerID;
        CustomersDto data = _CustomersRepository.Get(c);
        if (data != null) {
			tvCustomerID.setText(String.valueOf(data.getCustomerID()));
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
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteCustomers(String CustomerID) {
        CustomersCriteria criteria = new CustomersCriteria();
		criteria.CustomerID = CustomerID;
        _CustomersRepository.Delete(criteria);

        Intent intCustomersList = new Intent(getApplicationContext(), CustomersListActivity.class);
        startActivity(intCustomersList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}