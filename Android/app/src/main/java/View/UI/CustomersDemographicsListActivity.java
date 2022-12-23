package View.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.northwindsampledb.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.CustomersDemographicsRepository;
import Util.Constant;
import Util.SessionManager;
import View.Adapter.CustomersDemographicsListRecyclerViewAdapter;
import io.reactivex.Single;

public class CustomersDemographicsListActivity extends AppCompatActivity {

    private SessionManager session;
    private String access_token;
    private RecyclerView recyclerView;
    private CustomersDemographicsListRecyclerViewAdapter adapter;
    private RecyclerTouchListener touchListener;
    private List<CustomersDemographicsDto> _CustomersDemographicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customersdemographics_list);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        CustomersDemographicsRepository _CustomersDemographicsRepository = new CustomersDemographicsRepository(access_token);
        Criteria c = new Criteria();
        _CustomersDemographicsList = _CustomersDemographicsRepository.GetList(c);

        getSupportActionBar().setTitle("CustomersDemographics List (" + _CustomersDemographicsList.size() + " items)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (_CustomersDemographicsRepository.StatusCode == Constant.StatusCode_OK && _CustomersDemographicsList != null)
        {
            FloatingActionButton fabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intCreate = new Intent(getApplicationContext(), CustomersDemographicsCreateActivity.class);
                    startActivity(intCreate);
                }
            });

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            adapter = new CustomersDemographicsListRecyclerViewAdapter(_CustomersDemographicsList, getApplication(), true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //https://github.com/velmurugan-murugesan/Android-Example/tree/master/RecyclerviewWithSwipeMenuAndroidJava/app/src/main/res/layout
            touchListener = new RecyclerTouchListener(this,recyclerView);
            touchListener.setClickable(new RecyclerTouchListener.OnRowClickListener() {
                @Override
                public void onRowClicked(int position) {
                    Intent intDetail = new Intent(getApplicationContext(), CustomersDemographicsDetailActivity.class);
					intDetail.putExtra("CustomersTypeId", _CustomersDemographicsList.get(position).getCustomersTypeId());
                    startActivity(intDetail);
                }

                @Override
                public void onIndependentViewClicked(int independentViewID, int position) {

                }
            })
            .setSwipeOptionViews(R.id.delete_task, R.id.edit_task)
            .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                @Override
                public void onSwipeOptionClicked(int viewID, int position) {
                    switch (viewID){
                        case R.id.delete_task:
                            //_CategoriesList.remove(position);
                            //adapter.setTaskList(_CategoriesList);
                            Intent intDelete = new Intent(getApplicationContext(), CustomersDemographicsDeleteActivity.class);
							intDelete.putExtra("CustomersTypeId", _CustomersDemographicsList.get(position).getCustomersTypeId());
                            startActivity(intDelete);
                            break;
                        case R.id.edit_task:
                            //Toast.makeText(getApplicationContext(),"Edit Not Available",Toast.LENGTH_SHORT).show();
                            Intent intEdit = new Intent(getApplicationContext(), CustomersDemographicsEditActivity.class);
							intEdit.putExtra("CustomersTypeId", _CustomersDemographicsList.get(position).getCustomersTypeId());
                            startActivity(intEdit);
                            break;
                    }
                }
            });
            recyclerView.addOnItemTouchListener(touchListener);
        }
        else if (_CustomersDemographicsRepository.StatusCode == Constant.StatusCode_Unauthorized) {
            Intent loginIntent = new Intent(getApplicationContext(), LoginWebApiActivity.class);
            startActivity(loginIntent);
        }
        else if (_CustomersDemographicsRepository.StatusCode == Constant.StatusCode_Forbidden) {
            Toast.makeText(getApplicationContext(), R.string.Forbidden, Toast.LENGTH_LONG).show();
        }

        if (_CustomersDemographicsList != null)
        {
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            CustomersDemographicsListRecyclerViewAdapter adapter = new CustomersDemographicsListRecyclerViewAdapter(_CustomersDemographicsList, getApplication(), true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}