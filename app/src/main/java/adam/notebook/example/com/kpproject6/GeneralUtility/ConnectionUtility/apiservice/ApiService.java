package adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice;

import java.util.List;

import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import adam.notebook.example.com.kpproject6.model.cart.CartResponse;
import adam.notebook.example.com.kpproject6.model.product.ProductResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {


    @POST("login")
    @FormUrlEncoded
    Call<BaseResponse> login(
            @Field("email") String email,
            @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<BaseResponse> register(@Field("email") String email,
                                @Field("password") String password,
                                @Field("fullname") String fullname,
                                @Field("address") String address,
                                @Field("city") String city,
                                @Field("postal_code") String postal_code);

    @POST
    @FormUrlEncoded
    Call<CartResponse>addToCart(@Url String url ,
                                @Field("product_id") String idBarang,
                                @Field("qty") int qty);

    @POST
    Call<CartResponse<List<CartItem>>> getCartDetail(@Url String url);

    @POST
    @FormUrlEncoded
    Call<CartResponse> updateCart(@Url String url,
                                  @Field("cart_id") String idCartTemp,
                                  @Field("qty") String jumlah);

//    @POST
//    Call<BaseResponse> resetCart(@Url String url);

//    @POST
//    @FormUrlEncoded
//    Call<BaseResponse> removeCart(@Url String url, @Field("id_cart_temp") String idCartTemp);


    @GET
    Call<BaseResponse> getProfile(@Url String url);


    @POST
    @FormUrlEncoded
    Call<BaseResponse> updateProfile(@Url String url,
                                             @Field("fullname") String fullname,
                                             @Field("address") String address,
                                             @Field("city") String city,
                                             @Field("postal_code") String postal_code,
                                             @Field("profile_image") String profile_image);

}
