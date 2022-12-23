package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Repositories.CustomersCustomersDemoRepository;
import Util.SessionManager;

public class CustomersCustomersDemoViewModel extends AndroidViewModel {
    private CustomersCustomersDemoRepository repository;
    private LiveData<List<CustomersCustomersDemoDto>> data;

    public CustomersCustomersDemoViewModel(@NonNull Application application) {
        super(application);

        repository = new CustomersCustomersDemoRepository(SessionManager.ACCESS_TOKEN);
        CustomersCustomersDemoCriteria criteria = new CustomersCustomersDemoCriteria();
        data = (LiveData<List<CustomersCustomersDemoDto>>) repository.GetList(criteria);
    }

    public CustomersCustomersDemoDto get(String CustomerID, String CustomersTypeId) {
        CustomersCustomersDemoCriteria criteria = new CustomersCustomersDemoCriteria();
		criteria.CustomerID = CustomerID;
		criteria.CustomersTypeId = CustomersTypeId;
        return repository.Get(criteria);
    }

    public long insert(CustomersCustomersDemoDto item) {
        return repository.Post(item);
    }

    public void update(CustomersCustomersDemoDto item) {
        repository.Put(item);
    }

    public void delete(CustomersCustomersDemoDto item) {
        CustomersCustomersDemoCriteria criteria = new CustomersCustomersDemoCriteria();
		criteria.CustomerID = item.getCustomerID();
		criteria.CustomersTypeId = item.getCustomersTypeId();
        repository.Delete(criteria);
    }

    public LiveData<List<CustomersCustomersDemoDto>> getItems() { return data; }
}