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

import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.OrderDetailsRepository;
import HttpClient.Repositories.OrderRepository;
import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Repositories.ProductsRepository;
import HttpClient.DataTransferObjects.ProductsDto;
import Util.Converter;
import Util.SessionManager;
import Util.Validation;

public class OrderDetailsEditActivity extends AppCompatActivity {
	TextView tvOrderId;
	TextView tvProductId;
	EditText etUnitPrice;
	EditText etQuantity;
	EditText etDiscount;
	Spinner spnOrderOrderId;
	Spinner spnProductsProductId;
	ArrayAdapter<String> OrderOrderIdAdapter;
	ArrayAdapter<String> ProductsProductIdAdapter;
	List<OrderDto> _OrderList;
	List<ProductsDto> _ProductsList;
    Button btnEdit;
    private SessionManager session;
    private String access_token;
    private Validation validation;
    OrderDetailsRepository _OrderDetailsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails_edit);
        getSupportActionBar().setTitle("OrderDetails Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        validation = new Validation(getApplicationContext());

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
		etUnitPrice = (EditText) findViewById(R.id.etUnitPrice);
		etQuantity = (EditText) findViewById(R.id.etQuantity);
		etDiscount = (EditText) findViewById(R.id.etDiscount);
		GetOrderList();
		GetProductsList();
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    OrderDetailsDto model = new OrderDetailsDto();
					model.setOrderId(new Integer(tvOrderId.getText().toString()));
					model.setProductId(new Integer(tvProductId.getText().toString()));
					if (etUnitPrice.getText().length() > 0)
						model.setUnitPrice(new Double(etUnitPrice.getText().toString()));
					if (etQuantity.getText().length() > 0)
						model.setQuantity(new Short(etQuantity.getText().toString()));
					if (etDiscount.getText().length() > 0)
						model.setDiscount(new Double(etDiscount.getText().toString()));
                    EditOrderDetails(model);
                }
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
			etUnitPrice.setText(String.valueOf(data.getUnitPrice()));
			etQuantity.setText(String.valueOf(data.getQuantity()));
			etDiscount.setText(String.valueOf(data.getDiscount()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etUnitPrice.getText().toString(), false, "Double", null)) {
			etUnitPrice.requestFocus();
			etUnitPrice.setError(validation.Message);
			result = false;
		}
		else {
			etUnitPrice.clearFocus();
			etUnitPrice.setError(null);
		}
		if(!validation.isValid("Required", etQuantity.getText().toString(), false, "Short", null)) {
			etQuantity.requestFocus();
			etQuantity.setError(validation.Message);
			result = false;
		}
		else {
			etQuantity.clearFocus();
			etQuantity.setError(null);
		}
		if(!validation.isValid("Required", etDiscount.getText().toString(), false, "Double", null)) {
			etDiscount.requestFocus();
			etDiscount.setError(validation.Message);
			result = false;
		}
		else {
			etDiscount.clearFocus();
			etDiscount.setError(null);
		}

		return result;
	}

    void EditOrderDetails(OrderDetailsDto item) {
        if (item != null) {
            _OrderDetailsRepository.Put(item);
            Intent intOrderDetailsList = new Intent(getApplicationContext(), OrderDetailsListActivity.class);
            startActivity(intOrderDetailsList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetOrderList() {
		OrderRepository _OrderRepository = new OrderRepository(access_token);
		_OrderList =  _OrderRepository.GetList(new Criteria());
		String[] arrOrderOrderId = new String[_OrderList.size() + 1];
		arrOrderOrderId[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _OrderList.size(); e++) {
			arrOrderOrderId[e + 1] = String.valueOf(_OrderList.get(e).getOrderId());
		}
		spnOrderOrderId = (Spinner) findViewById(R.id.spnOrderOrderId);
		OrderOrderIdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrOrderOrderId);
		OrderOrderIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnOrderOrderId.setPrompt("Please Select!");
		spnOrderOrderId.setAdapter(OrderOrderIdAdapter);
	}

	void GetProductsList() {
		ProductsRepository _ProductsRepository = new ProductsRepository(access_token);
		_ProductsList =  _ProductsRepository.GetList(new Criteria());
		String[] arrProductsProductId = new String[_ProductsList.size() + 1];
		arrProductsProductId[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _ProductsList.size(); e++) {
			arrProductsProductId[e + 1] = String.valueOf(_ProductsList.get(e).getProductName());
		}
		spnProductsProductId = (Spinner) findViewById(R.id.spnProductsProductId);
		ProductsProductIdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrProductsProductId);
		ProductsProductIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnProductsProductId.setPrompt("Please Select!");
		spnProductsProductId.setAdapter(ProductsProductIdAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
