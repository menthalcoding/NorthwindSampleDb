package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.ShippersDto;
import HttpClient.Messages.ShippersRequest;
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

public interface IShippersClient {
    @GET("Shippers/{ShipperId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ShippersDto> Get(@Header("Authorization") String token, @Path("ShipperId") Integer ShipperId);

    @GET("Shippers")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<ShippersDto>> GetShippersList(@Header("Authorization") String token);

    @POST("Shippers/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ShippersDto> Post(@Header("Authorization") String token, @Body ShippersDto data);

    @POST("Shippers/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ShippersDto> Put(@Header("Authorization") String token, @Body ShippersDto data);

    @POST("Shippers/delete/{ShipperId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("ShipperId") Integer ShipperId);
}