package com.example.irfan.storeexpressagas.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.irfan.storeexpressagas.Adapters.CategoryListAdapter;
import com.example.irfan.storeexpressagas.Adapters.FproductListAdapter;
import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.abstract_classess.GeneralCallBack;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.databinding.*;
import com.example.irfan.storeexpressagas.extras.MenuHandler;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.FproductResponse;
import com.example.irfan.storeexpressagas.models.FproductTwoCol;
import com.example.irfan.storeexpressagas.network.RestClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    public RecyclerView recyclerViewCat;
    public RecyclerView recyclerViewFProduct;
    public CategoryListAdapter mAdapterCat;
    public FproductListAdapter mAdapterFproduct;

    public   List<CategoryResponse.catValue> catList = new ArrayList<>();

    public   List<FproductTwoCol> producListTwoCol = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop_by_cate);
        //parentBinding = binding;
        //loadViews();
        Log.d("test","calling test...");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MenuHandler.Activitycontextold=this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerViewCat = (RecyclerView) findViewById(R.id.recycler_view_cat);

        mAdapterCat = new CategoryListAdapter(this.catList);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
       // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        //recyclerViewCat.addItemDecoration(itemDecoration);

        recyclerViewCat.setHasFixedSize(false);
        recyclerViewCat.setLayoutManager(horizontalLayoutManagaer);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCat.setAdapter(this.mAdapterCat);



        recyclerViewFProduct = (RecyclerView) findViewById(R.id.recycler_view_fProducts);

        mAdapterFproduct = new FproductListAdapter(this.producListTwoCol);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

       // RecyclerView.ItemDecoration itemDecoration =
         //       new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerViewFProduct.addItemDecoration(itemDecoration);

        recyclerViewFProduct.setHasFixedSize(true);
        recyclerViewFProduct.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFProduct.setAdapter(this.mAdapterFproduct);






        // test();

        getCategories();



    }



    public void getCategories(){
        showProgress();
        Log.d("test","intest");
        RestClient.getAuthAdapter().getCategories().enqueue(new GeneralCallBack<CategoryResponse>(this) {
            @Override
            public void onSuccess(CategoryResponse response) {

                hideProgress();

                if (!response.getIserror()) {



                    catList.clear();
                    List<CategoryResponse.catValue> list = response.getValue();
                        for(CategoryResponse.catValue obj : list){

                            Log.d("test",obj.getName());
                            Log.d("test",obj.getImage());
                            catList.add(obj);
                        }




                    mAdapterCat.notifyDataSetChanged();

                    getFproducts();

                }
                else{



                }

            }

            @Override
            public void onFailure(Throwable throwable) {
                //onFailure implementation would be in GeneralCallBack class
                hideProgress();
                Log.d("test","failed");

            }



        });

    }


    public void getFproducts(){

        showProgress();
        Log.d("test","intestFproduct");
        RestClient.getAuthAdapter().getFeaturepProducts().enqueue(new GeneralCallBack<FproductResponse>(this) {
            @Override
            public void onSuccess(FproductResponse response) {

                Gson gson = new Gson();
                String Reslog= gson.toJson(response);
                Log.d("test", Reslog);
                producListTwoCol.clear();

                if (!response.getIserror()) {

                   List<FproductResponse.Value> iList = response.getValue();

                   for(int i=0; i< iList.size();i+=2){

                       if(i<iList.size()){
                           FproductTwoCol obj = new FproductTwoCol();

                            obj.ProductoneName=iList.get(i).getName();
                            obj.ProductonePrice="Rs "+iList.get(i).getPrice();
                            obj.ProductoneImg=iList.get(i).getImage();

                            if((i+1)<iList.size()){

                             obj.ProducttwoName=iList.get((i+1)).getName();
                                obj.ProducttwoPrice="Rs" +iList.get((i+1)).getPrice();
                                obj.ProducttwoImg=iList.get((i+1)).getImage();


                            }

                          producListTwoCol.add(obj);

                       }





                   }
                    mAdapterFproduct.notifyDataSetChanged();


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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
}
