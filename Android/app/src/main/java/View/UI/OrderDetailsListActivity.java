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

import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.OrderDetailsRepository;
import Util.Constant;
import Util.SessionManager;
import View.Adapter.OrderDetailsListRecyclerViewAdapter;
import io.reactivex.Single;

public class OrderDetailsListActivity extends AppCompatActivity {

    private SessionManager session;
    private String access_token;
    private RecyclerView recyclerView;
    private OrderDetailsListRecyclerViewAdapter adapter;
    private RecyclerTouchListener touchListener;
    private List<OrderDetailsDto> _OrderDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails_list);

        session = new SessionManager(getApplicationContext());
        access_token = session.getAccessToken();
        if (access_token.isEmpty()) {
            Intent intent = new Intent(this, LoginWebApiActivity.class);
            startActivity(intent);
            return;
        }

        OrderDetailsRepository _OrderDetailsRepository = new OrderDetailsRepository(access_token);
        Criteria c = new Criteria();
        _OrderDetailsList = _OrderDetailsRepository.GetList(c);

        getSupportActionBar().setTitle("OrderDetails List (" + _OrderDetailsList.size() + " items)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (_OrderDetailsRepository.StatusCode == Constant.StatusCode_OK && _OrderDetailsList != null)
        {
            FloatingActionButton fabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intCreate = new Intent(getApplicationContext(), OrderDetailsCreateActivity.class);
                    startActivity(intCreate);
                }
            });

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            adapter = new OrderDetailsListRecyclerViewAdapter(_OrderDetailsList, getApplication(), true);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //https://github.com/velmurugan-murugesan/Android-Example/tree/master/RecyclerviewWithSwipeMenuAndroidJava/app/src/main/res/layout
            touchListener = new RecyclerTouchListener(this,recyclerView);
            touchListener.setClickable(new RecyclerTouchListener.OnRowClickListener() {
                @Override
                public void onRowClicked(int position) {
                    Intent intDetail = new Intent(getApplicationContext(), OrderDetailsDetailActivity.class);
					intDetail.putExtra("OrderId", _OrderDetailsList.get(position).getOrderId());
					intDetail.putExtra("ProductId", _OrderDetailsList.get(position).getProductId());
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
                            Intent intDelete = new Intent(getApplicationContext(), OrderDetailsDeleteActivity.class);
							intDelete.putExtra("OrderId", _OrderDetailsList.get(position).getOrderId());
							intDelete.putExtra("ProductId", _OrderDetailsList.get(position).getProductId());
                            startActivity(intDelete);
                            break;
                        case R.id.edit_task:
                            //Toast.makeText(getApplicationContext(),"Edit Not Available",Toast.LENGTH_SHORT).show();
                            Intent intEdit = new Intent(getApplicationContext(), OrderDetailsEditActivity.class);
							intEdit.putExtra("OrderId", _OrderDetailsList.get(position).getOrderId());
							intEdit.putExtra("ProductId", _OrderDetailsList.get(position).getProductId());
                            startActivity(intEdit);
                            break;
                    }
                }
            });
            recyclerView.addOnItemTouchListener(touchListener);
        }
        else if (_OrderDetailsRepository.StatusCode == Constant.StatusCode_Unauthorized) {
            Intent loginIntent = new Intent(getApplicationContext(), LoginWebApiActivity.class);
            startActivity(loginIntent);
        }
        else if (_OrderDetailsRepository.StatusCode == Constant.StatusCode_Forbidden) {
            Toast.makeText(getApplicationContext(), R.string.Forbidden, Toast.LENGTH_LONG).show();
        }

        if (_OrderDetailsList != null)
        {
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            OrderDetailsListRecyclerViewAdapter adapter = new OrderDetailsListRecyclerViewAdapter(_OrderDetailsList, getApplication(), true);
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