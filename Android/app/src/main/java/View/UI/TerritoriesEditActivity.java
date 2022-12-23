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
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class TerritoriesEditActivity extends AppCompatActivity {
	TextView tvTerritoryID;
	EditText etTerritoryDescription;
	Spinner spnRegionRegionID;
	ArrayAdapter<String> RegionRegionIDAdapter;
	List<RegionDto> _RegionList;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    TerritoriesRepository _TerritoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_territories_edit);
        getSupportActionBar().setTitle("Territories Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _TerritoriesRepository = new TerritoriesRepository(access_token);

		tvTerritoryID = (TextView) findViewById(R.id.tvTerritoryID);
		etTerritoryDescription = (EditText) findViewById(R.id.etTerritoryDescription);
		GetRegionList();
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    TerritoriesDto model = new TerritoriesDto();
					model.setTerritoryID(new String(tvTerritoryID.getText().toString()));
					model.setTerritoryDescription(new String(etTerritoryDescription.getText().toString()));
					if (spnRegionRegionID.getSelectedItemPosition() > 0)
						model.setRegionID(_RegionList.get(spnRegionRegionID.getSelectedItemPosition() - 1).getRegionID());
                    EditTerritories(model);
                }
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
			etTerritoryDescription.setText(String.valueOf(data.getTerritoryDescription()));
			String selValRegionID = "";
            for (RegionDto item : _RegionList) {
				if (item.getRegionID().equals(data.getRegionID())) {
					selValRegionID = String.valueOf(item.getRegionDescription());
			        spnRegionRegionID.setSelection(RegionRegionIDAdapter.getPosition(selValRegionID));
                    break;
                }
			}
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
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

    void EditTerritories(TerritoriesDto item) {
        if (item != null) {
            _TerritoriesRepository.Put(item);
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
