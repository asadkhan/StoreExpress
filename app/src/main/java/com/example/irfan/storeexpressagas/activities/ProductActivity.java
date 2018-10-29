package com.example.irfan.storeexpressagas.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.extras.MenuHandler;
import com.example.irfan.storeexpressagas.models.Cart;
import com.example.irfan.storeexpressagas.models.FproductResponse;
import com.example.irfan.storeexpressagas.models.Product;
import com.squareup.picasso.Picasso;

public class ProductActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    ImageView img;
    TextView name,price,description,txt_qty_box;
    Button btnPlus,btnMinus,btn_add_to_cart;
public static Product obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detail);
        img= (ImageView) findViewById(R.id.img_product);
        name = (TextView) findViewById(R.id.txt_product_name);
        price = (TextView) findViewById(R.id.txt_product_price);
        description = (TextView) findViewById(R.id.txt_product_desc);
        txt_qty_box = (TextView) findViewById(R.id.txt_qty_box);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btn_add_to_cart=(Button) findViewById(R.id.btn_add_to_cart);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btn_add_to_cart.setOnClickListener(this);

        Picasso.with(this).load(obj.img).resize(90, 90).centerCrop().into(img);
        name.setText(obj.name);
        price.setText(obj.price);
       // description.setText(obj.getDescription().toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_about_product);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_product);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_product_details);
        navigationView.setNavigationItemSelectedListener(this);




    }


    @Override
    public void onClick(View v) {
        Log.d("test","click");
        switch (v.getId()) {
            case R.id.btn_plus:
                plus();

                break;

            case R.id.btn_minus:
                Log.d("test","show msg call");
                //  showMessageDailogNextScreen("test","testing message",Login.class);
                minus();
                break;

            case R.id.btn_add_to_cart:
                Log.d("test","show msg call");
                //  showMessageDailogNextScreen("test","testing message",Login.class);
                addToCart();
                break;


        }

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout   mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_view_product_details);
        if (id == R.id.menu_about) {
            // Handle the camera action
            mDrawerLayout.closeDrawers();
            //   MenuHandler.orderRide(this);
            openActivityWithFinish(AboutActivity.class);


        } else if (id == R.id.menu_home) {
            mDrawerLayout.closeDrawers();
            openActivityWithFinish(MainActivity.class);

        } else if (id == R.id.menu_order) {
            mDrawerLayout.closeDrawers();
            //MenuHandler.currentOrders(this);

        } else if (id == R.id.menu_history) {
            mDrawerLayout.closeDrawers();
            //MenuHandler.orderHistory(this);

        } else if (id == R.id.menu_call_us) {
            mDrawerLayout.closeDrawers();
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        } else if (id == R.id.menu_sms_tracking) {
            mDrawerLayout.closeDrawers();
            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        } else if (id == R.id.menu_logout) {
            MenuHandler.logOut(this);
        }

//         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

public void plus(){

int qty= Integer.valueOf(txt_qty_box.getText().toString());
qty=qty+1;
int objprice = (qty * Integer.valueOf(obj.price));
    price.setText(String.valueOf(objprice));
    txt_qty_box.setText(String.valueOf(qty));

}

    public void minus(){

        int qty= Integer.valueOf(txt_qty_box.getText().toString());
        qty=qty-1;
        if(qty <=0){

         qty=1;
        }
        int objprice = (qty * Integer.valueOf(obj.price));
        price.setText(String.valueOf(objprice));
        txt_qty_box.setText(String.valueOf(qty));

    }

public void addToCart(){

        int qty= Integer.valueOf(txt_qty_box.getText().toString());
    Cart.removeCartITem(obj.itemID,this);
        Cart.addToCart(obj.itemID,obj.name,Integer.valueOf(obj.price.toString()),qty,this,obj.img);
        showMessageToast(getResources().getString(R.string.msg_add_to_car));
openActivityWithFinish(MainActivity.class);
    }





}
