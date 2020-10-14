package com.eujoh.uoeapp.Common.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.User.UserDashboard;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
    }

    public void callForgetPassword(View view) {
        startActivity(new Intent(this, ForgetPassword.class));
    }

    public void callUserDashBoard(View view) {
        startActivity(new Intent(this, UserDashboard.class));
    }

    public void callSignUp(View view) {
        startActivity(new Intent(this,SignUp.class));
    }
}