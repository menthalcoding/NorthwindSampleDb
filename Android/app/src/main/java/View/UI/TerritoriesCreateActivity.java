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

import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.TerritoriesRepository;
import HttpClient.Repositories.RegionRepository;
import HttpClient.DataTransferObjects.RegionDto;
import Util.SessionManager;
import Util.Validation;

public class TerritoriesCreateActivity extends AppCompatActivity {

	EditText etTerritoryDescription;
	Spinner spnRegionRegionID;
	ArrayAdapter<String> RegionRegionIDAdapter;
	List<RegionDto> _RegionList;
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
        setContentView(R.layout.activity_territories_create);
        getSupportActionBar().setTitle("Territories Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etTerritoryDescription = (EditText) findViewById(R.id.etTerritoryDescription);
		GetRegionList();
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    TerritoriesDto model = new TerritoriesDto();
					model.setTerritoryDescription(new String(etTerritoryDescription.getText().toString()));
					if (spnRegionRegionID.getSelectedItemPosition() > 0)
						model.setRegionID(_RegionList.get(spnRegionRegionID.getSelectedItemPosition() - 1).getRegionID());
                    CreateTerritories(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etTerritoryDescription.getText().toString(), false, "String", "50")) {
			etTerritoryDescription.requestFocus();
			etTerritoryDescription.setError(validation.Message);
			result = false;
		}
		else {
			etTerritoryDescription.clearFocus();
			etTerritoryDescription.setError(null);
		}
		if(!validation.isValid("Required", (spnRegionRegionID.getSelectedItemPosition() > 0 ? String.valueOf(_RegionList.get(spnRegionRegionID.getSelectedItemPosition() - 1).getRegionID()) : null), false, "Integer", null)) {
			spnRegionRegionID.setFocusable(true);
			spnRegionRegionID.setFocusableInTouchMode(true);
			spnRegionRegionID.requestFocus();
			((TextView)spnRegionRegionID.getSelectedView()).setError(validation.Message);
			result = false;
		}
		else {
			spnRegionRegionID.clearFocus();
			((TextView)spnRegionRegionID.getSelectedView()).setError(null);
		}

		return result;
	}

    void CreateTerritories(TerritoriesDto item) {
        TerritoriesRepository _TerritoriesRepository = new TerritoriesRepository(access_token);
        if (_TerritoriesRepository.Post(item) > 0) {
            Intent intTerritoriesList = new Intent(getApplicationContext(), TerritoriesListActivity.class);
            startActivity(intTerritoriesList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetRegionList() {
		RegionRepository _RegionRepository = new RegionRepository(access_token);
		_RegionList =  _RegionRepository.GetList(new Criteria());
		String[] arrRegionRegionID = new String[_RegionList.size() + 1];
		arrRegionRegionID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _RegionList.size(); e++) {
			arrRegionRegionID[e + 1] = String.valueOf(_RegionList.get(e).getRegionDescription());
		}
		spnRegionRegionID = (Spinner) findViewById(R.id.spnRegionRegionID);
		RegionRegionIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrRegionRegionID);
		RegionRegionIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnRegionRegionID.setPrompt("Please Select!");
		spnRegionRegionID.setAdapter(RegionRegionIDAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
