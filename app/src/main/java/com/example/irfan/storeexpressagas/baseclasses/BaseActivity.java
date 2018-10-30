package com.example.irfan.storeexpressagas.baseclasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.activities.CartActivity;
import com.example.irfan.storeexpressagas.activities.CheckOutFirstActivity;
import com.example.irfan.storeexpressagas.activities.Login;
import com.example.irfan.storeexpressagas.activities.OrdersActivity;
import com.example.irfan.storeexpressagas.activities.ProductRequestActivity;
import com.example.irfan.storeexpressagas.activities.ProfileActivity;
import com.example.irfan.storeexpressagas.extras.PrefManager;
import com.example.irfan.storeexpressagas.extras.ProgressLoader;

import java.io.IOException;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected ViewDataBinding parentBinding;
    public PrefManager sharedperference;
    private ProgressLoader progressLoader;
    private boolean animationNeeded;
    private boolean forwardTransition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationNeeded = true;
        forwardTransition = true;
        sharedperference = new PrefManager(this);
        setListners();
//        parentBinding.getRoot().setOnTouchListener(new );
    }

    private void setListners() {
    }


    /**
     * @param activity
     * @usage it opens the activity receives in parameter
     */
    public void openActivity(Class activity) {

        startActivity(new Intent(this, activity));
    }


    public void openActivityProductRequest() {


        if(sharedperference.getToken()==null || sharedperference.getToken()=="") {

            startActivity(new Intent(this, Login.class));
            sharedperference.saveLogin("productrequest");
        }
        else{
            startActivity(new Intent(this, ProductRequestActivity.class));


        }

    }

    public void openActivityOrders() {


        if(sharedperference.getToken()==null || sharedperference.getToken()=="") {

            startActivity(new Intent(this, Login.class));
            sharedperference.saveLogin("orders");
        }
        else{
            startActivity(new Intent(this, OrdersActivity.class));


        }

    }


    public void openActivityProfile() {


        if(sharedperference.getToken()==null || sharedperference.getToken()=="") {

            startActivity(new Intent(this, Login.class));
            sharedperference.saveLogin("profile");
        }
        else{
            startActivity(new Intent(this, ProfileActivity.class));


        }

        //i = new Intent(SplashScreen.this, LanuageSelection.class);



    }


    /**
     * @param activity
     * @usage it opens the activity receives in parameter and finish  the current activity running
     */
    public void openActivityWithFinish(Class activity) {
        startActivity(new Intent(this, activity));
        finish();
    }

    /**
     * @param intent
     * @usage it opens the activity with provide intent and finish  the current activity running
     */
    public void openActivityWithFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    /**
     * @param activity
     * @param bundle
     * @usage It opens the activity with the provided bundle as a parameter
     */
    public void openActivity(Class activity, Bundle bundle) {
        startActivity(new Intent(this, activity).putExtras(bundle));
    }

    /**
     * @param activity
     * @param bundle
     * @usage It opens the activity for result with the provided bundle as a parameter
     */
    public void openActivityForResults(Class activity, Bundle bundle, int requestCode) {
        startActivityForResult(new Intent(this, activity).putExtras(bundle), requestCode);
    }


    /**
     * @param activity
     * @usage It opens the activity for result
     */
    public void openActivityForResults(Class activity, int requestCode) {
        startActivityForResult(new Intent(this, activity), requestCode);
    }

    public View getView() {
        if (parentBinding != null)
            return parentBinding.getRoot();
        else return null;

    }

    /**
     * @param view
     * @param message
     * @usage It use to show any message provided by the caller
     */
    public void showMessage(View view, String message) {
        //    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * @param throwable
     * @param view
     * @usage it handles onFailure Response of retrofit
     */
    public void onFailureResponse(View view, Throwable throwable) {
        if (throwable instanceof IOException) {
            //   showMessage(view,getString(R.string.no_interconection_error));
        } else {
            // showMessage(view,getString(R.string.some_thing_goes_wrong));
        }
    }


    public void showMessageToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();

    }


    public void showMessageDailogNextScreen(String title, String message, final Class activityI) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        openActivity(activityI);
                    }
                });

       /* builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/

        AlertDialog alert11 = builder1.create();
        alert11.show();



}


    public void showMessageDailog(String title, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

       /* builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/

        AlertDialog alert11 = builder1.create();
        alert11.show();



    }



    public void showProgress()
    {
        try {
            if (progressLoader == null)
            {
                progressLoader = new ProgressLoader();
            }

            progressLoader.show(getSupportFragmentManager(),TAG);
        }
        catch (IllegalStateException e)
        {
            Log.e(TAG, "showProgress:" + e.getMessage());
        }

    }

    public void hideProgress() {
        if (progressLoader != null) {
            try {
                progressLoader.dismissAllowingStateLoss();
            } catch (Exception e) {
                Log.e(TAG, "hideProgress:" + e.getMessage());
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    @Override
    protected void onPause() {

        super.onPause();
    }


    @Override
    public void onBackPressed() {
        forwardTransition=false;
        super.onBackPressed();
    }


    public static String removeZero(String str){
        StringBuffer sb = new StringBuffer(str);
        while (sb.length()>1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();  // return in String
    }

/*
    public boolean isInternetAvailable(){
        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return  isConnected;

    }
  */
    public void exit(){

        System.exit(0);
    }

    private void logOut(){

        sharedperference.removeALL();
       // openActivity(Login.class);
    }
}
