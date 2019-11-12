package com.example.irfan.storeexpressagas.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.abstract_classess.GeneralCallBack;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.extras.Auth;
import com.example.irfan.storeexpressagas.extras.Constants;
import com.example.irfan.storeexpressagas.extras.MenuHandler;
import com.example.irfan.storeexpressagas.extras.ValidationUtility;
import com.example.irfan.storeexpressagas.models.Cart;
import com.example.irfan.storeexpressagas.models.CartRequest;
import com.example.irfan.storeexpressagas.models.GResponse;
import com.example.irfan.storeexpressagas.models.ItemVM;
import com.example.irfan.storeexpressagas.models.ProductReqRequest;
import com.example.irfan.storeexpressagas.models.ProductReqResponse;
import com.example.irfan.storeexpressagas.network.RestClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductRequestActivity extends BaseActivity implements  NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    public TextView tv;
    public ImageView i;
    public EditText et_prodct_name,et_product_desc;
    private ImageView imageView;
    Button photoButton,btn_submit;
    private static final int CAMERA_REQUEST = 1888;
    public static String imgBase64;
    private AsyncTask mMyTask, updateTask;


    public Bitmap bitmap = null;
    public String file="",mediaPath;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_request);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_product_req);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_product_req);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_product_req);
        navigationView.setNavigationItemSelectedListener(this);

        et_prodct_name =(EditText) findViewById(R.id.et_prodct_name);
        et_product_desc =(EditText) findViewById(R.id.et_product_desc);

        imageView =(ImageView) findViewById(R.id.img_pod);
        photoButton = (Button) findViewById(R.id.btn_take_pic);
        btn_submit=(Button) findViewById(R.id.btn_submit);
        photoButton.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    private boolean isValidate() {
        if (!ValidationUtility.edittextValidator(et_prodct_name,et_product_desc)) {
            return false;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_product_req);
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
        }
//        else if (id == R.id.menu_pro_req) {
//            mDrawerLayout.closeDrawers();
//            openActivityProductRequest();
//            //MenuHandler.orderHistory(this);
//
//        }
        else if (id == R.id.menu_profile) {
            mDrawerLayout.closeDrawers();
            openActivityProfile();

            //MenuHandler.smsTracking(this);
            //MenuHandler.callUs(this);
            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());

        }

//        else if (id == R.id.menu_shopping) {
//            mDrawerLayout.closeDrawers();
//            openActivity(ShoppingListActivity.class);
//
//            //MenuHandler.smsTracking(this);
//            //MenuHandler.callUs(this);
//            //ActivityManager.showPopup(BookingActivity.this, Constant.CALL_NOW_DESCRIPTION, Constant.CALL_NOW_HEADING, Constant.CANCEL_BUTTON, Constant.CALL_NOW_BUTTON, Constant.CALL_BUTTON, Constant.PopupType.INFORMATION.ordinal());
//
//        }

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


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.actionbar_notifcation_img:
                openActivity(CartActivity.class);
                break;

            case R.id.actionbar_notifcation_textview:

                //  showMessageDailogNextScreen("test","testing message",Login.class);
                openActivity(CartActivity.class);
                break;

            case R.id.btn_take_pic:
                Log.d(Constants.TAG,"Camera Click");
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

                break;

            case R.id.btn_submit:
                Log.d(Constants.TAG,"Submit");
               if(isValidate()) {
                   updateTask = new AsyncTaskLoad().execute(mediaPath);
               }
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            bitmap=photo;
            imageView.setImageBitmap(photo);
        }
    }

    public void addProductReqest(){
        showProgress();
try {
    ProductReqRequest pr = new ProductReqRequest();
    pr.setRequestedProduct(et_prodct_name.getText().toString());
    pr.setProductDesc(et_product_desc.getText().toString());
    pr.setImage(imgBase64);
    Gson gson = new Gson();
    String Reslog = gson.toJson(pr);
    Log.d("testme Req", Reslog);

    RestClient.getAuthAdapterToekn(Auth.getToken(this)).addProductRequest(pr).enqueue(new GeneralCallBack<ProductReqResponse>(this) {
        @Override
        public void onSuccess(ProductReqResponse response) {

            if (!response.getIserror()) {

                Gson gson = new Gson();
                String Reslog = gson.toJson(response);
                Log.d("testme", Reslog);

                Toast.makeText(ProductRequestActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
                openActivity(MainActivity.class);
            }
            hideProgress();


        }

        @Override
        public void onFailure(Throwable throwable) {
            //onFailure implementation would be in GeneralCallBack class
            Log.d("testme", throwable.getMessage());
            hideProgress();
            Toast.makeText(ProductRequestActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();


        }


    });

}
catch (Exception e){
    hideProgress();
    Log.d("testme",e.getMessage());
}
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

    public class AsyncTaskLoad  extends AsyncTask<String, String, String> {
        private final static String TAG = "AsyncTaskLoadImage";

        @Override
        protected String doInBackground(String... params) {


            String encodedImage = "";
            if(bitmap != null) {
                Bitmap bm = bitmap;//BitmapFactory.decodeFile(mediaPath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] byteArrayImage = baos.toByteArray();
                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                encodedImage = encodedImage.replace("\n", "");
            }
            else{

                Log.d(Constants.TAG,"Pic Null");
            }
            return encodedImage;
        }
        @Override
        protected void onPostExecute(String base64) {
            imgBase64 = base64;
            // Toast.makeText(getApplicationContext(), imgBase64, Toast.LENGTH_SHORT).show();
            //setProfileOnServer();
            Log.d("testme",imgBase64);
            addProductReqest();
        }
    }

}
