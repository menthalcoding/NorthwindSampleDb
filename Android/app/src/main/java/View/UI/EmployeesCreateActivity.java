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

import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.EmployeesRepository;
import Util.SessionManager;
import Util.Validation;

public class EmployeesCreateActivity extends AppCompatActivity {

	EditText etLastName;
	EditText etFirstName;
	EditText etTitle;
	EditText etTitleOfCourtesy;
	EditText etBirthDate;
	EditText etHireDate;
	EditText etAddress;
	EditText etCity;
	EditText etRegion;
	EditText etPostalCode;
	EditText etCountry;
	EditText etHomePhone;
	EditText etExtension;
	EditText etPhoto;
	EditText etNotes;
	EditText etReportsTo;
	EditText etPhotoPath;
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
        setContentView(R.layout.activity_employees_create);
        getSupportActionBar().setTitle("Employees Create");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        validation = new Validation(getApplicationContext());
		etLastName = (EditText) findViewById(R.id.etLastName);
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etTitle = (EditText) findViewById(R.id.etTitle);
		etTitleOfCourtesy = (EditText) findViewById(R.id.etTitleOfCourtesy);
		etBirthDate = (EditText) findViewById(R.id.etBirthDate);
        etBirthDate.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
                        //etBirthDate.setInputType(InputType.TYPE_NULL);
				        year = calendar.get(Calendar.YEAR);
				        month = calendar.get(Calendar.MONTH);
				        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				        datePickerDialog = new DatePickerDialog(EmployeesCreateActivity.this,
					        new DatePickerDialog.OnDateSetListener() {
						        @Override
						        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String selectedDate = day + "/" + (month+1) + "/" + year;
									if (validation.isValidDate(selectedDate)) {
										etBirthDate.setText(selectedDate);
										etBirthDate.clearFocus();
										etBirthDate.setError(null);
									}
									else {
										etBirthDate.requestFocus();
										etBirthDate.setError(validation.Message);
									}
						        }
					        }, year, month, dayOfMonth);
				        datePickerDialog.show();
                    break;
                }
				return false;
			}
		});
		etHireDate = (EditText) findViewById(R.id.etHireDate);
        etHireDate.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
                        //etHireDate.setInputType(InputType.TYPE_NULL);
				        year = calendar.get(Calendar.YEAR);
				        month = calendar.get(Calendar.MONTH);
				        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				        datePickerDialog = new DatePickerDialog(EmployeesCreateActivity.this,
					        new DatePickerDialog.OnDateSetListener() {
						        @Override
						        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String selectedDate = day + "/" + (month+1) + "/" + year;
									if (validation.isValidDate(selectedDate)) {
										etHireDate.setText(selectedDate);
										etHireDate.clearFocus();
										etHireDate.setError(null);
									}
									else {
										etHireDate.requestFocus();
										etHireDate.setError(validation.Message);
									}
						        }
					        }, year, month, dayOfMonth);
				        datePickerDialog.show();
                    break;
                }
				return false;
			}
		});
		etAddress = (EditText) findViewById(R.id.etAddress);
		etCity = (EditText) findViewById(R.id.etCity);
		etRegion = (EditText) findViewById(R.id.etRegion);
		etPostalCode = (EditText) findViewById(R.id.etPostalCode);
		etCountry = (EditText) findViewById(R.id.etCountry);
		etHomePhone = (EditText) findViewById(R.id.etHomePhone);
		etExtension = (EditText) findViewById(R.id.etExtension);
		etPhoto = (EditText) findViewById(R.id.etPhoto);
		etNotes = (EditText) findViewById(R.id.etNotes);
		etReportsTo = (EditText) findViewById(R.id.etReportsTo);
		etPhotoPath = (EditText) findViewById(R.id.etPhotoPath);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsValid()) {
                    EmployeesDto model = new EmployeesDto();
					model.setLastName(new String(etLastName.getText().toString()));
					model.setFirstName(new String(etFirstName.getText().toString()));
					model.setTitle(new String(etTitle.getText().toString()));
					model.setTitleOfCourtesy(new String(etTitleOfCourtesy.getText().toString()));
					if (etBirthDate.getText().length() > 0)
						model.setBirthDate(new Date(etBirthDate.getText().toString()));
					if (etHireDate.getText().length() > 0)
						model.setHireDate(new Date(etHireDate.getText().toString()));
					model.setAddress(new String(etAddress.getText().toString()));
					model.setCity(new String(etCity.getText().toString()));
					model.setRegion(new String(etRegion.getText().toString()));
					model.setPostalCode(new String(etPostalCode.getText().toString()));
					model.setCountry(new String(etCountry.getText().toString()));
					model.setHomePhone(new String(etHomePhone.getText().toString()));
					model.setExtension(new String(etExtension.getText().toString()));
					model.setPhoto(new String(etPhoto.getText().toString()));
					model.setNotes(new String(etNotes.getText().toString()));
					if (etReportsTo.getText().length() > 0)
						model.setReportsTo(new Integer(etReportsTo.getText().toString()));
					model.setPhotoPath(new String(etPhotoPath.getText().toString()));
                    CreateEmployees(model);
                }
            }
        });
    }

	boolean IsValid() {
		boolean result = true;
		if(!validation.isValid("MaxLength", etFirstName.getText().toString(), true, "String", "50")) {
			etFirstName.requestFocus();
			etFirstName.setError(validation.Message);
			result = false;
		}
		else {
			etFirstName.clearFocus();
			etFirstName.setError(null);
		}
		if(!validation.isValid("MaxLength", etTitle.getText().toString(), false, "String", "30")) {
			etTitle.requestFocus();
			etTitle.setError(validation.Message);
			result = false;
		}
		else {
			etTitle.clearFocus();
			etTitle.setError(null);
		}
		if(!validation.isValid("MaxLength", etTitleOfCourtesy.getText().toString(), false, "String", "25")) {
			etTitleOfCourtesy.requestFocus();
			etTitleOfCourtesy.setError(validation.Message);
			result = false;
		}
		else {
			etTitleOfCourtesy.clearFocus();
			etTitleOfCourtesy.setError(null);
		}
		if(!validation.isValid("Range", etBirthDate.getText().toString(), false, "Date", null)) {
			etBirthDate.requestFocus();
			etBirthDate.setError(validation.Message);
			result = false;
		}
		else {
			etBirthDate.clearFocus();
			etBirthDate.setError(null);
		}
		if(!validation.isValid("Range", etHireDate.getText().toString(), true, "Date", null)) {
			etHireDate.requestFocus();
			etHireDate.setError(validation.Message);
			result = false;
		}
		else {
			etHireDate.clearFocus();
			etHireDate.setError(null);
		}
		if(!validation.isValid("MaxLength", etAddress.getText().toString(), true, "String", "60")) {
			etAddress.requestFocus();
			etAddress.setError(validation.Message);
			result = false;
		}
		else {
			etAddress.clearFocus();
			etAddress.setError(null);
		}
		if(!validation.isValid("MaxLength", etCity.getText().toString(), true, "String", "15")) {
			etCity.requestFocus();
			etCity.setError(validation.Message);
			result = false;
		}
		else {
			etCity.clearFocus();
			etCity.setError(null);
		}
		if(!validation.isValid("MaxLength", etRegion.getText().toString(), true, "String", "15")) {
			etRegion.requestFocus();
			etRegion.setError(validation.Message);
			result = false;
		}
		else {
			etRegion.clearFocus();
			etRegion.setError(null);
		}
		if(!validation.isValid("MaxLength", etPostalCode.getText().toString(), true, "String", "10")) {
			etPostalCode.requestFocus();
			etPostalCode.setError(validation.Message);
			result = false;
		}
		else {
			etPostalCode.clearFocus();
			etPostalCode.setError(null);
		}
		if(!validation.isValid("MaxLength", etCountry.getText().toString(), true, "String", "15")) {
			etCountry.requestFocus();
			etCountry.setError(validation.Message);
			result = false;
		}
		else {
			etCountry.clearFocus();
			etCountry.setError(null);
		}
		if(!validation.isValid("Phone", etHomePhone.getText().toString(), true, "String", "24")) {
			etHomePhone.requestFocus();
			etHomePhone.setError(validation.Message);
			result = false;
		}
		else {
			etHomePhone.clearFocus();
			etHomePhone.setError(null);
		}
		if(!validation.isValid("MaxLength", etExtension.getText().toString(), true, "String", "4")) {
			etExtension.requestFocus();
			etExtension.setError(validation.Message);
			result = false;
		}
		else {
			etExtension.clearFocus();
			etExtension.setError(null);
		}
		if(!validation.isValid("MaxLength", etPhoto.getText().toString(), true, "String", "255")) {
			etPhoto.requestFocus();
			etPhoto.setError(validation.Message);
			result = false;
		}
		else {
			etPhoto.clearFocus();
			etPhoto.setError(null);
		}
		if(!validation.isValid("MaxLength", etNotes.getText().toString(), true, "String", "2000")) {
			etNotes.requestFocus();
			etNotes.setError(validation.Message);
			result = false;
		}
		else {
			etNotes.clearFocus();
			etNotes.setError(null);
		}
		if(!validation.isValid("None", etReportsTo.getText().toString(), true, "Integer", null)) {
			etReportsTo.requestFocus();
			etReportsTo.setError(validation.Message);
			result = false;
		}
		else {
			etReportsTo.clearFocus();
			etReportsTo.setError(null);
		}
		if(!validation.isValid("MaxLength", etPhotoPath.getText().toString(), true, "String", "255")) {
			etPhotoPath.requestFocus();
			etPhotoPath.setError(validation.Message);
			result = false;
		}
		else {
			etPhotoPath.clearFocus();
			etPhotoPath.setError(null);
		}

		return result;
	}

    void CreateEmployees(EmployeesDto item) {
        EmployeesRepository _EmployeesRepository = new EmployeesRepository(access_token);
        if (_EmployeesRepository.Post(item) > 0) {
            Intent intEmployeesList = new Intent(getApplicationContext(), EmployeesListActivity.class);
            startActivity(intEmployeesList);
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
