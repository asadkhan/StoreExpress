package com.example.irfan.storeexpressagas.models;

import com.google.gson.annotations.SerializedName;

public class OrderRequest {

    @SerializedName("PaymentStatus")
    public static int PaymentStatus ;
    @SerializedName("OrderType")
    public static int OrderType ;
    @SerializedName("PaymentMeathod")
    public static int PaymentMeathod ;
}
