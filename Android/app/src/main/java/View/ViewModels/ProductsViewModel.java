package View.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Repositories.ProductsRepository;
import Util.SessionManager;

public class ProductsViewModel extends AndroidViewModel {
    private ProductsRepository repository;
    private LiveData<List<ProductsDto>> data;

    public ProductsViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductsRepository(SessionManager.ACCESS_TOKEN);
        ProductsCriteria criteria = new ProductsCriteria();
        data = (LiveData<List<ProductsDto>>) repository.GetList(criteria);
    }

    public ProductsDto get(Integer ProductId) {
        ProductsCriteria criteria = new ProductsCriteria();
		criteria.ProductId = ProductId;
        return repository.Get(criteria);
    }

    public long insert(ProductsDto item) {
        return repository.Post(item);
    }

    public void update(ProductsDto item) {
        repository.Put(item);
    }

    public void delete(ProductsDto item) {
        ProductsCriteria criteria = new ProductsCriteria();
		criteria.ProductId = item.getProductId();
        repository.Delete(criteria);
    }

    public LiveData<List<ProductsDto>> getItems() { return data; }
}