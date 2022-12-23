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

import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.ShippersRepository;
import Util.Converter;
import Util.SessionManager;

public class ShippersDetailActivity extends AppCompatActivity {
	TextView tvShipperId;
	TextView tvCompanyName;
	TextView tvPhone;
    private SessionManager session;
    private String access_token;
    ShippersRepository _ShippersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shippers_detail);
        getSupportActionBar().setTitle("Shippers Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _ShippersRepository = new ShippersRepository(access_token);

		tvShipperId = (TextView) findViewById(R.id.tvShipperId);
		tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
		tvPhone = (TextView) findViewById(R.id.tvPhone);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyShipperId = (Integer)extras.get("ShipperId");
            GetShippers(keyShipperId);
        }
    }

    private void GetShippers(Integer ShipperId) {
        ShippersCriteria c = new ShippersCriteria();
		c.ShipperId = ShipperId;
        ShippersDto data = _ShippersRepository.Get(c);
        if (data != null) {
			tvShipperId.setText(String.valueOf(data.getShipperId()));
			tvCompanyName.setText(String.valueOf(data.getCompanyName()));
			tvPhone.setText(String.valueOf(data.getPhone()));
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