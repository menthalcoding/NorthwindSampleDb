package HttpClient.Repositories;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import HttpClient.Core.EndPointContracts.IOrderClient;
import HttpClient.Core.WebClient;
import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.OrderRequest;
import HttpClient.Messages.OrderResponse;
import HttpClient.Messages.Criteria.OrderCriteria;
import HttpClient.Messages.Criteria.Criteria;
import HttpClient.Repositories.Core.IOrderRepository;
import HttpClient.Repositories.Core.RepositoryBase;
import Util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository extends RepositoryBase implements IOrderRepository {
    IOrderClient service;
    OrderRequest request;
    String access_token;
    public int StatusCode;

    public OrderRepository(String accessToken) {

        access_token = "Bearer " + accessToken;
        service = WebClient.createService(IOrderClient.class, accessToken);
        request = new OrderRequest();
        request.Criteria = new OrderCriteria();
    }

    @Override
    public List<OrderDto> GetList(Criteria criterion) {

        Call<List<OrderDto>> call = service.GetOrderList(access_token);
        List<OrderDto> body = null;
        try {
            Response<List<OrderDto>> response = call.execute();
            StatusCode = response.raw().code();
            body = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    @Override
    public OrderDto Get(Criteria criterion) {

        OrderRequest request = new OrderRequest();
        request.LoadOptions = new String[] { "Order" };
        request.Criteria = (OrderCriteria)criterion;

        OrderDto result = null;
        Call<OrderDto> call = service.Get(access_token, request.Criteria.OrderId);
        try {
            Response<OrderDto> response = call.execute();
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
    public int Post(OrderDto _OrderDto) {

        //OrderRequest request = new OrderRequest();
        //request.Order = _OrderDto;

        OrderDto result = null;
        Call<OrderDto> call = service.Post(access_token, _OrderDto);
        try {
            Response<OrderDto> response = call.execute();
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
    public void Put(OrderDto _OrderDto) {

        //OrderRequest request = new OrderRequest();
        //request.Order = _OrderDto;

        OrderDto result = null;
        Call<OrderDto> call = service.Put(access_token, _OrderDto);
        try {
            Response<OrderDto> response = call.execute();
            result = response.body();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Criteria criterion) {

        OrderCriteria criteria = (OrderCriteria)criterion;
        OrderDto result = null;
        Call<Integer> call = service.Delete(access_token, criteria.OrderId);
        try {
            Response<Integer> response = call.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}