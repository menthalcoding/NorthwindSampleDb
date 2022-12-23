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

import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersCustomersDemoRepository;
import Util.Converter;
import Util.SessionManager;

public class CustomersCustomersDemoDetailActivity extends AppCompatActivity {
	TextView tvCustomerID;
	TextView tvCustomersTypeId;
    private SessionManager session;
    private String access_token;
    CustomersCustomersDemoRepository _CustomersCustomersDemoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerscustomersdemo_detail);
        getSupportActionBar().setTitle("CustomersCustomersDemo Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CustomersCustomersDemoRepository = new CustomersCustomersDemoRepository(access_token);

		tvCustomerID = (TextView) findViewById(R.id.tvCustomerID);
		tvCustomersTypeId = (TextView) findViewById(R.id.tvCustomersTypeId);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			String keyCustomerID = (String)extras.get("CustomerID");
			String keyCustomersTypeId = (String)extras.get("CustomersTypeId");
            GetCustomersCustomersDemo(keyCustomerID, keyCustomersTypeId);
        }
    }

    private void GetCustomersCustomersDemo(String CustomerID, String CustomersTypeId) {
        CustomersCustomersDemoCriteria c = new CustomersCustomersDemoCriteria();
		c.CustomerID = CustomerID;
		c.CustomersTypeId = CustomersTypeId;
        CustomersCustomersDemoDto data = _CustomersCustomersDemoRepository.Get(c);
        if (data != null) {
			tvCustomerID.setText(String.valueOf(data.getCustomerID()));
			tvCustomersTypeId.setText(String.valueOf(data.getCustomersTypeId()));
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