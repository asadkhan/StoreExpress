package com.example.irfan.storeexpressagas.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.abstract_classess.GeneralCallBack;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.databinding.*;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.network.RestClient;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //parentBinding = binding;
        //loadViews();
        Log.d("test","calling test...");
        test();

    }



    public void test(){
        showProgress();
        Log.d("test","intest");
        RestClient.getAuthAdapter().test().enqueue(new GeneralCallBack<CategoryResponse>(this) {
            @Override
            public void onSuccess(CategoryResponse response) {

                hideProgress();

                if (!response.getIserror()) {
                    Log.d("test",response.getIserror().toString());

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
}
