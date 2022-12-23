package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ICategoriesClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.CategoriesRequest;
import HttpClient.Messages.CategoriesResponse;
import HttpClient.Messages.Criteria.CategoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ICategoriesRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesRepository extends RepositoryBase implements ICategoriesRepository {
    ICategoriesClient service;
    CategoriesRequest request;
    String access_token;
    public int StatusCode;

    public CategoriesRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ICategoriesClient.class, accessToken);
        request = new CategoriesRequest();
        request.Criteria = new CategoriesCriteria();
    }

    @Override
    public List<CategoriesDto> GetList(Criteria criterion) {

        Call<List<CategoriesDto>> call = service.GetCategoriesList(access_token);
        List<CategoriesDto> body = null;
        try {
            Response<List<CategoriesDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public CategoriesDto Get(Criteria criterion) {

        CategoriesRequest request = new CategoriesRequest();
        request.LoadOptions = new String[] { "Categories" };
        request.Criteria = (CategoriesCriteria)criterion;

        CategoriesDto result = null;
        Call<CategoriesDto> call = service.Get(access_token, request.Criteria.CategoryID);
        try {
            Response<CategoriesDto> response = call.execute();
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
    public int Post(CategoriesDto _CategoriesDto) {

        //CategoriesRequest request = new CategoriesRequest();
        //request.Categories = _CategoriesDto;

        CategoriesDto result = null;
        Call<CategoriesDto> call = service.Post(access_token, _CategoriesDto);
        try {
            Response<CategoriesDto> response = call.execute();
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
    public void Put(CategoriesDto _CategoriesDto) {

        //CategoriesRequest request = new CategoriesRequest();
        //request.Categories = _CategoriesDto;

        CategoriesDto result = null;
        Call<CategoriesDto> call = service.Put(access_token, _CategoriesDto);
        try {
            Response<CategoriesDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        CategoriesCriteria criteria = (CategoriesCriteria)criterion;
        CategoriesDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.CategoryID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}