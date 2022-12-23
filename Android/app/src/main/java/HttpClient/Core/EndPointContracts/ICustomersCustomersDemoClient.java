package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersCustomersDemoDto;
import HttpClient.Messages.CustomersCustomersDemoRequest;
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

public interface ICustomersCustomersDemoClient {
    @GET("CustomersCustomersDemo/{CustomerID}/{CustomersTypeId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersCustomersDemoDto> Get(@Header("Authorization") String token, @Path("CustomerID") String CustomerID, @Path("CustomersTypeId") String CustomersTypeId);

    @GET("CustomersCustomersDemo")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<CustomersCustomersDemoDto>> GetCustomersCustomersDemoList(@Header("Authorization") String token);

    @POST("CustomersCustomersDemo/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersCustomersDemoDto> Post(@Header("Authorization") String token, @Body CustomersCustomersDemoDto data);

    @POST("CustomersCustomersDemo/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersCustomersDemoDto> Put(@Header("Authorization") String token, @Body CustomersCustomersDemoDto data);

    @POST("CustomersCustomersDemo/delete/{CustomerID}/{CustomersTypeId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("CustomerID") String CustomerID, @Path("CustomersTypeId") String CustomersTypeId);
}