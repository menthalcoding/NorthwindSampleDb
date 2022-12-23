package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Repositories.RegionRepository;
import Util.SessionManager;

public class RegionViewModel extends AndroidViewModel {
    private RegionRepository repository;
    private LiveData<List<RegionDto>> data;

    public RegionViewModel(@NonNull Application application) {
        super(application);

        repository = new RegionRepository(SessionManager.ACCESS_TOKEN);
        RegionCriteria criteria = new RegionCriteria();
        data = (LiveData<List<RegionDto>>) repository.GetList(criteria);
    }

    public RegionDto get(Integer RegionID) {
        RegionCriteria criteria = new RegionCriteria();
		criteria.RegionID = RegionID;
        return repository.Get(criteria);
    }

    public long insert(RegionDto item) {
        return repository.Post(item);
    }

    public void update(RegionDto item) {
        repository.Put(item);
    }

    public void delete(RegionDto item) {
        RegionCriteria criteria = new RegionCriteria();
		criteria.RegionID = item.getRegionID();
        repository.Delete(criteria);
    }

    public LiveData<List<RegionDto>> getItems() { return data; }
}