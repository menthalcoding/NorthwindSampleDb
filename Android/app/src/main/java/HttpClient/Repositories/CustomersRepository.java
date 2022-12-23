package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ICustomersClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.CustomersRequest;
import HttpClient.Messages.CustomersResponse;
import HttpClient.Messages.Criteria.CustomersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ICustomersRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersRepository extends RepositoryBase implements ICustomersRepository {
    ICustomersClient service;
    CustomersRequest request;
    String access_token;
    public int StatusCode;

    public CustomersRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ICustomersClient.class, accessToken);
        request = new CustomersRequest();
        request.Criteria = new CustomersCriteria();
    }

    @Override
    public List<CustomersDto> GetList(Criteria criterion) {

        Call<List<CustomersDto>> call = service.GetCustomersList(access_token);
        List<CustomersDto> body = null;
        try {
            Response<List<CustomersDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public CustomersDto Get(Criteria criterion) {

        CustomersRequest request = new CustomersRequest();
        request.LoadOptions = new String[] { "Customers" };
        request.Criteria = (CustomersCriteria)criterion;

        CustomersDto result = null;
        Call<CustomersDto> call = service.Get(access_token, request.Criteria.CustomerID);
        try {
            Response<CustomersDto> response = call.execute();
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
    public int Post(CustomersDto _CustomersDto) {

        //CustomersRequest request = new CustomersRequest();
        //request.Customers = _CustomersDto;

        CustomersDto result = null;
        Call<CustomersDto> call = service.Post(access_token, _CustomersDto);
        try {
            Response<CustomersDto> response = call.execute();
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
    public void Put(CustomersDto _CustomersDto) {

        //CustomersRequest request = new CustomersRequest();
        //request.Customers = _CustomersDto;

        CustomersDto result = null;
        Call<CustomersDto> call = service.Put(access_token, _CustomersDto);
        try {
            Response<CustomersDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        CustomersCriteria criteria = (CustomersCriteria)criterion;
        CustomersDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.CustomerID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}