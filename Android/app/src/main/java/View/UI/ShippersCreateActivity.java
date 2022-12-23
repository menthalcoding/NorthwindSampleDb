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

import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.ShippersRepository;
import Util.SessionManager;
import Util.Validation;

public class ShippersCreateActivity extends AppCompatActivity {

	EditText etCompanyName;
	EditText etPhone;
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
        setContentView(R.layout.activity_shippers_create);
        getSupportActionBar().setTitle("Shippers Create");
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
		etPhone = (EditText) findViewById(R.id.etPhone);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    ShippersDto model = new ShippersDto();
					model.setCompanyName(new String(etCompanyName.getText().toString()));
					model.setPhone(new String(etPhone.getText().toString()));
                    CreateShippers(model);
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
		if(!validation.isValid("Phone", etPhone.getText().toString(), true, "String", "24")) {
			etPhone.requestFocus();
			etPhone.setError(validation.Message);
			result = false;
		}
		else {
			etPhone.clearFocus();
			etPhone.setError(null);
		}

		return result;
	}

    void CreateShippers(ShippersDto item) {
        ShippersRepository _ShippersRepository = new ShippersRepository(access_token);
        if (_ShippersRepository.Post(item) > 0) {
            Intent intShippersList = new Intent(getApplicationContext(), ShippersListActivity.class);
            startActivity(intShippersList);
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
