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

import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.TerritoriesRepository;
import Util.Converter;
import Util.SessionManager;

public class TerritoriesDeleteActivity extends AppCompatActivity {
	TextView tvTerritoryID;
	TextView tvTerritoryDescription;
	TextView tvRegionID;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    TerritoriesRepository _TerritoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_territories_delete);
        getSupportActionBar().setTitle("Territories Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _TerritoriesRepository = new TerritoriesRepository(access_token);

		tvTerritoryID = (TextView) findViewById(R.id.tvTerritoryID);
		tvTerritoryDescription = (TextView) findViewById(R.id.tvTerritoryDescription);
		tvRegionID = (TextView) findViewById(R.id.tvRegionID);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteTerritories(new String(tvTerritoryID.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			String keyTerritoryID = (String)extras.get("TerritoryID");
            GetTerritories(keyTerritoryID);
        }
    }

    private void GetTerritories(String TerritoryID) {
        TerritoriesCriteria c = new TerritoriesCriteria();
		c.TerritoryID = TerritoryID;
        TerritoriesDto data = _TerritoriesRepository.Get(c);
        if (data != null) {
			tvTerritoryID.setText(String.valueOf(data.getTerritoryID()));
			tvTerritoryDescription.setText(String.valueOf(data.getTerritoryDescription()));
			tvRegionID.setText(String.valueOf(data.getRegionID()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteTerritories(String TerritoryID) {
        TerritoriesCriteria criteria = new TerritoriesCriteria();
		criteria.TerritoryID = TerritoryID;
        _TerritoriesRepository.Delete(criteria);

        Intent intTerritoriesList = new Intent(getApplicationContext(), TerritoriesListActivity.class);
        startActivity(intTerritoriesList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}