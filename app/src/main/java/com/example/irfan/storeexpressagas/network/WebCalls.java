package com.example.irfan.storeexpressagas.network;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface WebCalls {


    @GET(EndPoints.TEST)
    Call<CategoryResponse> test();


}
