package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Repositories.SuppliersRepository;
import Util.SessionManager;

public class SuppliersViewModel extends AndroidViewModel {
    private SuppliersRepository repository;
    private LiveData<List<SuppliersDto>> data;

    public SuppliersViewModel(@NonNull Application application) {
        super(application);

        repository = new SuppliersRepository(SessionManager.ACCESS_TOKEN);
        SuppliersCriteria criteria = new SuppliersCriteria();
        data = (LiveData<List<SuppliersDto>>) repository.GetList(criteria);
    }

    public SuppliersDto get(Integer SupplierID) {
        SuppliersCriteria criteria = new SuppliersCriteria();
		criteria.SupplierID = SupplierID;
        return repository.Get(criteria);
    }

    public long insert(SuppliersDto item) {
        return repository.Post(item);
    }

    public void update(SuppliersDto item) {
        repository.Put(item);
    }

    public void delete(SuppliersDto item) {
        SuppliersCriteria criteria = new SuppliersCriteria();
		criteria.SupplierID = item.getSupplierID();
        repository.Delete(criteria);
    }

    public LiveData<List<SuppliersDto>> getItems() { return data; }
}