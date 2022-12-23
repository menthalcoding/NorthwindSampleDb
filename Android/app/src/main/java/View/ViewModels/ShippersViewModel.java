package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Repositories.ShippersRepository;
import Util.SessionManager;

public class ShippersViewModel extends AndroidViewModel {
    private ShippersRepository repository;
    private LiveData<List<ShippersDto>> data;

    public ShippersViewModel(@NonNull Application application) {
        super(application);

        repository = new ShippersRepository(SessionManager.ACCESS_TOKEN);
        ShippersCriteria criteria = new ShippersCriteria();
        data = (LiveData<List<ShippersDto>>) repository.GetList(criteria);
    }

    public ShippersDto get(Integer ShipperId) {
        ShippersCriteria criteria = new ShippersCriteria();
		criteria.ShipperId = ShipperId;
        return repository.Get(criteria);
    }

    public long insert(ShippersDto item) {
        return repository.Post(item);
    }

    public void update(ShippersDto item) {
        repository.Put(item);
    }

    public void delete(ShippersDto item) {
        ShippersCriteria criteria = new ShippersCriteria();
		criteria.ShipperId = item.getShipperId();
        repository.Delete(criteria);
    }

    public LiveData<List<ShippersDto>> getItems() { return data; }
}