package HttpClient.Core.EndPointContracts;

import java.util.List;

import HttpClient.DataTransferObjects.SuppliersDto;
import HttpClient.Messages.SuppliersRequest;
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

public interface ISuppliersClient {
    @GET("Suppliers/{SupplierID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<SuppliersDto> Get(@Header("Authorization") String token, @Path("SupplierID") Integer SupplierID);

    @GET("Suppliers")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<List<SuppliersDto>> GetSuppliersList(@Header("Authorization") String token);

    @POST("Suppliers/post")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<SuppliersDto> Post(@Header("Authorization") String token, @Body SuppliersDto data);

    @POST("Suppliers/put")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<SuppliersDto> Put(@Header("Authorization") String token, @Body SuppliersDto data);

    @POST("Suppliers/delete/{SupplierID}")
    @Headers({ "Content-Type: application/json;charset=UTF-8" })
    Call<Integer> Delete(@Header("Authorization") String token, @Path("SupplierID") Integer SupplierID);
}