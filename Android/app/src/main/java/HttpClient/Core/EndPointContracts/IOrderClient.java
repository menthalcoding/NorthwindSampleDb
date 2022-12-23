package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.OrderDto;
import HttpClient.Messages.OrderRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IOrderClient {
    @GET("Order/{OrderId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDto> Get(@Header("Authorization") String token, @Path("OrderId") Integer OrderId);

    @GET("Order")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<OrderDto>> GetOrderList(@Header("Authorization") String token);

    @POST("Order/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDto> Post(@Header("Authorization") String token, @Body OrderDto data);

    @POST("Order/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDto> Put(@Header("Authorization") String token, @Body OrderDto data);

    @POST("Order/delete/{OrderId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("OrderId") Integer OrderId);
}