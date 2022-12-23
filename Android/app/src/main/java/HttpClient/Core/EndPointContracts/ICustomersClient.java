package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersDto;
import HttpClient.Messages.CustomersRequest;
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

public interface ICustomersClient {
    @GET("Customers/{CustomerID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDto> Get(@Header("Authorization") String token, @Path("CustomerID") String CustomerID);

    @GET("Customers")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<CustomersDto>> GetCustomersList(@Header("Authorization") String token);

    @POST("Customers/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDto> Post(@Header("Authorization") String token, @Body CustomersDto data);

    @POST("Customers/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDto> Put(@Header("Authorization") String token, @Body CustomersDto data);

    @POST("Customers/delete/{CustomerID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("CustomerID") String CustomerID);
}