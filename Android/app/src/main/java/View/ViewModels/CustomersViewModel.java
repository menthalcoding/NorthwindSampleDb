package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Repositories.CustomersRepository;
import Util.SessionManager;

public class CustomersViewModel extends AndroidViewModel {
    private CustomersRepository repository;
    private LiveData<List<CustomersDto>> data;

    public CustomersViewModel(@NonNull Application application) {
        super(application);

        repository = new CustomersRepository(SessionManager.ACCESS_TOKEN);
        CustomersCriteria criteria = new CustomersCriteria();
        data = (LiveData<List<CustomersDto>>) repository.GetList(criteria);
    }

    public CustomersDto get(String CustomerID) {
        CustomersCriteria criteria = new CustomersCriteria();
		criteria.CustomerID = CustomerID;
        return repository.Get(criteria);
    }

    public long insert(CustomersDto item) {
        return repository.Post(item);
    }

    public void update(CustomersDto item) {
        repository.Put(item);
    }

    public void delete(CustomersDto item) {
        CustomersCriteria criteria = new CustomersCriteria();
		criteria.CustomerID = item.getCustomerID();
        repository.Delete(criteria);
    }

    public LiveData<List<CustomersDto>> getItems() { return data; }
}