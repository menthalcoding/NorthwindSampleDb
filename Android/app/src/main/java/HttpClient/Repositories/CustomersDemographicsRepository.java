package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ICustomersDemographicsClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.CustomersDemographicsRequest;
import HttpClient.Messages.CustomersDemographicsResponse;
import HttpClient.Messages.Criteria.CustomersDemographicsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ICustomersDemographicsRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersDemographicsRepository extends RepositoryBase implements ICustomersDemographicsRepository {
    ICustomersDemographicsClient service;
    CustomersDemographicsRequest request;
    String access_token;
    public int StatusCode;

    public CustomersDemographicsRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ICustomersDemographicsClient.class, accessToken);
        request = new CustomersDemographicsRequest();
        request.Criteria = new CustomersDemographicsCriteria();
    }

    @Override
    public List<CustomersDemographicsDto> GetList(Criteria criterion) {

        Call<List<CustomersDemographicsDto>> call = service.GetCustomersDemographicsList(access_token);
        List<CustomersDemographicsDto> body = null;
        try {
            Response<List<CustomersDemographicsDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public CustomersDemographicsDto Get(Criteria criterion) {

        CustomersDemographicsRequest request = new CustomersDemographicsRequest();
        request.LoadOptions = new String[] { "CustomersDemographics" };
        request.Criteria = (CustomersDemographicsCriteria)criterion;

        CustomersDemographicsDto result = null;
        Call<CustomersDemographicsDto> call = service.Get(access_token, request.Criteria.CustomersTypeId);
        try {
            Response<CustomersDemographicsDto> response = call.execute();
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
    public int Post(CustomersDemographicsDto _CustomersDemographicsDto) {

        //CustomersDemographicsRequest request = new CustomersDemographicsRequest();
        //request.CustomersDemographics = _CustomersDemographicsDto;

        CustomersDemographicsDto result = null;
        Call<CustomersDemographicsDto> call = service.Post(access_token, _CustomersDemographicsDto);
        try {
            Response<CustomersDemographicsDto> response = call.execute();
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
    public void Put(CustomersDemographicsDto _CustomersDemographicsDto) {

        //CustomersDemographicsRequest request = new CustomersDemographicsRequest();
        //request.CustomersDemographics = _CustomersDemographicsDto;

        CustomersDemographicsDto result = null;
        Call<CustomersDemographicsDto> call = service.Put(access_token, _CustomersDemographicsDto);
        try {
            Response<CustomersDemographicsDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        CustomersDemographicsCriteria criteria = (CustomersDemographicsCriteria)criterion;
        CustomersDemographicsDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.CustomersTypeId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}