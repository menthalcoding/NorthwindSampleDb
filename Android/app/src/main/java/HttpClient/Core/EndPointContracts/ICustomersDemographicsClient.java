package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.CustomersDemographicsDto;
import HttpClient.Messages.CustomersDemographicsRequest;
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

public interface ICustomersDemographicsClient {
    @GET("CustomersDemographics/{CustomersTypeId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDemographicsDto> Get(@Header("Authorization") String token, @Path("CustomersTypeId") String CustomersTypeId);

    @GET("CustomersDemographics")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<CustomersDemographicsDto>> GetCustomersDemographicsList(@Header("Authorization") String token);

    @POST("CustomersDemographics/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDemographicsDto> Post(@Header("Authorization") String token, @Body CustomersDemographicsDto data);

    @POST("CustomersDemographics/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CustomersDemographicsDto> Put(@Header("Authorization") String token, @Body CustomersDemographicsDto data);

    @POST("CustomersDemographics/delete/{CustomersTypeId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("CustomersTypeId") String CustomersTypeId);
}