package com.example.irfan.storeexpressagas.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.irfan.storeexpressagas.Adapters.CartItemListAdapter;
import com.example.irfan.storeexpressagas.Adapters.CheckOutCartItemAdapter;
import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.abstract_classess.GeneralCallBack;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.extras.Auth;
import com.example.irfan.storeexpressagas.extras.MenuHandler;
import com.example.irfan.storeexpressagas.extras.Orders;
import com.example.irfan.storeexpressagas.extras.PrefManager;
import com.example.irfan.storeexpressagas.models.Cart;
import com.example.irfan.storeexpressagas.models.CartRequest;
import com.example.irfan.storeexpressagas.models.GResponse;
import com.example.irfan.storeexpressagas.models.ItemVM;
import com.example.irfan.storeexpressagas.models.OrderModel;
import com.example.irfan.storeexpressagas.models.OrderRequest;
import com.example.irfan.storeexpressagas.models.OrderResponse;
import com.example.irfan.storeexpressagas.network.RestClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFirstActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    public RecyclerView recyclerViewCheckoutItem;

    public CheckOutCartItemAdapter mAdapterCheckoutitem;

    public Button btnNext;

public RadioButton rBtndelivery,rBtnPickUp;

    public List<Cart> cartItemList = new ArrayList<>();
    public TextView tv;
    public ImageView i;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkoutfirst);

        rBtndelivery = (RadioButton) findViewById(R.id.radioDelivery);
        rBtndelivery.setOnCheckedChangeListener(this);
        rBtnPickUp = (RadioButton) findViewById(R.id.radioPickUp);
        rBtnPickUp.setOnCheckedChangeListener(this);
        btnNext=(Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_checkoutf);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_checkoutF);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_checkoutf);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewCheckoutItem = (RecyclerView) findViewById(R.id.recycler_view_checkoutItem);

        mAdapterCheckoutitem = new CheckOutCartItemAdapter(this.cartItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // RecyclerView.ItemDecoration itemDecoration =
        //       new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        //recyclerViewCart.addItemDecoration(itemDecoration);

        recyclerViewCheckoutItem.setHasFixedSize(true);
        recyclerViewCheckoutItem.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCheckoutItem.setAdapter(this.mAdapterCheckoutitem);
        getCart();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.radioDelivery) {
                rBtndelivery.setChecked(true);
                rBtnPickUp.setChecked(false);
                OrderRequest.OrderType=1;

            }
            if (buttonView.getId() == R.id.radioPickUp) {
                rBtnPickUp.setChecked(true);
                rBtndelivery.setChecked(false);

                OrderRequest.OrderType=0;
                OrderRequest.PaymentMeathod=0;
                OrderRequest.PaymentStatus=0;
            }
        }
    }


    @Override
    public void onClick(View v) {
        Log.d("test","Next click");
        Log.d("test",String.valueOf(OrderRequest.OrderType));
        switch (v.getId()) {
            case R.id.btn_next:
                Log.d("test","Next click");
            if(OrderRequest.OrderType !=-1){


                if(OrderRequest.OrderType==0){
                  placeOrderPickup();
                //openActivity(OStatusPickupActivity.class);
                }
                else if(OrderRequest.OrderType==1){

                    PlaceOrderDelivery();

                }
            }

                break;
            case R.id.actionbar_notifcation_img:
                openActivity(CartActivity.class);
                break;

            case R.id.actionbar_notifcation_textview:
                Log.d("test","show msg call");
                //  showMessageDailogNextScreen("test","testing message",Login.class);
                openActivity(CartActivity.class);
                break;


        }

    }
    public void getCart(){
        cartItemList.clear();
        List<Cart> cartlst=Cart.getCart(this);


        for(Cart obj : cartlst){
            Log.d("test","OBJ"+obj.ItemName);
            Cart t = new Cart();
            t.ItemQty=obj.ItemQty;
            t.ItemID=obj.ItemID;
            t.ItemImg=obj.ItemImg;
            t.ItemPrice=obj.ItemPrice;
            t.ItemName=obj.ItemName;

            cartItemList.add(t);

        }

        mAdapterCheckoutitem.notifyDataSetChanged();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_cart);
        if (id == R.id.menu_about) {
            // Handle the camera action
            mDrawerLayout.closeDrawers();
            openActivityWithFinish(AboutActivity.class);

        } else if (id == R.id.menu_home) {
            mDrawerLayout.closeDrawers();
            openActivity(MainActivity.class);
            // MenuHandler.tracking(this);

        } else if (id == R.id.menu_cart) {
            mDrawerLayout.closeDrawers();
            //MenuHandler.currentOrders(this);
            openActivity(CartActivity.class);
        } else if (id == R.id.menu_pro_req) {
            mDrawerLayout.closeDrawers();
            openActivityProductRequest();
            //MenuHandler.orderHistory(this);

        } else if (id == R.id.menu_profile) {
            mDrawerLayout.closeDrawers();
            openActivityProfile();

            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        }

        else if (id == R.id.menu_shopping) {
            mDrawerLayout.closeDrawers();
            openActivity(ShoppingListActivity.class);

            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        }

        else if (id == R.id.menu_orders) {
            mDrawerLayout.closeDrawers();
            openActivityOrders();

            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        }

        else if (id == R.id.menu_all_cat) {
            mDrawerLayout.closeDrawers();
            openActivity(AllCatActivity.class);

            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        }



        else if (id == R.id.menu_logout) {
            MenuHandler.logOut(this);
        }

