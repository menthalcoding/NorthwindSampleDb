package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.ISuppliersClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.SuppliersRequest;
import HttpClient.Messages.SuppliersResponse;
import HttpClient.Messages.Criteria.SuppliersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.ISuppliersRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuppliersRepository extends RepositoryBase implements ISuppliersRepository {
    ISuppliersClient service;
    SuppliersRequest request;
    String access_token;
    public int StatusCode;

    public SuppliersRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(ISuppliersClient.class, accessToken);
        request = new SuppliersRequest();
        request.Criteria = new SuppliersCriteria();
    }

    @Override
    public List<SuppliersDto> GetList(Criteria criterion) {

        Call<List<SuppliersDto>> call = service.GetSuppliersList(access_token);
        List<SuppliersDto> body = null;
        try {
            Response<List<SuppliersDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public SuppliersDto Get(Criteria criterion) {

        SuppliersRequest request = new SuppliersRequest();
        request.LoadOptions = new String[] { "Suppliers" };
        request.Criteria = (SuppliersCriteria)criterion;

        SuppliersDto result = null;
        Call<SuppliersDto> call = service.Get(access_token, request.Criteria.SupplierID);
        try {
            Response<SuppliersDto> response = call.execute();
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
    public int Post(SuppliersDto _SuppliersDto) {

        //SuppliersRequest request = new SuppliersRequest();
        //request.Suppliers = _SuppliersDto;

        SuppliersDto result = null;
        Call<SuppliersDto> call = service.Post(access_token, _SuppliersDto);
        try {
            Response<SuppliersDto> response = call.execute();
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
    public void Put(SuppliersDto _SuppliersDto) {

        //SuppliersRequest request = new SuppliersRequest();
        //request.Suppliers = _SuppliersDto;

        SuppliersDto result = null;
        Call<SuppliersDto> call = service.Put(access_token, _SuppliersDto);
        try {
            Response<SuppliersDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        SuppliersCriteria criteria = (SuppliersCriteria)criterion;
        SuppliersDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.SupplierID);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}