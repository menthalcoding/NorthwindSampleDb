package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.ProductsDto;
import HttpClient.Messages.ProductsRequest;
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

public interface IProductsClient {
    @GET("Products/{ProductId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ProductsDto> Get(@Header("Authorization") String token, @Path("ProductId") Integer ProductId);

    @GET("Products")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<ProductsDto>> GetProductsList(@Header("Authorization") String token);

    @POST("Products/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ProductsDto> Post(@Header("Authorization") String token, @Body ProductsDto data);

    @POST("Products/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<ProductsDto> Put(@Header("Authorization") String token, @Body ProductsDto data);

    @POST("Products/delete/{ProductId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("ProductId") Integer ProductId);
}