package com.example.irfan.storeexpressagas.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.abstract_classess.GeneralCallBack;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.extras.ValidationUtility;
import com.example.irfan.storeexpressagas.models.CategoryResponse;
import com.example.irfan.storeexpressagas.models.LoginResponse;
import com.example.irfan.storeexpressagas.network.RestClient;

public class Login  extends BaseActivity implements View.OnClickListener {

    Button Btn_signup,Btn_login;
    EditText Txt_email,Txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("test","in");
        Btn_signup = (Button) findViewById(R.id.btn_signup);

        Txt_email = (EditText) findViewById(R.id.txt_email);
        Txt_password = (EditText) findViewById(R.id.txt_password);
        Btn_login = (Button) findViewById(R.id.btn_login);

        setEventListners();

    }


    private void setEventListners() {
        Btn_signup.setOnClickListener(this);
        Btn_login.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        Log.d("test","click");
        switch (v.getId()) {
            case R.id.btn_signup:

                openActivity(Registration.class);

                break;

            case R.id.btn_login:
                Log.d("test","clog");
                if(isValidate()){
                    signIN();
                    Log.d("test","clogv");
                }
                                break;


        }

    }

    private boolean isValidate() {
        if (!ValidationUtility.edittextValidator(Txt_email,Txt_password)) {
            return false;
        }


        return true;
    }
public void signIN(){

    showProgress();
       Log.d("test", "intest");
       RestClient.getAuthAdapter().loginUser(Txt_email.getText().toString(), Txt_password.getText().toString(), "password").enqueue(new GeneralCallBack<LoginResponse>(this) {
           @Override
           public void onSuccess(LoginResponse response) {

               hideProgress();

               if (response.getAccessToken() != null && !response.getAccessToken().equals("")) {

                   sharedperference.saveToken(response.getAccessToken().toString());
                        String loginDetails=sharedperference.getLogin();
                   switch (loginDetails) {
                       case "":

                           openActivity(MainActivity.class);
                           break;
                       case "checkout":
                           sharedperference.saveLogin("");
                           openActivity(CheckOutFirstActivity.class);
                           break;

                       case "productrequest":
                           sharedperference.saveLogin("");
                           openActivity(ProductRequestActivity.class);
                           break;
                       case "profile":
                           sharedperference.saveLogin("");
                           openActivity(ProfileActivity.class);
                           break;


                   }



               } else {

                   Toast.makeText(getApplicationContext(), "incorrect",
                           Toast.LENGTH_LONG).show();


               }

           }

           @Override
           public void onFailure(Throwable throwable) {
               //onFailure implementation would be in GeneralCallBack class

               Toast.makeText(getApplicationContext(), "Failed",
                       Toast.LENGTH_LONG).show();

               hideProgress();
               Log.d("test", "failed");

           }






       });

   }



}




