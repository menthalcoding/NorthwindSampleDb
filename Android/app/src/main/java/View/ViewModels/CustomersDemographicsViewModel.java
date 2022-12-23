package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Repositories.CustomersDemographicsRepository;
import Util.SessionManager;

public class CustomersDemographicsViewModel extends AndroidViewModel {
    private CustomersDemographicsRepository repository;
    private LiveData<List<CustomersDemographicsDto>> data;

    public CustomersDemographicsViewModel(@NonNull Application application) {
        super(application);

        repository = new CustomersDemographicsRepository(SessionManager.ACCESS_TOKEN);
        CustomersDemographicsCriteria criteria = new CustomersDemographicsCriteria();
        data = (LiveData<List<CustomersDemographicsDto>>) repository.GetList(criteria);
    }

    public CustomersDemographicsDto get(String CustomersTypeId) {
        CustomersDemographicsCriteria criteria = new CustomersDemographicsCriteria();
		criteria.CustomersTypeId = CustomersTypeId;
        return repository.Get(criteria);
    }

    public long insert(CustomersDemographicsDto item) {
        return repository.Post(item);
    }

    public void update(CustomersDemographicsDto item) {
        repository.Put(item);
    }

    public void delete(CustomersDemographicsDto item) {
        CustomersDemographicsCriteria criteria = new CustomersDemographicsCriteria();
		criteria.CustomersTypeId = item.getCustomersTypeId();
        repository.Delete(criteria);
    }

    public LiveData<List<CustomersDemographicsDto>> getItems() { return data; }
}