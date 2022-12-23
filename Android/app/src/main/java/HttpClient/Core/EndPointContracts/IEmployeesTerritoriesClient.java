package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.EmployeesTerritoriesDto;
import HttpClient.Messages.EmployeesTerritoriesRequest;
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

public interface IEmployeesTerritoriesClient {
    @GET("EmployeesTerritories/{EmpleymontId}/{TerritoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesTerritoriesDto> Get(@Header("Authorization") String token, @Path("EmpleymontId") Integer EmpleymontId, @Path("TerritoryID") String TerritoryID);

    @GET("EmployeesTerritories")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<EmployeesTerritoriesDto>> GetEmployeesTerritoriesList(@Header("Authorization") String token);

    @POST("EmployeesTerritories/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesTerritoriesDto> Post(@Header("Authorization") String token, @Body EmployeesTerritoriesDto data);

    @POST("EmployeesTerritories/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<EmployeesTerritoriesDto> Put(@Header("Authorization") String token, @Body EmployeesTerritoriesDto data);

    @POST("EmployeesTerritories/delete/{EmpleymontId}/{TerritoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("EmpleymontId") Integer EmpleymontId, @Path("TerritoryID") String TerritoryID);
}