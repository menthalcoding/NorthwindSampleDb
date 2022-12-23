package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.OrderDetailsDto;
import HttpClient.Messages.OrderDetailsRequest;
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

public interface IOrderDetailsClient {
    @GET("OrderDetails/{OrderId}/{ProductId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDetailsDto> Get(@Header("Authorization") String token, @Path("OrderId") Integer OrderId, @Path("ProductId") Integer ProductId);

    @GET("OrderDetails")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<OrderDetailsDto>> GetOrderDetailsList(@Header("Authorization") String token);

    @POST("OrderDetails/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDetailsDto> Post(@Header("Authorization") String token, @Body OrderDetailsDto data);

    @POST("OrderDetails/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<OrderDetailsDto> Put(@Header("Authorization") String token, @Body OrderDetailsDto data);

    @POST("OrderDetails/delete/{OrderId}/{ProductId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("OrderId") Integer OrderId, @Path("ProductId") Integer ProductId);
}