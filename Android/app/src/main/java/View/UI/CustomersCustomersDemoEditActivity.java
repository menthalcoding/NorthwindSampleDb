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

import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersCustomersDemoRepository;
import HttpClient.Repositories.CustomersRepository;
import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Repositories.CustomersDemographicsRepository;
import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class CustomersCustomersDemoEditActivity extends AppCompatActivity {
	TextView tvCustomerID;
	TextView tvCustomersTypeId;
	Spinner spnCustomersCustomerID;
	Spinner spnCustomersDemographicsCustomersTypeId;
	ArrayAdapter<String> CustomersCustomerIDAdapter;
	ArrayAdapter<String> CustomersDemographicsCustomersTypeIdAdapter;
	List<CustomersDto> _CustomersList;
	List<CustomersDemographicsDto> _CustomersDemographicsList;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    CustomersCustomersDemoRepository _CustomersCustomersDemoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerscustomersdemo_edit);
        getSupportActionBar().setTitle("CustomersCustomersDemo Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

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
		GetCustomersList();
		GetCustomersDemographicsList();
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    CustomersCustomersDemoDto model = new CustomersCustomersDemoDto();
					model.setCustomerID(new String(tvCustomerID.getText().toString()));
					model.setCustomersTypeId(new String(tvCustomersTypeId.getText().toString()));
                    EditCustomersCustomersDemo(model);
                }
            }
        });

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

	boolean IsValid() {
		boolean result = true;

		return result;
	}

    void EditCustomersCustomersDemo(CustomersCustomersDemoDto item) {
        if (item != null) {
            _CustomersCustomersDemoRepository.Put(item);
            Intent intCustomersCustomersDemoList = new Intent(getApplicationContext(), CustomersCustomersDemoListActivity.class);
            startActivity(intCustomersCustomersDemoList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetCustomersList() {
		CustomersRepository _CustomersRepository = new CustomersRepository(access_token);
		_CustomersList =  _CustomersRepository.GetList(new Criteria());
		String[] arrCustomersCustomerID = new String[_CustomersList.size() + 1];
		arrCustomersCustomerID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _CustomersList.size(); e++) {
			arrCustomersCustomerID[e + 1] = String.valueOf(_CustomersList.get(e).getCompanyName());
		}
		spnCustomersCustomerID = (Spinner) findViewById(R.id.spnCustomersCustomerID);
		CustomersCustomerIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCustomersCustomerID);
		CustomersCustomerIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCustomersCustomerID.setPrompt("Please Select!");
		spnCustomersCustomerID.setAdapter(CustomersCustomerIDAdapter);
	}

	void GetCustomersDemographicsList() {
		CustomersDemographicsRepository _CustomersDemographicsRepository = new CustomersDemographicsRepository(access_token);
		_CustomersDemographicsList =  _CustomersDemographicsRepository.GetList(new Criteria());
		String[] arrCustomersDemographicsCustomersTypeId = new String[_CustomersDemographicsList.size() + 1];
		arrCustomersDemographicsCustomersTypeId[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _CustomersDemographicsList.size(); e++) {
			arrCustomersDemographicsCustomersTypeId[e + 1] = String.valueOf(_CustomersDemographicsList.get(e).getCustomersTypeId());
		}
		spnCustomersDemographicsCustomersTypeId = (Spinner) findViewById(R.id.spnCustomersDemographicsCustomersTypeId);
		CustomersDemographicsCustomersTypeIdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCustomersDemographicsCustomersTypeId);
		CustomersDemographicsCustomersTypeIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCustomersDemographicsCustomersTypeId.setPrompt("Please Select!");
		spnCustomersDemographicsCustomersTypeId.setAdapter(CustomersDemographicsCustomersTypeIdAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
