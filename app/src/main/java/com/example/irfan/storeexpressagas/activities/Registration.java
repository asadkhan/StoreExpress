package com.example.irfan.storeexpressagas.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;

public class Registration extends BaseActivity implements View.OnClickListener  {

Button Btn_Signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Btn_Signin = (Button) findViewById(R.id.btn_signin);
        setEventListners();


    }



    private void setEventListners() {
        Btn_Signin.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        Log.d("test","click");
        switch (v.getId()) {
            case R.id.btn_signin:

                openActivity(Login.class);

                break;


        }

    }


}
