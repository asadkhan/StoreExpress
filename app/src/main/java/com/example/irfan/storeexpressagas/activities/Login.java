package com.example.irfan.storeexpressagas.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.irfan.storeexpressagas.R;
import com.example.irfan.storeexpressagas.baseclasses.BaseActivity;
import com.example.irfan.storeexpressagas.extras.ValidationUtility;

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

                if(!isValidate()){
                    signIN();
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



}



}
