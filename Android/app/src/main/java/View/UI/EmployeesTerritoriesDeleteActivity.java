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

import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.EmployeesTerritoriesRepository;
import Util.Converter;
import Util.SessionManager;

public class EmployeesTerritoriesDeleteActivity extends AppCompatActivity {
	TextView tvEmpleymontId;
	TextView tvTerritoryID;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    EmployeesTerritoriesRepository _EmployeesTerritoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeesterritories_delete);
        getSupportActionBar().setTitle("EmployeesTerritories Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _EmployeesTerritoriesRepository = new EmployeesTerritoriesRepository(access_token);

		tvEmpleymontId = (TextView) findViewById(R.id.tvEmpleymontId);
		tvTerritoryID = (TextView) findViewById(R.id.tvTerritoryID);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteEmployeesTerritories(new Integer(tvEmpleymontId.getText().toString()),new String(tvTerritoryID.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyEmpleymontId = (Integer)extras.get("EmpleymontId");
			String keyTerritoryID = (String)extras.get("TerritoryID");
            GetEmployeesTerritories(keyEmpleymontId, keyTerritoryID);
        }
    }

    private void GetEmployeesTerritories(Integer EmpleymontId, String TerritoryID) {
        EmployeesTerritoriesCriteria c = new EmployeesTerritoriesCriteria();
		c.EmpleymontId = EmpleymontId;
		c.TerritoryID = TerritoryID;
        EmployeesTerritoriesDto data = _EmployeesTerritoriesRepository.Get(c);
        if (data != null) {
			tvEmpleymontId.setText(String.valueOf(data.getEmpleymontId()));
			tvTerritoryID.setText(String.valueOf(data.getTerritoryID()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteEmployeesTerritories(Integer EmpleymontId, String TerritoryID) {
        EmployeesTerritoriesCriteria criteria = new EmployeesTerritoriesCriteria();
		criteria.EmpleymontId = EmpleymontId;
		criteria.TerritoryID = TerritoryID;
        _EmployeesTerritoriesRepository.Delete(criteria);

        Intent intEmployeesTerritoriesList = new Intent(getApplicationContext(), EmployeesTerritoriesListActivity.class);
        startActivity(intEmployeesTerritoriesList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}