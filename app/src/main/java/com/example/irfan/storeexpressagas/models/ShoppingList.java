package com.example.irfan.storeexpressagas.models;

import android.content.Context;
import android.util.Log;

import com.example.irfan.storeexpressagas.extras.DeviceDatabaseHandler;

import java.util.List;

public class ShoppingList {

public int Id;
public String Name;
public String Date;



    public static void addList( String Name, String Date,Context context){

        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);




        ShoppingList lst = new ShoppingList();
        lst.Name=Name;
        lst.Date=Date;


            databaseHelper.addShoppingList(lst);


    }

    public static void addListItem( int ListID, String Name,Context context){

        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);




        ShoppingListItem obj = new ShoppingListItem();
        obj.ShoppingListId=ListID;
        obj.itemName=Name;


        databaseHelper.addShoppingListItem(obj);


    }


    public static void removeShoppingList(int ListID,Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        databaseHelper.removeShoppingList(ListID);
    }


    public static void removeShoppingListItem(int ItemID,Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        databaseHelper.removeShoppingListItem(ItemID);
    }

    public static void updateShoppingList(int ID,String Name,Context context) {
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        databaseHelper.updateShoppingList(ID,Name);
    }


    public static List<ShoppingList> getShoppingList(Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        return  databaseHelper.getShoppingList(100);
    }

    public static List<ShoppingListItem> getShoppingListItem(int ListID,Context context){
        DeviceDatabaseHandler databaseHelper = DeviceDatabaseHandler.getInstance(context);
        return  databaseHelper.getShoppingListItem(100,ListID);
    }



}
