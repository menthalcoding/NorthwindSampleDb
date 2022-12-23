package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IShippersClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.ShippersRequest;
import HttpClient.Messages.ShippersResponse;
import HttpClient.Messages.Criteria.ShippersCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IShippersRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippersRepository extends RepositoryBase implements IShippersRepository {
    IShippersClient service;
    ShippersRequest request;
    String access_token;
    public int StatusCode;

    public ShippersRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IShippersClient.class, accessToken);
        request = new ShippersRequest();
        request.Criteria = new ShippersCriteria();
    }

    @Override
    public List<ShippersDto> GetList(Criteria criterion) {

        Call<List<ShippersDto>> call = service.GetShippersList(access_token);
        List<ShippersDto> body = null;
        try {
            Response<List<ShippersDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public ShippersDto Get(Criteria criterion) {

        ShippersRequest request = new ShippersRequest();
        request.LoadOptions = new String[] { "Shippers" };
        request.Criteria = (ShippersCriteria)criterion;

        ShippersDto result = null;
        Call<ShippersDto> call = service.Get(access_token, request.Criteria.ShipperId);
        try {
            Response<ShippersDto> response = call.execute();
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
    public int Post(ShippersDto _ShippersDto) {

        //ShippersRequest request = new ShippersRequest();
        //request.Shippers = _ShippersDto;

        ShippersDto result = null;
        Call<ShippersDto> call = service.Post(access_token, _ShippersDto);
        try {
            Response<ShippersDto> response = call.execute();
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
    public void Put(ShippersDto _ShippersDto) {

        //ShippersRequest request = new ShippersRequest();
        //request.Shippers = _ShippersDto;

        ShippersDto result = null;
        Call<ShippersDto> call = service.Put(access_token, _ShippersDto);
        try {
            Response<ShippersDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        ShippersCriteria criteria = (ShippersCriteria)criterion;
        ShippersDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.ShipperId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}