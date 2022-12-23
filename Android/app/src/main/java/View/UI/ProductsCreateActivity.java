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

import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.ProductsRepository;
import HttpClient.Repositories.CategoriesRepository;
import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Repositories.SuppliersRepository;
import HttpClient.DataTransferObjects.SuppliersDto;
import Util.SessionManager;
import Util.Validation;

public class ProductsCreateActivity extends AppCompatActivity {

	EditText etProductName;
	EditText etQuantityPerUnit;
	EditText etUnitPrice;
	EditText etUnitsInStock;
	EditText etUnitsOnOrder;
	EditText etReorderLevel;
	EditText etDiscontinued;
	Spinner spnCategoriesCategoryID;
	Spinner spnSuppliersSupplierID;
	ArrayAdapter<String> CategoriesCategoryIDAdapter;
	ArrayAdapter<String> SuppliersSupplierIDAdapter;
	List<CategoriesDto> _CategoriesList;
	List<SuppliersDto> _SuppliersList;
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
        setContentView(R.layout.activity_products_create);
        getSupportActionBar().setTitle("Products Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etProductName = (EditText) findViewById(R.id.etProductName);
		etQuantityPerUnit = (EditText) findViewById(R.id.etQuantityPerUnit);
		etUnitPrice = (EditText) findViewById(R.id.etUnitPrice);
		etUnitsInStock = (EditText) findViewById(R.id.etUnitsInStock);
		etUnitsOnOrder = (EditText) findViewById(R.id.etUnitsOnOrder);
		etReorderLevel = (EditText) findViewById(R.id.etReorderLevel);
		etDiscontinued = (EditText) findViewById(R.id.etDiscontinued);
		GetCategoriesList();
		GetSuppliersList();
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    ProductsDto model = new ProductsDto();
					model.setProductName(new String(etProductName.getText().toString()));
					if (spnSuppliersSupplierID.getSelectedItemPosition() > 0)
						model.setSupplierID(_SuppliersList.get(spnSuppliersSupplierID.getSelectedItemPosition() - 1).getSupplierID());
					if (spnCategoriesCategoryID.getSelectedItemPosition() > 0)
						model.setCategoryID(_CategoriesList.get(spnCategoriesCategoryID.getSelectedItemPosition() - 1).getCategoryID());
					model.setQuantityPerUnit(new String(etQuantityPerUnit.getText().toString()));
					if (etUnitPrice.getText().length() > 0)
						model.setUnitPrice(new Double(etUnitPrice.getText().toString()));
					if (etUnitsInStock.getText().length() > 0)
						model.setUnitsInStock(new Short(etUnitsInStock.getText().toString()));
					if (etUnitsOnOrder.getText().length() > 0)
						model.setUnitsOnOrder(new Short(etUnitsOnOrder.getText().toString()));
					if (etReorderLevel.getText().length() > 0)
						model.setReorderLevel(new Short(etReorderLevel.getText().toString()));
					if (etDiscontinued.getText().length() > 0)
						model.setDiscontinued(new Boolean(etDiscontinued.getText().toString()));
                    CreateProducts(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", etProductName.getText().toString(), false, "String", "40")) {
			etProductName.requestFocus();
			etProductName.setError(validation.Message);
			result = false;
		}
		else {
			etProductName.clearFocus();
			etProductName.setError(null);
		}
		if(!validation.isValid("None", (spnSuppliersSupplierID.getSelectedItemPosition() > 0 ? String.valueOf(_SuppliersList.get(spnSuppliersSupplierID.getSelectedItemPosition() - 1).getSupplierID()) : null), true, "Integer", null)) {
			spnSuppliersSupplierID.setFocusable(true);
			spnSuppliersSupplierID.setFocusableInTouchMode(true);
			spnSuppliersSupplierID.requestFocus();
			((TextView)spnSuppliersSupplierID.getSelectedView()).setError(validation.Message);
			result = false;
		}
		else {
			spnSuppliersSupplierID.clearFocus();
			((TextView)spnSuppliersSupplierID.getSelectedView()).setError(null);
		}
		if(!validation.isValid("None", (spnCategoriesCategoryID.getSelectedItemPosition() > 0 ? String.valueOf(_CategoriesList.get(spnCategoriesCategoryID.getSelectedItemPosition() - 1).getCategoryID()) : null), true, "Integer", null)) {
			spnCategoriesCategoryID.setFocusable(true);
			spnCategoriesCategoryID.setFocusableInTouchMode(true);
			spnCategoriesCategoryID.requestFocus();
			((TextView)spnCategoriesCategoryID.getSelectedView()).setError(validation.Message);
			result = false;
		}
		else {
			spnCategoriesCategoryID.clearFocus();
			((TextView)spnCategoriesCategoryID.getSelectedView()).setError(null);
		}
		if(!validation.isValid("MaxLength", etQuantityPerUnit.getText().toString(), true, "String", "20")) {
			etQuantityPerUnit.requestFocus();
			etQuantityPerUnit.setError(validation.Message);
			result = false;
		}
		else {
			etQuantityPerUnit.clearFocus();
			etQuantityPerUnit.setError(null);
		}
		if(!validation.isValid("None", etUnitPrice.getText().toString(), true, "Double", null)) {
			etUnitPrice.requestFocus();
			etUnitPrice.setError(validation.Message);
			result = false;
		}
		else {
			etUnitPrice.clearFocus();
			etUnitPrice.setError(null);
		}
		if(!validation.isValid("None", etUnitsInStock.getText().toString(), true, "Short", null)) {
			etUnitsInStock.requestFocus();
			etUnitsInStock.setError(validation.Message);
			result = false;
		}
		else {
			etUnitsInStock.clearFocus();
			etUnitsInStock.setError(null);
		}
		if(!validation.isValid("None", etUnitsOnOrder.getText().toString(), true, "Short", null)) {
			etUnitsOnOrder.requestFocus();
			etUnitsOnOrder.setError(validation.Message);
			result = false;
		}
		else {
			etUnitsOnOrder.clearFocus();
			etUnitsOnOrder.setError(null);
		}
		if(!validation.isValid("None", etReorderLevel.getText().toString(), true, "Short", null)) {
			etReorderLevel.requestFocus();
			etReorderLevel.setError(validation.Message);
			result = false;
		}
		else {
			etReorderLevel.clearFocus();
			etReorderLevel.setError(null);
		}
		if(!validation.isValid("Required", etDiscontinued.getText().toString(), false, "Boolean", null)) {
			etDiscontinued.requestFocus();
			etDiscontinued.setError(validation.Message);
			result = false;
		}
		else {
			etDiscontinued.clearFocus();
			etDiscontinued.setError(null);
		}

		return result;
	}

