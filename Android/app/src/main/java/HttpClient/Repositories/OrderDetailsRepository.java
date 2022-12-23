package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IOrderDetailsClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.OrderDetailsRequest;
import HttpClient.Messages.OrderDetailsResponse;
import HttpClient.Messages.Criteria.OrderDetailsCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IOrderDetailsRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsRepository extends RepositoryBase implements IOrderDetailsRepository {
    IOrderDetailsClient service;
    OrderDetailsRequest request;
    String access_token;
    public int StatusCode;

    public OrderDetailsRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IOrderDetailsClient.class, accessToken);
        request = new OrderDetailsRequest();
        request.Criteria = new OrderDetailsCriteria();
    }

    @Override
    public List<OrderDetailsDto> GetList(Criteria criterion) {

        Call<List<OrderDetailsDto>> call = service.GetOrderDetailsList(access_token);
        List<OrderDetailsDto> body = null;
        try {
            Response<List<OrderDetailsDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public OrderDetailsDto Get(Criteria criterion) {

        OrderDetailsRequest request = new OrderDetailsRequest();
        request.LoadOptions = new String[] { "OrderDetails" };
        request.Criteria = (OrderDetailsCriteria)criterion;

        OrderDetailsDto result = null;
        Call<OrderDetailsDto> call = service.Get(access_token, request.Criteria.OrderId, request.Criteria.ProductId);
        try {
            Response<OrderDetailsDto> response = call.execute();
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
    public int Post(OrderDetailsDto _OrderDetailsDto) {

        //OrderDetailsRequest request = new OrderDetailsRequest();
        //request.OrderDetails = _OrderDetailsDto;

        OrderDetailsDto result = null;
        Call<OrderDetailsDto> call = service.Post(access_token, _OrderDetailsDto);
        try {
            Response<OrderDetailsDto> response = call.execute();
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
    public void Put(OrderDetailsDto _OrderDetailsDto) {

        //OrderDetailsRequest request = new OrderDetailsRequest();
        //request.OrderDetails = _OrderDetailsDto;

        OrderDetailsDto result = null;
        Call<OrderDetailsDto> call = service.Put(access_token, _OrderDetailsDto);
        try {
            Response<OrderDetailsDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        OrderDetailsCriteria criteria = (OrderDetailsCriteria)criterion;
        OrderDetailsDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.OrderId, criteria.ProductId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}