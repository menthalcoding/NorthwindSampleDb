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

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersDemographicsRepository;
import Util.Converter;
import Util.SessionManager;

public class CustomersDemographicsDeleteActivity extends AppCompatActivity {
	TextView tvCustomersTypeId;
	TextView tvCustomersDesc;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    CustomersDemographicsRepository _CustomersDemographicsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersdemographics_delete);
        getSupportActionBar().setTitle("CustomersDemographics Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CustomersDemographicsRepository = new CustomersDemographicsRepository(access_token);

		tvCustomersTypeId = (TextView) findViewById(R.id.tvCustomersTypeId);
		tvCustomersDesc = (TextView) findViewById(R.id.tvCustomersDesc);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCustomersDemographics(new String(tvCustomersTypeId.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			String keyCustomersTypeId = (String)extras.get("CustomersTypeId");
            GetCustomersDemographics(keyCustomersTypeId);
        }
    }

    private void GetCustomersDemographics(String CustomersTypeId) {
        CustomersDemographicsCriteria c = new CustomersDemographicsCriteria();
		c.CustomersTypeId = CustomersTypeId;
        CustomersDemographicsDto data = _CustomersDemographicsRepository.Get(c);
        if (data != null) {
			tvCustomersTypeId.setText(String.valueOf(data.getCustomersTypeId()));
			tvCustomersDesc.setText(String.valueOf(data.getCustomersDesc()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteCustomersDemographics(String CustomersTypeId) {
        CustomersDemographicsCriteria criteria = new CustomersDemographicsCriteria();
		criteria.CustomersTypeId = CustomersTypeId;
        _CustomersDemographicsRepository.Delete(criteria);

        Intent intCustomersDemographicsList = new Intent(getApplicationContext(), CustomersDemographicsListActivity.class);
        startActivity(intCustomersDemographicsList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}