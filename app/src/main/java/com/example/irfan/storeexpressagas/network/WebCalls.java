package com.example.irfan.storeexpressagas.network;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.GResponse;
import com.example.irfan.storeexpressagas.models.GeneralResponse;
import com.example.irfan.storeexpressagas.models.LoginResponse;
import com.example.irfan.storeexpressagas.models.RegistrationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface WebCalls {


    @GET(EndPoints.TEST)
    Call<CategoryResponse> test();

    @FormUrlEncoded
    @POST(EndPoints.LOGIN)
    Call<LoginResponse> loginUser(@Field("username") String username,
                                     @Field("password") String password,
                                     @Field("grant_type") String grant_type);



   // @Headers("Content-Type: application/json")
    @POST(EndPoints.REGISTRATION)
    Call<GResponse> registerUser(@Body RegistrationRequest regUser);

}