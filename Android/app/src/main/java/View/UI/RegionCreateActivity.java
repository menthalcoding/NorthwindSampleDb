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

import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.RegionRepository;
import Util.SessionManager;
import Util.Validation;

public class RegionCreateActivity extends AppCompatActivity {

	EditText etRegionDescription;
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
        setContentView(R.layout.activity_region_create);
        getSupportActionBar().setTitle("Region Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etRegionDescription = (EditText) findViewById(R.id.etRegionDescription);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    RegionDto model = new RegionDto();
					model.setRegionDescription(new String(etRegionDescription.getText().toString()));
                    CreateRegion(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etRegionDescription.getText().toString(), false, "String", "50")) {
			etRegionDescription.requestFocus();
			etRegionDescription.setError(validation.Message);
			result = false;
		}
		else {
			etRegionDescription.clearFocus();
			etRegionDescription.setError(null);
		}

		return result;
	}

    void CreateRegion(RegionDto item) {
        RegionRepository _RegionRepository = new RegionRepository(access_token);
        if (_RegionRepository.Post(item) > 0) {
            Intent intRegionList = new Intent(getApplicationContext(), RegionListActivity.class);
            startActivity(intRegionList);
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
