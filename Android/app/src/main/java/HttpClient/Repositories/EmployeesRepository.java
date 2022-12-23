package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IEmployeesClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.EmployeesRequest;
import HttpClient.Messages.EmployeesResponse;
import HttpClient.Messages.Criteria.EmployeesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IEmployeesRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesRepository extends RepositoryBase implements IEmployeesRepository {
    IEmployeesClient service;
    EmployeesRequest request;
    String access_token;
    public int StatusCode;

    public EmployeesRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IEmployeesClient.class, accessToken);
        request = new EmployeesRequest();
        request.Criteria = new EmployeesCriteria();
    }

    @Override
    public List<EmployeesDto> GetList(Criteria criterion) {

        Call<List<EmployeesDto>> call = service.GetEmployeesList(access_token);
        List<EmployeesDto> body = null;
        try {
            Response<List<EmployeesDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public EmployeesDto Get(Criteria criterion) {

        EmployeesRequest request = new EmployeesRequest();
        request.LoadOptions = new String[] { "Employees" };
        request.Criteria = (EmployeesCriteria)criterion;

        EmployeesDto result = null;
        Call<EmployeesDto> call = service.Get(access_token, request.Criteria.EmployeeID);
        try {
            Response<EmployeesDto> response = call.execute();
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
    public int Post(EmployeesDto _EmployeesDto) {

        //EmployeesRequest request = new EmployeesRequest();
        //request.Employees = _EmployeesDto;

        EmployeesDto result = null;
        Call<EmployeesDto> call = service.Post(access_token, _EmployeesDto);
        try {
            Response<EmployeesDto> response = call.execute();
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
    public void Put(EmployeesDto _EmployeesDto) {

        //EmployeesRequest request = new EmployeesRequest();
        //request.Employees = _EmployeesDto;

        EmployeesDto result = null;
        Call<EmployeesDto> call = service.Put(access_token, _EmployeesDto);
        try {
            Response<EmployeesDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        EmployeesCriteria criteria = (EmployeesCriteria)criterion;
        EmployeesDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.EmployeeID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}