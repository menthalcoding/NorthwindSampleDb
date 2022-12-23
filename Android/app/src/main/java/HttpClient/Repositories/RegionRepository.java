package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IRegionClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.RegionRequest;
import HttpClient.Messages.RegionResponse;
import HttpClient.Messages.Criteria.RegionCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IRegionRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionRepository extends RepositoryBase implements IRegionRepository {
    IRegionClient service;
    RegionRequest request;
    String access_token;
    public int StatusCode;

    public RegionRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IRegionClient.class, accessToken);
        request = new RegionRequest();
        request.Criteria = new RegionCriteria();
    }

    @Override
    public List<RegionDto> GetList(Criteria criterion) {

        Call<List<RegionDto>> call = service.GetRegionList(access_token);
        List<RegionDto> body = null;
        try {
            Response<List<RegionDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public RegionDto Get(Criteria criterion) {

        RegionRequest request = new RegionRequest();
        request.LoadOptions = new String[] { "Region" };
        request.Criteria = (RegionCriteria)criterion;

        RegionDto result = null;
        Call<RegionDto> call = service.Get(access_token, request.Criteria.RegionID);
        try {
            Response<RegionDto> response = call.execute();
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
    public int Post(RegionDto _RegionDto) {

        //RegionRequest request = new RegionRequest();
        //request.Region = _RegionDto;

        RegionDto result = null;
        Call<RegionDto> call = service.Post(access_token, _RegionDto);
        try {
            Response<RegionDto> response = call.execute();
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
    public void Put(RegionDto _RegionDto) {

        //RegionRequest request = new RegionRequest();
        //request.Region = _RegionDto;

        RegionDto result = null;
        Call<RegionDto> call = service.Put(access_token, _RegionDto);
        try {
            Response<RegionDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        RegionCriteria criteria = (RegionCriteria)criterion;
        RegionDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.RegionID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}