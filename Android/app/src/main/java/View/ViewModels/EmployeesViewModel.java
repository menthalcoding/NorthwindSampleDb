package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Repositories.EmployeesRepository;
import Util.SessionManager;

public class EmployeesViewModel extends AndroidViewModel {
    private EmployeesRepository repository;
    private LiveData<List<EmployeesDto>> data;

    public EmployeesViewModel(@NonNull Application application) {
        super(application);

        repository = new EmployeesRepository(SessionManager.ACCESS_TOKEN);
        EmployeesCriteria criteria = new EmployeesCriteria();
        data = (LiveData<List<EmployeesDto>>) repository.GetList(criteria);
    }

    public EmployeesDto get(Integer EmployeeID) {
        EmployeesCriteria criteria = new EmployeesCriteria();
		criteria.EmployeeID = EmployeeID;
        return repository.Get(criteria);
    }

    public long insert(EmployeesDto item) {
        return repository.Post(item);
    }

    public void update(EmployeesDto item) {
        repository.Put(item);
    }

    public void delete(EmployeesDto item) {
        EmployeesCriteria criteria = new EmployeesCriteria();
		criteria.EmployeeID = item.getEmployeeID();
        repository.Delete(criteria);
    }

    public LiveData<List<EmployeesDto>> getItems() { return data; }
}