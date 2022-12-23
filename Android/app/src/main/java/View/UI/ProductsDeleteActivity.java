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

import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.ProductsRepository;
import Util.Converter;
import Util.SessionManager;

public class ProductsDeleteActivity extends AppCompatActivity {
	TextView tvProductId;
	TextView tvProductName;
	TextView tvSupplierID;
	TextView tvCategoryID;
	TextView tvQuantityPerUnit;
	TextView tvUnitPrice;
	TextView tvUnitsInStock;
	TextView tvUnitsOnOrder;
	TextView tvReorderLevel;
	TextView tvDiscontinued;
    Button btnDelete;
    private SessionManager session;
    private String access_token;
    ProductsRepository _ProductsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_delete);
        getSupportActionBar().setTitle("Products Delete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        _ProductsRepository = new ProductsRepository(access_token);

		tvProductId = (TextView) findViewById(R.id.tvProductId);
		tvProductName = (TextView) findViewById(R.id.tvProductName);
		tvSupplierID = (TextView) findViewById(R.id.tvSupplierID);
		tvCategoryID = (TextView) findViewById(R.id.tvCategoryID);
		tvQuantityPerUnit = (TextView) findViewById(R.id.tvQuantityPerUnit);
		tvUnitPrice = (TextView) findViewById(R.id.tvUnitPrice);
		tvUnitsInStock = (TextView) findViewById(R.id.tvUnitsInStock);
		tvUnitsOnOrder = (TextView) findViewById(R.id.tvUnitsOnOrder);
		tvReorderLevel = (TextView) findViewById(R.id.tvReorderLevel);
		tvDiscontinued = (TextView) findViewById(R.id.tvDiscontinued);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteProducts(new Integer(tvProductId.getText().toString()));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
			Integer keyProductId = (Integer)extras.get("ProductId");
            GetProducts(keyProductId);
        }
    }

    private void GetProducts(Integer ProductId) {
        ProductsCriteria c = new ProductsCriteria();
		c.ProductId = ProductId;
        ProductsDto data = _ProductsRepository.Get(c);
        if (data != null) {
			tvProductId.setText(String.valueOf(data.getProductId()));
			tvProductName.setText(String.valueOf(data.getProductName()));
			tvSupplierID.setText(String.valueOf(data.getSupplierID()));
			tvCategoryID.setText(String.valueOf(data.getCategoryID()));
			tvQuantityPerUnit.setText(String.valueOf(data.getQuantityPerUnit()));
			tvUnitPrice.setText(String.valueOf(data.getUnitPrice()));
			tvUnitsInStock.setText(String.valueOf(data.getUnitsInStock()));
			tvUnitsOnOrder.setText(String.valueOf(data.getUnitsOnOrder()));
			tvReorderLevel.setText(String.valueOf(data.getReorderLevel()));
			tvDiscontinued.setText(String.valueOf(data.getDiscontinued()));
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

    private void DeleteProducts(Integer ProductId) {
        ProductsCriteria criteria = new ProductsCriteria();
		criteria.ProductId = ProductId;
        _ProductsRepository.Delete(criteria);

        Intent intProductsList = new Intent(getApplicationContext(), ProductsListActivity.class);
        startActivity(intProductsList);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}