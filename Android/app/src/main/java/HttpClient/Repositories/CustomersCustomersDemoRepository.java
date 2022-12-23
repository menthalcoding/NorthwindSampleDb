package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ICustomersCustomersDemoClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.CustomersCustomersDemoRequest;
import HttpClient.Messages.CustomersCustomersDemoResponse;
import HttpClient.Messages.Criteria.CustomersCustomersDemoCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ICustomersCustomersDemoRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersCustomersDemoRepository extends RepositoryBase implements ICustomersCustomersDemoRepository {
    ICustomersCustomersDemoClient service;
    CustomersCustomersDemoRequest request;
    String access_token;
    public int StatusCode;

    public CustomersCustomersDemoRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ICustomersCustomersDemoClient.class, accessToken);
        request = new CustomersCustomersDemoRequest();
        request.Criteria = new CustomersCustomersDemoCriteria();
    }

    @Override
    public List<CustomersCustomersDemoDto> GetList(Criteria criterion) {

        Call<List<CustomersCustomersDemoDto>> call = service.GetCustomersCustomersDemoList(access_token);
        List<CustomersCustomersDemoDto> body = null;
        try {
            Response<List<CustomersCustomersDemoDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public CustomersCustomersDemoDto Get(Criteria criterion) {

        CustomersCustomersDemoRequest request = new CustomersCustomersDemoRequest();
        request.LoadOptions = new String[] { "CustomersCustomersDemo" };
        request.Criteria = (CustomersCustomersDemoCriteria)criterion;

        CustomersCustomersDemoDto result = null;
        Call<CustomersCustomersDemoDto> call = service.Get(access_token, request.Criteria.CustomerID, request.Criteria.CustomersTypeId);
        try {
            Response<CustomersCustomersDemoDto> response = call.execute();
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
    public int Post(CustomersCustomersDemoDto _CustomersCustomersDemoDto) {

        //CustomersCustomersDemoRequest request = new CustomersCustomersDemoRequest();
        //request.CustomersCustomersDemo = _CustomersCustomersDemoDto;

        CustomersCustomersDemoDto result = null;
        Call<CustomersCustomersDemoDto> call = service.Post(access_token, _CustomersCustomersDemoDto);
        try {
            Response<CustomersCustomersDemoDto> response = call.execute();
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
    public void Put(CustomersCustomersDemoDto _CustomersCustomersDemoDto) {

        //CustomersCustomersDemoRequest request = new CustomersCustomersDemoRequest();
        //request.CustomersCustomersDemo = _CustomersCustomersDemoDto;

        CustomersCustomersDemoDto result = null;
        Call<CustomersCustomersDemoDto> call = service.Put(access_token, _CustomersCustomersDemoDto);
        try {
            Response<CustomersCustomersDemoDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        CustomersCustomersDemoCriteria criteria = (CustomersCustomersDemoCriteria)criterion;
        CustomersCustomersDemoDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.CustomerID, criteria.CustomersTypeId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}