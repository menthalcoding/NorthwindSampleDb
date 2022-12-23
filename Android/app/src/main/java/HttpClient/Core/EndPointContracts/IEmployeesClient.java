package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.EmployeesDto;
import HttpClient.Messages.EmployeesRequest;
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

public interface IEmployeesClient {
    @GET("Employees/{EmployeeID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesDto> Get(@Header("Authorization") String token, @Path("EmployeeID") Integer EmployeeID);

    @GET("Employees")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<EmployeesDto>> GetEmployeesList(@Header("Authorization") String token);

    @POST("Employees/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesDto> Post(@Header("Authorization") String token, @Body EmployeesDto data);

    @POST("Employees/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesDto> Put(@Header("Authorization") String token, @Body EmployeesDto data);

    @POST("Employees/delete/{EmployeeID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("EmployeeID") Integer EmployeeID);
}