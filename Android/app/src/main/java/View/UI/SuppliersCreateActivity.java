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

import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.SuppliersRepository;
import Util.SessionManager;
import Util.Validation;

public class SuppliersCreateActivity extends AppCompatActivity {

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
	EditText etHomePage;
    Button btnCreate;
    private SessionManager session;
    private String access_token;
    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_create);
        getSupportActionBar().setTitle("Suppliers Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
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
		etHomePage = (EditText) findViewById(R.id.etHomePage);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    SuppliersDto model = new SuppliersDto();
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
					model.setHomePage(new String(etHomePage.getText().toString()));
                    CreateSuppliers(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etCompanyName.getText().toString(), false, "String", "40")) {
			etCompanyName.requestFocus();
			etCompanyName.setError(validation.Message);
			result = false;
		}
		else {
			etCompanyName.clearFocus();
			etCompanyName.setError(null);
		}
		if(!validation.isValid("MaxLength", etContactName.getText().toString(), true, "String", "30")) {
			etContactName.requestFocus();
			etContactName.setError(validation.Message);
			result = false;
		}
		else {
			etContactName.clearFocus();
			etContactName.setError(null);
		}
		if(!validation.isValid("StringLength", etContactTitle.getText().toString(), true, "String", "30")) {
			etContactTitle.requestFocus();
			etContactTitle.setError(validation.Message);
			result = false;
		}
		else {
			etContactTitle.clearFocus();
			etContactTitle.setError(null);
		}
		if(!validation.isValid("MaxLength", etAddress.getText().toString(), true, "String", "60")) {
			etAddress.requestFocus();
			etAddress.setError(validation.Message);
			result = false;
		}
		else {
			etAddress.clearFocus();
			etAddress.setError(null);
		}
		if(!validation.isValid("MaxLength", etCity.getText().toString(), true, "String", "15")) {
			etCity.requestFocus();
			etCity.setError(validation.Message);
			result = false;
		}
		else {
			etCity.clearFocus();
			etCity.setError(null);
		}
		if(!validation.isValid("MaxLength", etRegion.getText().toString(), true, "String", "15")) {
			etRegion.requestFocus();
			etRegion.setError(validation.Message);
			result = false;
		}
		else {
			etRegion.clearFocus();
			etRegion.setError(null);
		}
		if(!validation.isValid("MaxLength", etPostalCode.getText().toString(), true, "String", "10")) {
			etPostalCode.requestFocus();
			etPostalCode.setError(validation.Message);
			result = false;
		}
		else {
			etPostalCode.clearFocus();
			etPostalCode.setError(null);
		}
		if(!validation.isValid("MaxLength", etCountry.getText().toString(), true, "String", "15")) {
			etCountry.requestFocus();
			etCountry.setError(validation.Message);
			result = false;
		}
		else {
			etCountry.clearFocus();
			etCountry.setError(null);
		}
		if(!validation.isValid("Phone", etPhone.getText().toString(), true, "String", "24")) {
			etPhone.requestFocus();
			etPhone.setError(validation.Message);
			result = false;
		}
		else {
			etPhone.clearFocus();
			etPhone.setError(null);
		}
		if(!validation.isValid("Phone", etFax.getText().toString(), true, "String", "24")) {
			etFax.requestFocus();
			etFax.setError(validation.Message);
			result = false;
		}
		else {
			etFax.clearFocus();
			etFax.setError(null);
		}
		if(!validation.isValid("MaxLength", etHomePage.getText().toString(), true, "String", "2000")) {
			etHomePage.requestFocus();
			etHomePage.setError(validation.Message);
			result = false;
		}
		else {
			etHomePage.clearFocus();
			etHomePage.setError(null);
		}

		return result;
	}

    void CreateSuppliers(SuppliersDto item) {
        SuppliersRepository _SuppliersRepository = new SuppliersRepository(access_token);
        if (_SuppliersRepository.Post(item) > 0) {
            Intent intSuppliersList = new Intent(getApplicationContext(), SuppliersListActivity.class);
            startActivity(intSuppliersList);
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
