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

import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.RegionRepository;
import Util.Converter;
import Util.SessionManager;

public class RegionDetailActivity extends AppCompatActivity {
	TextView tvRegionID;
	TextView tvRegionDescription;
    private SessionManager session;
    private String access_token;
    RegionRepository _RegionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_detail);
        getSupportActionBar().setTitle("Region Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _RegionRepository = new RegionRepository(access_token);

		tvRegionID = (TextView) findViewById(R.id.tvRegionID);
		tvRegionDescription = (TextView) findViewById(R.id.tvRegionDescription);

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
			tvRegionDescription.setText(String.valueOf(data.getRegionDescription()));
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