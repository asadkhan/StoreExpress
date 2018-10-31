package com.example.irfan.storeexpressagas.models;

import com.google.gson.annotations.SerializedName;

public class OrderModel {


        @SerializedName("PaymentStatus")
        public  int PaymentStatus=-1 ;
        @SerializedName("OrderType")
        public  int OrderType=-1 ;
        @SerializedName("PaymentMeathod")
        public  int PaymentMeathod =-1;

}
