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

import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersRepository;
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class CustomersEditActivity extends AppCompatActivity {
	TextView tvCustomerID;
	EditText etCompanyName;
	EditText etContactName;
	EditText etContactTitle;
	EditText etAddress;
	EditText etCity;
	EditText etRegion;
	EditText etPostalCode;
	EditText etCountry;
	EditText etPhone;
	EditText etFax;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    CustomersRepository _CustomersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_edit);
        getSupportActionBar().setTitle("Customers Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CustomersRepository = new CustomersRepository(access_token);

		tvCustomerID = (TextView) findViewById(R.id.tvCustomerID);
		etCompanyName = (EditText) findViewById(R.id.etCompanyName);
		etContactName = (EditText) findViewById(R.id.etContactName);
		etContactTitle = (EditText) findViewById(R.id.etContactTitle);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etCity = (EditText) findViewById(R.id.etCity);
		etRegion = (EditText) findViewById(R.id.etRegion);
		etPostalCode = (EditText) findViewById(R.id.etPostalCode);
		etCountry = (EditText) findViewById(R.id.etCountry);
		etPhone = (EditText) findViewById(R.id.etPhone);
		etFax = (EditText) findViewById(R.id.etFax);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    CustomersDto model = new CustomersDto();
					model.setCustomerID(new String(tvCustomerID.getText().toString()));
					model.setCompanyName(new String(etCompanyName.getText().toString()));
					model.setContactName(new String(etContactName.getText().toString()));
					model.setContactTitle(new String(etContactTitle.getText().toString()));
					model.setAddress(new String(etAddress.getText().toString()));
					model.setCity(new String(etCity.getText().toString()));
					model.setRegion(new String(etRegion.getText().toString()));
					model.setPostalCode(new String(etPostalCode.getText().toString()));
					model.setCountry(new String(etCountry.getText().toString()));
					model.setPhone(new String(etPhone.getText().toString()));
					model.setFax(new String(etFax.getText().toString()));
                    EditCustomers(model);
                }
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
			etCompanyName.setText(String.valueOf(data.getCompanyName()));
			etContactName.setText(String.valueOf(data.getContactName()));
			etContactTitle.setText(String.valueOf(data.getContactTitle()));
			etAddress.setText(String.valueOf(data.getAddress()));
			etCity.setText(String.valueOf(data.getCity()));
			etRegion.setText(String.valueOf(data.getRegion()));
			etPostalCode.setText(String.valueOf(data.getPostalCode()));
			etCountry.setText(String.valueOf(data.getCountry()));
			etPhone.setText(String.valueOf(data.getPhone()));
			etFax.setText(String.valueOf(data.getFax()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("MaxLength", etCompanyName.getText().toString(), true, "String", "50")) {
			etCompanyName.requestFocus();
			etCompanyName.setError(validation.Message);
			result = false;
		}
		else {
			etCompanyName.clearFocus();
			etCompanyName.setError(null);
		}
		if(!validation.isValid("MaxLength", etContactName.getText().toString(), false, "String", "30")) {
			etContactName.requestFocus();
			etContactName.setError(validation.Message);
			result = false;
		}
		else {
			etContactName.clearFocus();
			etContactName.setError(null);
		}
		if(!validation.isValid("MaxLength", etContactTitle.getText().toString(), false, "String", "30")) {
			etContactTitle.requestFocus();
			etContactTitle.setError(validation.Message);
			result = false;
		}
		else {
			etContactTitle.clearFocus();
			etContactTitle.setError(null);
		}
		if(!validation.isValid("MaxLength", etAddress.getText().toString(), false, "String", "60")) {
			etAddress.requestFocus();
			etAddress.setError(validation.Message);
			result = false;
		}
		else {
			etAddress.clearFocus();
			etAddress.setError(null);
		}
		if(!validation.isValid("MaxLength", etCity.getText().toString(), false, "String", "15")) {
			etCity.requestFocus();
			etCity.setError(validation.Message);
			result = false;
		}
		else {
			etCity.clearFocus();
			etCity.setError(null);
		}
		if(!validation.isValid("MaxLength", etRegion.getText().toString(), false, "String", "15")) {
			etRegion.requestFocus();
			etRegion.setError(validation.Message);
			result = false;
		}
		else {
			etRegion.clearFocus();
			etRegion.setError(null);
		}
		if(!validation.isValid("MaxLength", etPostalCode.getText().toString(), false, "String", "12")) {
			etPostalCode.requestFocus();
			etPostalCode.setError(validation.Message);
			result = false;
		}
		else {
			etPostalCode.clearFocus();
			etPostalCode.setError(null);
		}
		if(!validation.isValid("MaxLength", etCountry.getText().toString(), false, "String", "15")) {
			etCountry.requestFocus();
			etCountry.setError(validation.Message);
			result = false;
		}
		else {
			etCountry.clearFocus();
			etCountry.setError(null);
		}
		if(!validation.isValid("Phone", etPhone.getText().toString(), false, "String", "24")) {
			etPhone.requestFocus();
			etPhone.setError(validation.Message);
			result = false;
		}
		else {
			etPhone.clearFocus();
			etPhone.setError(null);
		}
		if(!validation.isValid("Phone", etFax.getText().toString(), false, "String", "24")) {
			etFax.requestFocus();
			etFax.setError(validation.Message);
			result = false;
		}
		else {
			etFax.clearFocus();
			etFax.setError(null);
		}

		return result;
	}

    void EditCustomers(CustomersDto item) {
        if (item != null) {
            _CustomersRepository.Put(item);
            Intent intCustomersList = new Intent(getApplicationContext(), CustomersListActivity.class);
            startActivity(intCustomersList);
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
