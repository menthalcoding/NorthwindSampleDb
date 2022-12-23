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
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class RegionEditActivity extends AppCompatActivity {
	TextView tvRegionID;
	EditText etRegionDescription;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    RegionRepository _RegionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_edit);
        getSupportActionBar().setTitle("Region Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _RegionRepository = new RegionRepository(access_token);

		tvRegionID = (TextView) findViewById(R.id.tvRegionID);
		etRegionDescription = (EditText) findViewById(R.id.etRegionDescription);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    RegionDto model = new RegionDto();
					model.setRegionID(new Integer(tvRegionID.getText().toString()));
					model.setRegionDescription(new String(etRegionDescription.getText().toString()));
                    EditRegion(model);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyRegionID = (Integer)extras.get("RegionID");
            GetRegion(keyRegionID);
        }
    }

    private void GetRegion(Integer RegionID) {
        RegionCriteria c = new RegionCriteria();
		c.RegionID = RegionID;
        RegionDto data = _RegionRepository.Get(c);
        if (data != null) {
			tvRegionID.setText(String.valueOf(data.getRegionID()));
			etRegionDescription.setText(String.valueOf(data.getRegionDescription()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
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

    void EditRegion(RegionDto item) {
        if (item != null) {
            _RegionRepository.Put(item);
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
