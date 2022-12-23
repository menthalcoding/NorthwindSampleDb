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
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.example.northwindsampledb.R;

import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.OrderRepository;
import HttpClient.Repositories.CustomersRepository;
import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Repositories.EmployeesRepository;
import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Repositories.ShippersRepository;
import HttpClient.DataTransferObjects.ShippersDto;
import Util.SessionManager;
import Util.Validation;

public class OrderCreateActivity extends AppCompatActivity {

	EditText etOrderDate;
	EditText etRequiredDate;
	EditText etShippedDate;
	EditText etShipName;
	EditText etFreight;
	EditText etShipAddress;
	EditText etShipCity;
	EditText etShipRegion;
	EditText etShipPostalCode;
	EditText etShipCountry;
	Spinner spnCustomersCustomerID;
	Spinner spnEmployeesEmployeeID;
	Spinner spnShippersShipperId;
	ArrayAdapter<String> CustomersCustomerIDAdapter;
	ArrayAdapter<String> EmployeesEmployeeIDAdapter;
	ArrayAdapter<String> ShippersShipperIdAdapter;
	List<CustomersDto> _CustomersList;
	List<EmployeesDto> _EmployeesList;
	List<ShippersDto> _ShippersList;
    Button btnCreate;
	DatePickerDialog datePickerDialog;
	Calendar calendar = Calendar.getInstance();
	int year, month, dayOfMonth;
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
        setContentView(R.layout.activity_order_create);
        getSupportActionBar().setTitle("Order Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etOrderDate = (EditText) findViewById(R.id.etOrderDate);
        etOrderDate.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
                        //etOrderDate.setInputType(InputType.TYPE_NULL);
				        year = calendar.get(Calendar.YEAR);
				        month = calendar.get(Calendar.MONTH);
				        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				        datePickerDialog = new DatePickerDialog(OrderCreateActivity.this,
					        new DatePickerDialog.OnDateSetListener() {
						        @Override
						        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String selectedDate = day + "/" + (month+1) + "/" + year;
									if (validation.isValidDate(selectedDate)) {
										etOrderDate.setText(selectedDate);
										etOrderDate.clearFocus();
										etOrderDate.setError(null);
									}
									else {
										etOrderDate.requestFocus();
										etOrderDate.setError(validation.Message);
									}
						        }
					        }, year, month, dayOfMonth);
				        datePickerDialog.show();
                    break;
                }
				return false;
			}
		});
		etRequiredDate = (EditText) findViewById(R.id.etRequiredDate);
        etRequiredDate.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
                        //etRequiredDate.setInputType(InputType.TYPE_NULL);
				        year = calendar.get(Calendar.YEAR);
				        month = calendar.get(Calendar.MONTH);
				        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				        datePickerDialog = new DatePickerDialog(OrderCreateActivity.this,
					        new DatePickerDialog.OnDateSetListener() {
						        @Override
						        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String selectedDate = day + "/" + (month+1) + "/" + year;
									if (validation.isValidDate(selectedDate)) {
										etRequiredDate.setText(selectedDate);
										etRequiredDate.clearFocus();
										etRequiredDate.setError(null);
									}
									else {
										etRequiredDate.requestFocus();
										etRequiredDate.setError(validation.Message);
									}
						        }
					        }, year, month, dayOfMonth);
				        datePickerDialog.show();
                    break;
                }
				return false;
			}
		});
		etShippedDate = (EditText) findViewById(R.id.etShippedDate);
        etShippedDate.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
                        //etShippedDate.setInputType(InputType.TYPE_NULL);
				        year = calendar.get(Calendar.YEAR);
				        month = calendar.get(Calendar.MONTH);
				        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				        datePickerDialog = new DatePickerDialog(OrderCreateActivity.this,
					        new DatePickerDialog.OnDateSetListener() {
						        @Override
						        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String selectedDate = day + "/" + (month+1) + "/" + year;
									if (validation.isValidDate(selectedDate)) {
										etShippedDate.setText(selectedDate);
										etShippedDate.clearFocus();
										etShippedDate.setError(null);
									}
									else {
										etShippedDate.requestFocus();
										etShippedDate.setError(validation.Message);
									}
						        }
					        }, year, month, dayOfMonth);
				        datePickerDialog.show();
                    break;
                }
				return false;
			}
		});
		etShipName = (EditText) findViewById(R.id.etShipName);
		etFreight = (EditText) findViewById(R.id.etFreight);
		etShipAddress = (EditText) findViewById(R.id.etShipAddress);
		etShipCity = (EditText) findViewById(R.id.etShipCity);
		etShipRegion = (EditText) findViewById(R.id.etShipRegion);
		etShipPostalCode = (EditText) findViewById(R.id.etShipPostalCode);
		etShipCountry = (EditText) findViewById(R.id.etShipCountry);
		GetCustomersList();
		GetEmployeesList();
		GetShippersList();
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    OrderDto model = new OrderDto();
					if (spnCustomersCustomerID.getSelectedItemPosition() > 0)
						model.setCustomerID(_CustomersList.get(spnCustomersCustomerID.getSelectedItemPosition() - 1).getCustomerID());
					if (spnEmployeesEmployeeID.getSelectedItemPosition() > 0)
						model.setEmployeeID(_EmployeesList.get(spnEmployeesEmployeeID.getSelectedItemPosition() - 1).getEmployeeID());
					if (etOrderDate.getText().length() > 0)
						model.setOrderDate(new Date(etOrderDate.getText().toString()));
					if (etRequiredDate.getText().length() > 0)
						model.setRequiredDate(new Date(etRequiredDate.getText().toString()));
					if (etShippedDate.getText().length() > 0)
						model.setShippedDate(new Date(etShippedDate.getText().toString()));
					model.setShipName(new String(etShipName.getText().toString()));
					if (spnShippersShipperId.getSelectedItemPosition() > 0)
						model.setShipVia(_ShippersList.get(spnShippersShipperId.getSelectedItemPosition() - 1).getShipperId());
					if (etFreight.getText().length() > 0)
						model.setFreight(new BigDecimal(etFreight.getText().toString()));
					model.setShipAddress(new String(etShipAddress.getText().toString()));
					model.setShipCity(new String(etShipCity.getText().toString()));
					model.setShipRegion(new String(etShipRegion.getText().toString()));
					model.setShipPostalCode(new String(etShipPostalCode.getText().toString()));
					model.setShipCountry(new String(etShipCountry.getText().toString()));
                    CreateOrder(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("Required", (spnCustomersCustomerID.getSelectedItemPosition() > 0 ? String.valueOf(_CustomersList.get(spnCustomersCustomerID.getSelectedItemPosition() - 1).getCustomerID()) : null), false, "String", "5")) {
			spnCustomersCustomerID.setFocusable(true);
			spnCustomersCustomerID.setFocusableInTouchMode(true);
			spnCustomersCustomerID.requestFocus();
			((TextView)spnCustomersCustomerID.getSelectedView()).setError(validation.Message);
			result = false;
		}
		else {
			spnCustomersCustomerID.clearFocus();
			((TextView)spnCustomersCustomerID.getSelectedView()).setError(null);
		}
		if(!validation.isValid("Required", (spnEmployeesEmployeeID.getSelectedItemPosition() > 0 ? String.valueOf(_EmployeesList.get(spnEmployeesEmployeeID.getSelectedItemPosition() - 1).getEmployeeID()) : null), false, "Integer", "0")) {
			spnEmployeesEmployeeID.setFocusable(true);
			spnEmployeesEmployeeID.setFocusableInTouchMode(true);
			spnEmployeesEmployeeID.requestFocus();
			((TextView)spnEmployeesEmployeeID.getSelectedView()).setError(validation.Message);
			result = false;
		}
		else {
			spnEmployeesEmployeeID.clearFocus();
			((TextView)spnEmployeesEmployeeID.getSelectedView()).setError(null);
		}
		if(!validation.isValid("", etOrderDate.getText().toString(), false, "Date", null)) {
			etOrderDate.requestFocus();
			etOrderDate.setError(validation.Message);
			result = false;
		}
		else {
			etOrderDate.clearFocus();
			etOrderDate.setError(null);
		}
		if(!validation.isValid("Range", etRequiredDate.getText().toString(), true, "Date", null)) {
			etRequiredDate.requestFocus();
			etRequiredDate.setError(validation.Message);
			result = false;
		}
		else {
			etRequiredDate.clearFocus();
			etRequiredDate.setError(null);
		}
		if(!validation.isValid("Range", etShippedDate.getText().toString(), true, "Date", null)) {
			etShippedDate.requestFocus();
			etShippedDate.setError(validation.Message);
			result = false;
		}
		else {
			etShippedDate.clearFocus();
			etShippedDate.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipName.getText().toString(), true, "String", "40")) {
			etShipName.requestFocus();
			etShipName.setError(validation.Message);
			result = false;
		}
		else {
			etShipName.clearFocus();
			etShipName.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipAddress.getText().toString(), true, "String", "60")) {
			etShipAddress.requestFocus();
			etShipAddress.setError(validation.Message);
			result = false;
		}
		else {
			etShipAddress.clearFocus();
			etShipAddress.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipCity.getText().toString(), true, "String", "15")) {
			etShipCity.requestFocus();
			etShipCity.setError(validation.Message);
			result = false;
		}
		else {
			etShipCity.clearFocus();
			etShipCity.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipRegion.getText().toString(), true, "String", "15")) {
			etShipRegion.requestFocus();
			etShipRegion.setError(validation.Message);
			result = false;
		}
		else {
			etShipRegion.clearFocus();
			etShipRegion.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipPostalCode.getText().toString(), true, "String", "10")) {
			etShipPostalCode.requestFocus();
			etShipPostalCode.setError(validation.Message);
			result = false;
		}
		else {
			etShipPostalCode.clearFocus();
			etShipPostalCode.setError(null);
		}
		if(!validation.isValid("MaxLength", etShipCountry.getText().toString(), true, "String", "15")) {
			etShipCountry.requestFocus();
			etShipCountry.setError(validation.Message);
			result = false;
		}
		else {
			etShipCountry.clearFocus();
			etShipCountry.setError(null);
		}

		return result;
	}

    void CreateOrder(OrderDto item) {
        OrderRepository _OrderRepository = new OrderRepository(access_token);
        if (_OrderRepository.Post(item) > 0) {
            Intent intOrderList = new Intent(getApplicationContext(), OrderListActivity.class);
            startActivity(intOrderList);
        }
        else {
            Toast.makeText(getApplicationContext(), "Bad Request", Toast.LENGTH_LONG).show();
        }
    }

	void GetCustomersList() {
		CustomersRepository _CustomersRepository = new CustomersRepository(access_token);
		_CustomersList =  _CustomersRepository.GetList(new Criteria());
		String[] arrCustomersCustomerID = new String[_CustomersList.size() + 1];
		arrCustomersCustomerID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _CustomersList.size(); e++) {
			arrCustomersCustomerID[e + 1] = String.valueOf(_CustomersList.get(e).getCompanyName());
		}
		spnCustomersCustomerID = (Spinner) findViewById(R.id.spnCustomersCustomerID);
		CustomersCustomerIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCustomersCustomerID);
		CustomersCustomerIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCustomersCustomerID.setPrompt("Please Select!");
		spnCustomersCustomerID.setAdapter(CustomersCustomerIDAdapter);
	}

	void GetEmployeesList() {
		EmployeesRepository _EmployeesRepository = new EmployeesRepository(access_token);
		_EmployeesList =  _EmployeesRepository.GetList(new Criteria());
		String[] arrEmployeesEmployeeID = new String[_EmployeesList.size() + 1];
		arrEmployeesEmployeeID[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _EmployeesList.size(); e++) {
			arrEmployeesEmployeeID[e + 1] = String.valueOf(_EmployeesList.get(e).getTitle());
		}
		spnEmployeesEmployeeID = (Spinner) findViewById(R.id.spnEmployeesEmployeeID);
		EmployeesEmployeeIDAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrEmployeesEmployeeID);
		EmployeesEmployeeIDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnEmployeesEmployeeID.setPrompt("Please Select!");
		spnEmployeesEmployeeID.setAdapter(EmployeesEmployeeIDAdapter);
	}

	void GetShippersList() {
		ShippersRepository _ShippersRepository = new ShippersRepository(access_token);
		_ShippersList =  _ShippersRepository.GetList(new Criteria());
		String[] arrShippersShipperId = new String[_ShippersList.size() + 1];
		arrShippersShipperId[0] = getResources().getString(R.string.prompt_spinner_select);
		for (int e = 0; e < _ShippersList.size(); e++) {
			arrShippersShipperId[e + 1] = String.valueOf(_ShippersList.get(e).getCompanyName());
		}
		spnShippersShipperId = (Spinner) findViewById(R.id.spnShippersShipperId);
		ShippersShipperIdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrShippersShipperId);
		ShippersShipperIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnShippersShipperId.setPrompt("Please Select!");
		spnShippersShipperId.setAdapter(ShippersShipperIdAdapter);
	}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
