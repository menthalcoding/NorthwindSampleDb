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

import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.OrderDetailsRepository;
import Util.Converter;
import Util.SessionManager;

public class OrderDetailsDeleteActivity extends AppCompatActivity {
	TextView tvOrderId;
	TextView tvProductId;
	TextView tvUnitPrice;
	TextView tvQuantity;
	TextView tvDiscount;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    OrderDetailsRepository _OrderDetailsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails_delete);
        getSupportActionBar().setTitle("OrderDetails Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _OrderDetailsRepository = new OrderDetailsRepository(access_token);

		tvOrderId = (TextView) findViewById(R.id.tvOrderId);
		tvProductId = (TextView) findViewById(R.id.tvProductId);
		tvUnitPrice = (TextView) findViewById(R.id.tvUnitPrice);
		tvQuantity = (TextView) findViewById(R.id.tvQuantity);
		tvDiscount = (TextView) findViewById(R.id.tvDiscount);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteOrderDetails(new Integer(tvOrderId.getText().toString()),new Integer(tvProductId.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyOrderId = (Integer)extras.get("OrderId");
			Integer keyProductId = (Integer)extras.get("ProductId");
            GetOrderDetails(keyOrderId, keyProductId);
        }
    }

    private void GetOrderDetails(Integer OrderId, Integer ProductId) {
        OrderDetailsCriteria c = new OrderDetailsCriteria();
		c.OrderId = OrderId;
		c.ProductId = ProductId;
        OrderDetailsDto data = _OrderDetailsRepository.Get(c);
        if (data != null) {
			tvOrderId.setText(String.valueOf(data.getOrderId()));
			tvProductId.setText(String.valueOf(data.getProductId()));
			tvUnitPrice.setText(String.valueOf(data.getUnitPrice()));
			tvQuantity.setText(String.valueOf(data.getQuantity()));
			tvDiscount.setText(String.valueOf(data.getDiscount()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteOrderDetails(Integer OrderId, Integer ProductId) {
        OrderDetailsCriteria criteria = new OrderDetailsCriteria();
		criteria.OrderId = OrderId;
		criteria.ProductId = ProductId;
        _OrderDetailsRepository.Delete(criteria);

        Intent intOrderDetailsList = new Intent(getApplicationContext(), OrderDetailsListActivity.class);
        startActivity(intOrderDetailsList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}