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

import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.EmployeesTerritoriesRepository;
import HttpClient.Repositories.EmployeesRepository;
import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Repositories.TerritoriesRepository;
import HttpClient.DataTransferObjects.TerritoriesDto;
import Util.SessionManager;
import Util.Validation;

public class EmployeesTerritoriesCreateActivity extends AppCompatActivity {

	Spinner spnEmployeesEmployeeID;
	Spinner spnTerritoriesTerritoryID;
	ArrayAdapter<String> EmployeesEmployeeIDAdapter;
	ArrayAdapter<String> TerritoriesTerritoryIDAdapter;
	List<EmployeesDto> _EmployeesList;
	List<TerritoriesDto> _TerritoriesList;
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
        setContentView(R.layout.activity_employeesterritories_create);
        getSupportActionBar().setTitle("EmployeesTerritories Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		GetEmployeesList();
		GetTerritoriesList();
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    EmployeesTerritoriesDto model = new EmployeesTerritoriesDto();
                    CreateEmployeesTerritories(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;

		return result;
	}

    void CreateEmployeesTerritories(EmployeesTerritoriesDto item) {
        EmployeesTerritoriesRepository _EmployeesTerritoriesRepository = new EmployeesTerritoriesRepository(access_token);
        if (_EmployeesTerritoriesRepository.Post(item) > 0) {
            Intent intEmployeesTerritoriesList = new Intent(getApplicationContext(), EmployeesTerritoriesListActivity.class);
            startActivity(intEmployeesTerritoriesList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetEmployeesList() {
		EmployeesRepository _EmployeesRepository = new EmployeesRepository(access_token);
		_EmployeesList =  _EmployeesRepository.GetList(new Criteria());
		String[] arrEmployeesEmployeeID = new String[_EmployeesList.size() + 1];
		arrEmployeesEmployeeID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _EmployeesList.size(); e++) {
			arrEmployeesEmployeeID[e + 1] = String.valueOf(_EmployeesList.get(e).getTitle());
		}
		spnEmployeesEmployeeID = (Spinner) findViewById(R.id.spnEmployeesEmployeeID);
		EmployeesEmployeeIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrEmployeesEmployeeID);
		EmployeesEmployeeIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEmployeesEmployeeID.setPrompt("Please Select!");
		spnEmployeesEmployeeID.setAdapter(EmployeesEmployeeIDAdapter);
	}

	void GetTerritoriesList() {
		TerritoriesRepository _TerritoriesRepository = new TerritoriesRepository(access_token);
		_TerritoriesList =  _TerritoriesRepository.GetList(new Criteria());
		String[] arrTerritoriesTerritoryID = new String[_TerritoriesList.size() + 1];
		arrTerritoriesTerritoryID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _TerritoriesList.size(); e++) {
			arrTerritoriesTerritoryID[e + 1] = String.valueOf(_TerritoriesList.get(e).getTerritoryID());
		}
		spnTerritoriesTerritoryID = (Spinner) findViewById(R.id.spnTerritoriesTerritoryID);
		TerritoriesTerritoryIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrTerritoriesTerritoryID);
		TerritoriesTerritoryIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTerritoriesTerritoryID.setPrompt("Please Select!");
		spnTerritoriesTerritoryID.setAdapter(TerritoriesTerritoryIDAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
