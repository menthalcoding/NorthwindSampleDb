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
import Util.SessionManager;
import Util.Validation;

public class CustomersDemographicsCreateActivity extends AppCompatActivity {

	EditText etCustomersDesc;
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
        setContentView(R.layout.activity_customersdemographics_create);
        getSupportActionBar().setTitle("CustomersDemographics Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etCustomersDesc = (EditText) findViewById(R.id.etCustomersDesc);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    CustomersDemographicsDto model = new CustomersDemographicsDto();
					model.setCustomersDesc(new String(etCustomersDesc.getText().toString()));
                    CreateCustomersDemographics(model);
                }
            }
        });
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

    void CreateCustomersDemographics(CustomersDemographicsDto item) {
        CustomersDemographicsRepository _CustomersDemographicsRepository = new CustomersDemographicsRepository(access_token);
        if (_CustomersDemographicsRepository.Post(item) > 0) {
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