    void CreateProducts(ProductsDto item) {
        ProductsRepository _ProductsRepository = new ProductsRepository(access_token);
        if (_ProductsRepository.Post(item) > 0) {
            Intent intProductsList = new Intent(getApplicationContext(), ProductsListActivity.class);
            startActivity(intProductsList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetCategoriesList() {
		CategoriesRepository _CategoriesRepository = new CategoriesRepository(access_token);
		_CategoriesList =  _CategoriesRepository.GetList(new Criteria());
		String[] arrCategoriesCategoryID = new String[_CategoriesList.size() + 1];
		arrCategoriesCategoryID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _CategoriesList.size(); e++) {
			arrCategoriesCategoryID[e + 1] = String.valueOf(_CategoriesList.get(e).getCategoryName());
		}
		spnCategoriesCategoryID = (Spinner) findViewById(R.id.spnCategoriesCategoryID);
		CategoriesCategoryIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCategoriesCategoryID);
		CategoriesCategoryIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCategoriesCategoryID.setPrompt("Please Select!");
		spnCategoriesCategoryID.setAdapter(CategoriesCategoryIDAdapter);
	}

	void GetSuppliersList() {
		SuppliersRepository _SuppliersRepository = new SuppliersRepository(access_token);
		_SuppliersList =  _SuppliersRepository.GetList(new Criteria());
		String[] arrSuppliersSupplierID = new String[_SuppliersList.size() + 1];
		arrSuppliersSupplierID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _SuppliersList.size(); e++) {
			arrSuppliersSupplierID[e + 1] = String.valueOf(_SuppliersList.get(e).getCompanyName());
		}
		spnSuppliersSupplierID = (Spinner) findViewById(R.id.spnSuppliersSupplierID);
		SuppliersSupplierIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrSuppliersSupplierID);
		SuppliersSupplierIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSuppliersSupplierID.setPrompt("Please Select!");
		spnSuppliersSupplierID.setAdapter(SuppliersSupplierIDAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
