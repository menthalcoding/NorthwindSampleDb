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

import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.OrderRepository;
import Util.Converter;
import Util.SessionManager;

public class OrderDeleteActivity extends AppCompatActivity {
	TextView tvOrderId;
	TextView tvCustomerID;
	TextView tvEmployeeID;
	TextView tvOrderDate;
	TextView tvRequiredDate;
	TextView tvShippedDate;
	TextView tvShipName;
	TextView tvShipVia;
	TextView tvFreight;
	TextView tvShipAddress;
	TextView tvShipCity;
	TextView tvShipRegion;
	TextView tvShipPostalCode;
	TextView tvShipCountry;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    OrderRepository _OrderRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_delete);
        getSupportActionBar().setTitle("Order Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _OrderRepository = new OrderRepository(access_token);

		tvOrderId = (TextView) findViewById(R.id.tvOrderId);
		tvCustomerID = (TextView) findViewById(R.id.tvCustomerID);
		tvEmployeeID = (TextView) findViewById(R.id.tvEmployeeID);
		tvOrderDate = (TextView) findViewById(R.id.tvOrderDate);
		tvRequiredDate = (TextView) findViewById(R.id.tvRequiredDate);
		tvShippedDate = (TextView) findViewById(R.id.tvShippedDate);
		tvShipName = (TextView) findViewById(R.id.tvShipName);
		tvShipVia = (TextView) findViewById(R.id.tvShipVia);
		tvFreight = (TextView) findViewById(R.id.tvFreight);
		tvShipAddress = (TextView) findViewById(R.id.tvShipAddress);
		tvShipCity = (TextView) findViewById(R.id.tvShipCity);
		tvShipRegion = (TextView) findViewById(R.id.tvShipRegion);
		tvShipPostalCode = (TextView) findViewById(R.id.tvShipPostalCode);
		tvShipCountry = (TextView) findViewById(R.id.tvShipCountry);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteOrder(new Integer(tvOrderId.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyOrderId = (Integer)extras.get("OrderId");
            GetOrder(keyOrderId);
        }
    }

    private void GetOrder(Integer OrderId) {
        OrderCriteria c = new OrderCriteria();
		c.OrderId = OrderId;
        OrderDto data = _OrderRepository.Get(c);
        if (data != null) {
			tvOrderId.setText(String.valueOf(data.getOrderId()));
			tvCustomerID.setText(String.valueOf(data.getCustomerID()));
			tvEmployeeID.setText(String.valueOf(data.getEmployeeID()));
			tvOrderDate.setText((data.getOrderDate() != null ? Converter.formatDate(data.getOrderDate()) : ""));
			tvRequiredDate.setText((data.getRequiredDate() != null ? Converter.formatDate(data.getRequiredDate()) : ""));
			tvShippedDate.setText((data.getShippedDate() != null ? Converter.formatDate(data.getShippedDate()) : ""));
			tvShipName.setText(String.valueOf(data.getShipName()));
			tvShipVia.setText(String.valueOf(data.getShipVia()));
			tvFreight.setText(String.valueOf(data.getFreight()));
			tvShipAddress.setText(String.valueOf(data.getShipAddress()));
			tvShipCity.setText(String.valueOf(data.getShipCity()));
			tvShipRegion.setText(String.valueOf(data.getShipRegion()));
			tvShipPostalCode.setText(String.valueOf(data.getShipPostalCode()));
			tvShipCountry.setText(String.valueOf(data.getShipCountry()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteOrder(Integer OrderId) {
        OrderCriteria criteria = new OrderCriteria();
		criteria.OrderId = OrderId;
        _OrderRepository.Delete(criteria);

        Intent intOrderList = new Intent(getApplicationContext(), OrderListActivity.class);
        startActivity(intOrderList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}