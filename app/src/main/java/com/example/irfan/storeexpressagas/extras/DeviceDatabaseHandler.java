package com.example.irfan.storeexpressagas.extras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.irfan.storeexpressagas.models.Cart;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DeviceDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DeviceDb";

    // Audio and dataUsage table name
    private static final String TABLE_NAME = "Cart";
    ;
    //columns Name for Call tables
    private static final String CART_ID = "Id";
    private static final String CART_ITEM_ID = "item_ID";
    private static final String CART_ITEM_NAME = "item_name";
    private static final String CART_ITEM_QTY = "item_qty";
    private static final String CART_ITEM_PRICE = "item_price";
    private static final String CART_ITEM_IMG = "item_img";
    private static DeviceDatabaseHandler sInstance;

    public static synchronized DeviceDatabaseHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DeviceDatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DeviceDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CART_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CART_ITEM_ID + " INTEGER,"
                + CART_ITEM_NAME + " TEXT," + CART_ITEM_QTY + " INTEGER," + CART_ITEM_PRICE + " INTEGER,"+CART_ITEM_IMG  + " TEXT "+ ")";
        db.execSQL(CART_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    public void addToCart(Cart data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CART_ITEM_ID, data.ItemID);
        values.put(CART_ITEM_NAME, data.ItemName);
        values.put(CART_ITEM_QTY, data.ItemQty);
        values.put(CART_ITEM_PRICE, data.ItemPrice);
        values.put(CART_ITEM_IMG, data.ItemImg);
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<Cart> getCart(int limit) {
        if (limit == 0) limit = 5;
        List<Cart> cartItemList = new ArrayList<Cart>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cart cartItem = new Cart();
                cartItem.ID = cursor.getInt(0);
                cartItem.ItemID = cursor.getInt(1);
                cartItem.ItemName = cursor.getString(2);
                cartItem.ItemQty = cursor.getInt(3);
                cartItem.ItemPrice = cursor.getInt(4);
                cartItem.ItemImg = cursor.getString(5);
                // Adding contact to list
                cartItemList.add(cartItem);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
        // return contact list
        Gson gson = new Gson();
        String Reslog= gson.toJson(cartItemList);
        Log.d("test", Reslog);
        return cartItemList;
    }

    public Cart getCartItem(int ItemID) {


        Cart cartItem = new Cart();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " where " + CART_ITEM_ID + "=" + ItemID + " limit 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                cartItem.ID = cursor.getInt(0);
                cartItem.ItemID = cursor.getInt(1);
                cartItem.ItemName = cursor.getString(2);
                cartItem.ItemQty = cursor.getInt(3);
                cartItem.ItemPrice = cursor.getInt(4);
                cartItem.ItemImg = cursor.getString(5);
                // Adding contact to list

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            //   db.close();
        }

return cartItem;
    }

    public void setItemQty(int ItemID,int Qty,int price  ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues data=new ContentValues();
        data.put(CART_ITEM_QTY,Qty);
        data.put(CART_ITEM_PRICE,price);
        db.update(TABLE_NAME, data, CART_ITEM_ID + "=" + ItemID, null);
        db.close(); // Closing database connection


    }

    public void removeCartItem(int ItemID ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, CART_ITEM_ID + " = ?",
                new String[]{String.valueOf(ItemID)});

        db.close();

    }


    public int getItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
