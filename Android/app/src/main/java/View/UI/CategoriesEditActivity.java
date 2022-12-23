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

import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CategoriesRepository;
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class CategoriesEditActivity extends AppCompatActivity {
	TextView tvCategoryID;
	EditText etCategoryName;
	EditText etDescription;
	EditText etPicture;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    CategoriesRepository _CategoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_edit);
        getSupportActionBar().setTitle("Categories Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CategoriesRepository = new CategoriesRepository(access_token);

		tvCategoryID = (TextView) findViewById(R.id.tvCategoryID);
		etCategoryName = (EditText) findViewById(R.id.etCategoryName);
		etDescription = (EditText) findViewById(R.id.etDescription);
		etPicture = (EditText) findViewById(R.id.etPicture);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    CategoriesDto model = new CategoriesDto();
					model.setCategoryID(new Integer(tvCategoryID.getText().toString()));
					model.setCategoryName(new String(etCategoryName.getText().toString()));
					model.setDescription(new String(etDescription.getText().toString()));
					model.setPicture(new String(etPicture.getText().toString()));
                    EditCategories(model);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyCategoryID = (Integer)extras.get("CategoryID");
            GetCategories(keyCategoryID);
        }
    }

    private void GetCategories(Integer CategoryID) {
        CategoriesCriteria c = new CategoriesCriteria();
		c.CategoryID = CategoryID;
        CategoriesDto data = _CategoriesRepository.Get(c);
        if (data != null) {
			tvCategoryID.setText(String.valueOf(data.getCategoryID()));
			etCategoryName.setText(String.valueOf(data.getCategoryName()));
			etDescription.setText(String.valueOf(data.getDescription()));
			etPicture.setText(String.valueOf(data.getPicture()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etCategoryName.getText().toString(), false, "String", "15")) {
			etCategoryName.requestFocus();
			etCategoryName.setError(validation.Message);
			result = false;
		}
		else {
			etCategoryName.clearFocus();
			etCategoryName.setError(null);
		}
		if(!validation.isValid("MaxLength", etDescription.getText().toString(), true, "String", "2000")) {
			etDescription.requestFocus();
			etDescription.setError(validation.Message);
			result = false;
		}
		else {
			etDescription.clearFocus();
			etDescription.setError(null);
		}
		if(!validation.isValid("MaxLength", etPicture.getText().toString(), true, "String", "255")) {
			etPicture.requestFocus();
			etPicture.setError(validation.Message);
			result = false;
		}
		else {
			etPicture.clearFocus();
			etPicture.setError(null);
		}

		return result;
	}

    void EditCategories(CategoriesDto item) {
        if (item != null) {
            _CategoriesRepository.Put(item);
            Intent intCategoriesList = new Intent(getApplicationContext(), CategoriesListActivity.class);
            startActivity(intCategoriesList);
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
