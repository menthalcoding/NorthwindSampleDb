package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.TerritoriesDto;
import HttpClient.Messages.TerritoriesRequest;
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

public interface ITerritoriesClient {
    @GET("Territories/{TerritoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<TerritoriesDto> Get(@Header("Authorization") String token, @Path("TerritoryID") String TerritoryID);

    @GET("Territories")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<TerritoriesDto>> GetTerritoriesList(@Header("Authorization") String token);

    @POST("Territories/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<TerritoriesDto> Post(@Header("Authorization") String token, @Body TerritoriesDto data);

    @POST("Territories/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<TerritoriesDto> Put(@Header("Authorization") String token, @Body TerritoriesDto data);

    @POST("Territories/delete/{TerritoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("TerritoryID") String TerritoryID);
}