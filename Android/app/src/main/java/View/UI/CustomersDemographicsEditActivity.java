package View.UI;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.example.northwindsampledb.R;

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersDemographicsRepository;
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class CustomersDemographicsEditActivity extends AppCompatActivity {
	TextView tvCustomersTypeId;
	EditText etCustomersDesc;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    CustomersDemographicsRepository _CustomersDemographicsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersdemographics_edit);
        getSupportActionBar().setTitle("CustomersDemographics Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CustomersDemographicsRepository = new CustomersDemographicsRepository(access_token);

		tvCustomersTypeId = (TextView) findViewById(R.id.tvCustomersTypeId);
		etCustomersDesc = (EditText) findViewById(R.id.etCustomersDesc);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    CustomersDemographicsDto model = new CustomersDemographicsDto();
					model.setCustomersTypeId(new String(tvCustomersTypeId.getText().toString()));
					model.setCustomersDesc(new String(etCustomersDesc.getText().toString()));
                    EditCustomersDemographics(model);
                }
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
			etCustomersDesc.setText(String.valueOf(data.getCustomersDesc()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("MaxLength", etCustomersDesc.getText().toString(), true, "String", "2000")) {
			etCustomersDesc.requestFocus();
			etCustomersDesc.setError(validation.Message);
			result = false;
		}
		else {
			etCustomersDesc.clearFocus();
			etCustomersDesc.setError(null);
		}

		return result;
	}

    void EditCustomersDemographics(CustomersDemographicsDto item) {
        if (item != null) {
            _CustomersDemographicsRepository.Put(item);
            Intent intCustomersDemographicsList = new Intent(getApplicationContext(), CustomersDemographicsListActivity.class);
            startActivity(intCustomersDemographicsList);
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
