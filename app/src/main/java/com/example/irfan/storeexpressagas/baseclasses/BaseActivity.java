package com.example.irfan.storeexpressagas.baseclasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


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
        sharedperference=new PrefManager(this);
        setListners();
//        parentBinding.getRoot().setOnTouchListener(new );
    }

    private void setListners() {
    }


    /**
     * @usage it opens the activity receives in parameter
     * @param activity
     */
    public void openActivity(Class activity)
    {
        startActivity(new Intent(this,activity));
    }

    /**
     * @usage it opens the activity receives in parameter and finish  the current activity running
     * @param activity
     */
    public void openActivityWithFinish(Class activity)
    {
        startActivity(new Intent(this,activity));
        finish();
    }

    /**
     * @usage it opens the activity with provide intent and finish  the current activity running
     * @param intent
     */
    public void openActivityWithFinish(Intent intent)
    {
        startActivity(intent);
        finish();
    }

    /**
     * @usage It opens the activity with the provided bundle as a parameter
     * @param activity
     * @param bundle
     */
    public void openActivity(Class activity, Bundle bundle)
    {
        startActivity(new Intent(this,activity).putExtras(bundle));
    }

    /**
     * @usage It opens the activity for result with the provided bundle as a parameter
     * @param activity
     * @param bundle
     */
    public void openActivityForResults(Class activity, Bundle bundle, int requestCode)
    {
        startActivityForResult(new Intent(this,activity).putExtras(bundle), requestCode);
    }


    /**
     * @usage It opens the activity for result
     * @param activity
     */
    public void openActivityForResults(Class activity, int requestCode)
    {
        startActivityForResult(new Intent(this,activity), requestCode);
    }
    public View getView()
    {
        if (parentBinding!=null)
            return parentBinding.getRoot();
        else return null;

    }

    /**
     * @usage It use to show any message provided by the caller
     * @param view
     * @param message
     */
    public void showMessage(View view, String message)
    {
    //    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * @usage it handles onFailure Response of retrofit
     * @param throwable
     * @param view
     */
    public void onFailureResponse(View view,Throwable throwable)
    {
        if (throwable instanceof IOException)
        {
         //   showMessage(view,getString(R.string.no_interconection_error));
        }
        else
        {
           // showMessage(view,getString(R.string.some_thing_goes_wrong));
        }
    }


    /**
     * @usage It use to show any message provided by the caller
     * @param message
     */
    public void showMessage(String message)
    {

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
