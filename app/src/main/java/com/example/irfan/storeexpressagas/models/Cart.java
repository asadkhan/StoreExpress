package com.example.irfan.storeexpressagas.models;

import android.content.Context;

import com.example.irfan.storeexpressagas.extras.DeviceDatabaseHandler;

import java.util.List;

public class Cart {
    public int ID;
    public int ItemID;

    public String ItemName;
    public int ItemQty;
    public int ItemPrice;
    public String ItemImg;
   // public static DeviceDatabaseHandler databaseHelper;




    public static void addToCart(int itemID, String itemName, int itemPrice, int itemQty, Context context,String ITemimg){

        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);

        Cart item=databaseHelper.getCartItem(itemID);
        boolean newItem=true;
        if(item ==null){

            newItem=false;
        }
        if(item.ItemID==0){
            newItem=false;

        }


        if(newItem){

            Cart cartItem = new Cart();
            cartItem.ItemID=itemID;
            cartItem.ItemName=itemName;
            cartItem.ItemQty=itemQty;
            cartItem.ItemPrice=(itemPrice*itemQty);
            cartItem.ItemImg=ITemimg;
            databaseHelper.addToCart(cartItem);
        }
        else{

            int qty=item.ItemQty +itemQty;
            int price=(qty*itemPrice);
            databaseHelper.setItemQty(item.ItemID,qty,price);



        }

    }

    public static Cart getCartITem(int itemID,Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        return  databaseHelper.getCartItem(itemID);
    }

    public static void removeCartITem(int itemID,Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
          databaseHelper.removeCartItem(itemID);
    }


    public static List<Cart> getCart( Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        return  databaseHelper.getCart(100);
    }





}
