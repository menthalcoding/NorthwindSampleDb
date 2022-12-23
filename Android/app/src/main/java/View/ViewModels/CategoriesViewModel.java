package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Repositories.CategoriesRepository;
import Util.SessionManager;

public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository repository;
    private LiveData<List<CategoriesDto>> data;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);

        repository = new CategoriesRepository(SessionManager.ACCESS_TOKEN);
        CategoriesCriteria criteria = new CategoriesCriteria();
        data = (LiveData<List<CategoriesDto>>) repository.GetList(criteria);
    }

    public CategoriesDto get(Integer CategoryID) {
        CategoriesCriteria criteria = new CategoriesCriteria();
		criteria.CategoryID = CategoryID;
        return repository.Get(criteria);
    }

    public long insert(CategoriesDto item) {
        return repository.Post(item);
    }

    public void update(CategoriesDto item) {
        repository.Put(item);
    }

    public void delete(CategoriesDto item) {
        CategoriesCriteria criteria = new CategoriesCriteria();
		criteria.CategoryID = item.getCategoryID();
        repository.Delete(criteria);
    }

    public LiveData<List<CategoriesDto>> getItems() { return data; }
}