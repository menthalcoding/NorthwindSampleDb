package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Repositories.OrderRepository;
import Util.SessionManager;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepository repository;
    private LiveData<List<OrderDto>> data;

    public OrderViewModel(@NonNull Application application) {
        super(application);

        repository = new OrderRepository(SessionManager.ACCESS_TOKEN);
        OrderCriteria criteria = new OrderCriteria();
        data = (LiveData<List<OrderDto>>) repository.GetList(criteria);
    }

    public OrderDto get(Integer OrderId) {
        OrderCriteria criteria = new OrderCriteria();
		criteria.OrderId = OrderId;
        return repository.Get(criteria);
    }

    public long insert(OrderDto item) {
        return repository.Post(item);
    }

    public void update(OrderDto item) {
        repository.Put(item);
    }

    public void delete(OrderDto item) {
        OrderCriteria criteria = new OrderCriteria();
		criteria.OrderId = item.getOrderId();
        repository.Delete(criteria);
    }

    public LiveData<List<OrderDto>> getItems() { return data; }
}