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

import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CategoriesRepository;
import Util.Converter;
import Util.SessionManager;

public class CategoriesDeleteActivity extends AppCompatActivity {
	TextView tvCategoryID;
	TextView tvCategoryName;
	TextView tvDescription;
	TextView tvPicture;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    CategoriesRepository _CategoriesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_delete);
        getSupportActionBar().setTitle("Categories Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _CategoriesRepository = new CategoriesRepository(access_token);

		tvCategoryID = (TextView) findViewById(R.id.tvCategoryID);
		tvCategoryName = (TextView) findViewById(R.id.tvCategoryName);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvPicture = (TextView) findViewById(R.id.tvPicture);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCategories(new Integer(tvCategoryID.getText().toString()));
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
			tvCategoryName.setText(String.valueOf(data.getCategoryName()));
			tvDescription.setText(String.valueOf(data.getDescription()));
			tvPicture.setText(String.valueOf(data.getPicture()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteCategories(Integer CategoryID) {
        CategoriesCriteria criteria = new CategoriesCriteria();
		criteria.CategoryID = CategoryID;
        _CategoriesRepository.Delete(criteria);

        Intent intCategoriesList = new Intent(getApplicationContext(), CategoriesListActivity.class);
        startActivity(intCategoriesList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}