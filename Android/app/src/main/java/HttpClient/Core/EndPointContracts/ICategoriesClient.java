package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.CategoriesDto;
import HttpClient.Messages.CategoriesRequest;
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

public interface ICategoriesClient {
    @GET("Categories/{CategoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CategoriesDto> Get(@Header("Authorization") String token, @Path("CategoryID") Integer CategoryID);

    @GET("Categories")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<CategoriesDto>> GetCategoriesList(@Header("Authorization") String token);

    @POST("Categories/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CategoriesDto> Post(@Header("Authorization") String token, @Body CategoriesDto data);

    @POST("Categories/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<CategoriesDto> Put(@Header("Authorization") String token, @Body CategoriesDto data);

    @POST("Categories/delete/{CategoryID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("CategoryID") Integer CategoryID);
}