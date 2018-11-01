package com.example.irfan.storeexpressagas.network;

public class EndPoints {

    static final String API_PREFIX="/api/";
    static final String CATEGORIES=API_PREFIX+"category/getcategories";
    static final String REGISTRATION=API_PREFIX+"accounts/createappuser";
    static final String TEST=API_PREFIX+"cart/updatecart";
    static final String LOGIN="/oauth/token";
    static final String FPRODUCT=API_PREFIX+"item/featureproducts";
    static final String PRODUCTBYCAT=API_PREFIX+"item/GetItem";
    static final String PLACEORDER=API_PREFIX+"order/placeorder";
    static final String ADRESSES=API_PREFIX+"useraddress/getuseraddresses";
}
