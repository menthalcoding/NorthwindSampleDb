package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.RegionDto;
import HttpClient.Messages.RegionRequest;
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

public interface IRegionClient {
    @GET("Region/{RegionID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<RegionDto> Get(@Header("Authorization") String token, @Path("RegionID") Integer RegionID);

    @GET("Region")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<RegionDto>> GetRegionList(@Header("Authorization") String token);

    @POST("Region/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<RegionDto> Post(@Header("Authorization") String token, @Body RegionDto data);

    @POST("Region/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<RegionDto> Put(@Header("Authorization") String token, @Body RegionDto data);

    @POST("Region/delete/{RegionID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("RegionID") Integer RegionID);
}