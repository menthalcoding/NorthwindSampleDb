package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IProductsClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.ProductsRequest;
import HttpClient.Messages.ProductsResponse;
import HttpClient.Messages.Criteria.ProductsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IProductsRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository extends RepositoryBase implements IProductsRepository {
    IProductsClient service;
    ProductsRequest request;
    String access_token;
    public int StatusCode;

    public ProductsRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IProductsClient.class, accessToken);
        request = new ProductsRequest();
        request.Criteria = new ProductsCriteria();
    }

    @Override
    public List<ProductsDto> GetList(Criteria criterion) {

        Call<List<ProductsDto>> call = service.GetProductsList(access_token);
        List<ProductsDto> body = null;
        try {
            Response<List<ProductsDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public ProductsDto Get(Criteria criterion) {

        ProductsRequest request = new ProductsRequest();
        request.LoadOptions = new String[] { "Products" };
        request.Criteria = (ProductsCriteria)criterion;

        ProductsDto result = null;
        Call<ProductsDto> call = service.Get(access_token, request.Criteria.ProductId);
        try {
            Response<ProductsDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }

    @Override
    public int GetCount(Criteria criterion) {
        return 0;
    }

    @Override
    public int Post(ProductsDto _ProductsDto) {

        //ProductsRequest request = new ProductsRequest();
        //request.Products = _ProductsDto;

        ProductsDto result = null;
        Call<ProductsDto> call = service.Post(access_token, _ProductsDto);
        try {
            Response<ProductsDto> response = call.execute();
            if (response.isSuccessful() && response.code() == 200) {
                return 1;
            }
            //result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void Put(ProductsDto _ProductsDto) {

        //ProductsRequest request = new ProductsRequest();
        //request.Products = _ProductsDto;

        ProductsDto result = null;
        Call<ProductsDto> call = service.Put(access_token, _ProductsDto);
        try {
            Response<ProductsDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        ProductsCriteria criteria = (ProductsCriteria)criterion;
        ProductsDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.ProductId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}