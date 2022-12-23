package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IEmployeesTerritoriesClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.EmployeesTerritoriesRequest;
import HttpClient.Messages.EmployeesTerritoriesResponse;
import HttpClient.Messages.Criteria.EmployeesTerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IEmployeesTerritoriesRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeesTerritoriesRepository extends RepositoryBase implements IEmployeesTerritoriesRepository {
    IEmployeesTerritoriesClient service;
    EmployeesTerritoriesRequest request;
    String access_token;
    public int StatusCode;

    public EmployeesTerritoriesRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IEmployeesTerritoriesClient.class, accessToken);
        request = new EmployeesTerritoriesRequest();
        request.Criteria = new EmployeesTerritoriesCriteria();
    }

    @Override
    public List<EmployeesTerritoriesDto> GetList(Criteria criterion) {

        Call<List<EmployeesTerritoriesDto>> call = service.GetEmployeesTerritoriesList(access_token);
        List<EmployeesTerritoriesDto> body = null;
        try {
            Response<List<EmployeesTerritoriesDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public EmployeesTerritoriesDto Get(Criteria criterion) {

        EmployeesTerritoriesRequest request = new EmployeesTerritoriesRequest();
        request.LoadOptions = new String[] { "EmployeesTerritories" };
        request.Criteria = (EmployeesTerritoriesCriteria)criterion;

        EmployeesTerritoriesDto result = null;
        Call<EmployeesTerritoriesDto> call = service.Get(access_token, request.Criteria.EmpleymontId, request.Criteria.TerritoryID);
        try {
            Response<EmployeesTerritoriesDto> response = call.execute();
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
    public int Post(EmployeesTerritoriesDto _EmployeesTerritoriesDto) {

        //EmployeesTerritoriesRequest request = new EmployeesTerritoriesRequest();
        //request.EmployeesTerritories = _EmployeesTerritoriesDto;

        EmployeesTerritoriesDto result = null;
        Call<EmployeesTerritoriesDto> call = service.Post(access_token, _EmployeesTerritoriesDto);
        try {
            Response<EmployeesTerritoriesDto> response = call.execute();
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
    public void Put(EmployeesTerritoriesDto _EmployeesTerritoriesDto) {

        //EmployeesTerritoriesRequest request = new EmployeesTerritoriesRequest();
        //request.EmployeesTerritories = _EmployeesTerritoriesDto;

        EmployeesTerritoriesDto result = null;
        Call<EmployeesTerritoriesDto> call = service.Put(access_token, _EmployeesTerritoriesDto);
        try {
            Response<EmployeesTerritoriesDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        EmployeesTerritoriesCriteria criteria = (EmployeesTerritoriesCriteria)criterion;
        EmployeesTerritoriesDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.EmpleymontId, criteria.TerritoryID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}