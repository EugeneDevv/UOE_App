package com.eujoh.uoeapp.Common.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;

public class VerifyOTP extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.verify_otp);
    }

    public void verifyCallNextSignUpScreen(View view) {
        startActivity(new Intent(this,SignUp3rdClass.class));
    }
}