//         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

public void placeOrderPickup(){
    Log.d("test","place oder call");
    showProgress();
    Log.d("test", Auth.getToken(this));
    List<ItemVM> itemlst = new ArrayList<>();
    List<Cart> cartItems = Cart.getCart(this);

    //  Log.d("test",Auth.getToken(c));

    for(Cart obj : cartItems){
        ItemVM Iobj= new ItemVM();
        Iobj.Id=obj.ItemID;
        Iobj.Quantity=obj.ItemQty;
        itemlst.add(Iobj);
    }
    CartRequest cart = new CartRequest();
    cart.items=itemlst;

    Gson gson = new Gson();
    String Reslog= gson.toJson(cart);
    Log.d("test", Reslog);

    RestClient.getAuthAdapterToekn(Auth.getToken(this)).test(cart).enqueue(new GeneralCallBack<GResponse>(this) {
        @Override
        public void onSuccess(GResponse response) {

            if(!response.getIserror()) {

                Gson gson = new Gson();
                String Reslog = gson.toJson(response);
                Log.d("test", Reslog);

                placeOrderPickupNext();

            }hideProgress();




        }

        @Override
        public void onFailure(Throwable throwable) {
            //onFailure implementation would be in GeneralCallBack class
            hideProgress();
            Log.d("test","failed");

        }



    });
}


public void PlaceOrderDelivery(){

        openActivity(PaymentMethodActivity.class);
        finish();
}

    public void placeOrderPickupNext(){

        showProgress();
        OrderModel obj = new OrderModel();
        obj.OrderType=OrderRequest.OrderType;
        obj.PaymentMeathod=OrderRequest.PaymentMeathod;
        obj.PaymentStatus=OrderRequest.PaymentStatus;

        Gson gson = new Gson();
        String Reslog= gson.toJson(obj);
        Log.d("test", Reslog);

        RestClient.getAuthAdapterToekn(Auth.getToken(this)).placeORder(obj).enqueue(new GeneralCallBack<OrderResponse>(this) {
            @Override
            public void onSuccess(OrderResponse response) {
                Gson gson = new Gson();
                String Reslog= gson.toJson(response);
                Log.d("test", Reslog);

                if(!response.getIserror()){

                    openActivity(OStatusPickupActivity.class);

                }

                hideProgress();



            }

            @Override
            public void onFailure(Throwable throwable) {
                //onFailure implementation would be in GeneralCallBack class
                hideProgress();
                Log.d("test","failed");

            }



        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        MenuItem item = menu.findItem(R.id.badge);
        MenuItemCompat.setActionView(item, R.layout.menu_cart);
        RelativeLayout notifCount = (RelativeLayout)   MenuItemCompat.getActionView(item);
        i =notifCount.findViewById(R.id.actionbar_notifcation_img);
        tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        //tv.setText("12");
        tv.setText(String.valueOf(Cart.getCartTotalItem(this)));
        i.setOnClickListener(this);
        tv.setOnClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

}
