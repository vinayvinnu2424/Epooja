package com.example.epoojatest.Retrofit;

import com.example.epoojatest.AdaptersandModels.CategoryModel;
import com.example.epoojatest.AdaptersandModels.LoginModel;
import com.example.epoojatest.AdaptersandModels.ProductDetailsModel;
import com.example.epoojatest.AdaptersandModels.ProductModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitMethod {
    @GET("index.php?route=restapi/categories1")
    Call<CategoryModel> getCategories();

    @GET("index.php?route=restapi/categories/productslist")
    Call<ProductModel> getAlldata(@Query("category") String category, @Query("website") String website, @Query("page") String page, @Query("limit") String limit);

    @GET("index.php?route=restapi/categories/products")
    Call<ProductDetailsModel> getProducts(@Query("id") String id, @Query("website") String website, @Query("customer_id") String customerId);

    @FormUrlEncoded
    @POST("index.php?route=restapi/login/loginwithotp")
    Call<LoginModel> loginWithOTP(@Field("mobile") String mobile);

}
