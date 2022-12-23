package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Repositories.TerritoriesRepository;
import Util.SessionManager;

public class TerritoriesViewModel extends AndroidViewModel {
    private TerritoriesRepository repository;
    private LiveData<List<TerritoriesDto>> data;

    public TerritoriesViewModel(@NonNull Application application) {
        super(application);

        repository = new TerritoriesRepository(SessionManager.ACCESS_TOKEN);
        TerritoriesCriteria criteria = new TerritoriesCriteria();
        data = (LiveData<List<TerritoriesDto>>) repository.GetList(criteria);
    }

    public TerritoriesDto get(String TerritoryID) {
        TerritoriesCriteria criteria = new TerritoriesCriteria();
		criteria.TerritoryID = TerritoryID;
        return repository.Get(criteria);
    }

    public long insert(TerritoriesDto item) {
        return repository.Post(item);
    }

    public void update(TerritoriesDto item) {
        repository.Put(item);
    }

    public void delete(TerritoriesDto item) {
        TerritoriesCriteria criteria = new TerritoriesCriteria();
		criteria.TerritoryID = item.getTerritoryID();
        repository.Delete(criteria);
    }

    public LiveData<List<TerritoriesDto>> getItems() { return data; }
}