package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Repositories.EmployeesTerritoriesRepository;
import Util.SessionManager;

public class EmployeesTerritoriesViewModel extends AndroidViewModel {
    private EmployeesTerritoriesRepository repository;
    private LiveData<List<EmployeesTerritoriesDto>> data;

    public EmployeesTerritoriesViewModel(@NonNull Application application) {
        super(application);

        repository = new EmployeesTerritoriesRepository(SessionManager.ACCESS_TOKEN);
        EmployeesTerritoriesCriteria criteria = new EmployeesTerritoriesCriteria();
        data = (LiveData<List<EmployeesTerritoriesDto>>) repository.GetList(criteria);
    }

    public EmployeesTerritoriesDto get(Integer EmpleymontId, String TerritoryID) {
        EmployeesTerritoriesCriteria criteria = new EmployeesTerritoriesCriteria();
		criteria.EmpleymontId = EmpleymontId;
		criteria.TerritoryID = TerritoryID;
        return repository.Get(criteria);
    }

    public long insert(EmployeesTerritoriesDto item) {
        return repository.Post(item);
    }

    public void update(EmployeesTerritoriesDto item) {
        repository.Put(item);
    }

    public void delete(EmployeesTerritoriesDto item) {
        EmployeesTerritoriesCriteria criteria = new EmployeesTerritoriesCriteria();
		criteria.EmpleymontId = item.getEmpleymontId();
		criteria.TerritoryID = item.getTerritoryID();
        repository.Delete(criteria);
    }

    public LiveData<List<EmployeesTerritoriesDto>> getItems() { return data; }
}