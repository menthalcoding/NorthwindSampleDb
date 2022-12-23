package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ITerritoriesClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.TerritoriesRequest;
import HttpClient.Messages.TerritoriesResponse;
import HttpClient.Messages.Criteria.TerritoriesCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ITerritoriesRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerritoriesRepository extends RepositoryBase implements ITerritoriesRepository {
    ITerritoriesClient service;
    TerritoriesRequest request;
    String access_token;
    public int StatusCode;

    public TerritoriesRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ITerritoriesClient.class, accessToken);
        request = new TerritoriesRequest();
        request.Criteria = new TerritoriesCriteria();
    }

    @Override
    public List<TerritoriesDto> GetList(Criteria criterion) {

        Call<List<TerritoriesDto>> call = service.GetTerritoriesList(access_token);
        List<TerritoriesDto> body = null;
        try {
            Response<List<TerritoriesDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public TerritoriesDto Get(Criteria criterion) {

        TerritoriesRequest request = new TerritoriesRequest();
        request.LoadOptions = new String[] { "Territories" };
        request.Criteria = (TerritoriesCriteria)criterion;

        TerritoriesDto result = null;
        Call<TerritoriesDto> call = service.Get(access_token, request.Criteria.TerritoryID);
        try {
            Response<TerritoriesDto> response = call.execute();
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
    public int Post(TerritoriesDto _TerritoriesDto) {

        //TerritoriesRequest request = new TerritoriesRequest();
        //request.Territories = _TerritoriesDto;

        TerritoriesDto result = null;
        Call<TerritoriesDto> call = service.Post(access_token, _TerritoriesDto);
        try {
            Response<TerritoriesDto> response = call.execute();
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
    public void Put(TerritoriesDto _TerritoriesDto) {

        //TerritoriesRequest request = new TerritoriesRequest();
        //request.Territories = _TerritoriesDto;

        TerritoriesDto result = null;
        Call<TerritoriesDto> call = service.Put(access_token, _TerritoriesDto);
        try {
            Response<TerritoriesDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        TerritoriesCriteria criteria = (TerritoriesCriteria)criterion;
        TerritoriesDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.TerritoryID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}