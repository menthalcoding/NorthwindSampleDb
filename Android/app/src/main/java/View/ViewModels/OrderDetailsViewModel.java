package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Repositories.OrderDetailsRepository;
import Util.SessionManager;

public class OrderDetailsViewModel extends AndroidViewModel {
    private OrderDetailsRepository repository;
    private LiveData<List<OrderDetailsDto>> data;

    public OrderDetailsViewModel(@NonNull Application application) {
        super(application);

        repository = new OrderDetailsRepository(SessionManager.ACCESS_TOKEN);
        OrderDetailsCriteria criteria = new OrderDetailsCriteria();
        data = (LiveData<List<OrderDetailsDto>>) repository.GetList(criteria);
    }

    public OrderDetailsDto get(Integer OrderId, Integer ProductId) {
        OrderDetailsCriteria criteria = new OrderDetailsCriteria();
		criteria.OrderId = OrderId;
		criteria.ProductId = ProductId;
        return repository.Get(criteria);
    }

    public long insert(OrderDetailsDto item) {
        return repository.Post(item);
    }

    public void update(OrderDetailsDto item) {
        repository.Put(item);
    }

    public void delete(OrderDetailsDto item) {
        OrderDetailsCriteria criteria = new OrderDetailsCriteria();
		criteria.OrderId = item.getOrderId();
		criteria.ProductId = item.getProductId();
        repository.Delete(criteria);
    }

    public LiveData<List<OrderDetailsDto>> getItems() { return data; }
}