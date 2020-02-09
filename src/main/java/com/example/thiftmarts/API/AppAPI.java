package com.example.thiftmarts.API;

import com.example.thiftmarts.Model.ImageResponse;
import com.example.thiftmarts.Model.LoginResponse;
import com.example.thiftmarts.Model.Products;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AppAPI {
    @FormUrlEncoded
    @POST("api/product/create")
    Call<Void> addproduct (@Header("x-access-token") String token, @Field("name") String name ,@Field("category") String category, @Field("price") String price , @Field("description") String descrition, @Field("image") String image);


    @Multipart
    @POST("api/product/upload")
    Call<ImageResponse>upload(@Header("x-access-token") String token, @Part MultipartBody.Part img);

    @GET("api/product")
    Call<List<Products>>getAllProducts(@Header("x-access-token") String token);


    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> checkUser(@Field("Username") String Email , @Field("Password") String password);

    @FormUrlEncoded
    @POST("users/registeruser")
    Call<Void> register(@Field("Fname") String first_name , @Field("Lname") String last_name,@Field("Email") String email ,@Field("Username") String username ,@Field("Age") String age,  @Field("Password") String password, @Field("Usertype") String userType);



}

