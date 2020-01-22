package com.example.thiftmarts.API;

import com.example.thiftmarts.Model.Image;
import com.example.thiftmarts.Model.LoginResponse;
import com.example.thiftmarts.Model.Product;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AppAPI {
    @FormUrlEncoded
    @POST("api/product/create")
    Call<Void> addproduct (@Header("x-access-token") String token, @Field("name") String name ,@Field("category") String category, @Field("price") String price , @Field("description") String descrition, @Field("image") String image);


    @Multipart
    @POST("api/product/upload")
    Call<Image>upload(@Header("x-access-token") String token, @Part MultipartBody.Part img);

    @GET("products")
    Call<List<Product>>getAllProducts();

    @GET("products/{id}")
    Call<Product> GetSingleProduct(
            @Path("id") String ProductId
    );

    @PUT("products/{id}")
    Call<Product> UpdateProduct(
            @Path("id") String ProductId,
            @Field("name") String ProductName,
            @Field("category") String ProductCategory,
            @Field("price") String Price,
            @Field("description") String description
    );


    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> checkUser(@Field("Email") String Email , @Field("Password") String password);

    @FormUrlEncoded
    @POST("products/addproduct")
    Call<Product> AddProdut(
            @Field("name") String ProductName,
            @Field("category") String ProductCategory,
            @Field("price") String Price,
            @Field("description") String description,
            @Field("image") String imagename
    );

    @Multipart
    @POST("/upload")
    Call<Image>UploadImage(@Part("imageFile") MultipartBody.Part file);

    @FormUrlEncoded
    @POST("users/register")
    Call<Void> register(
            @Field("Name") String name ,
            @Field("Address") String address,
            @Field("Email") String email ,
            @Field("Gender") String gender ,
            @Field("Password") String password,
            @Field("Usertype") String userType
    );
}

