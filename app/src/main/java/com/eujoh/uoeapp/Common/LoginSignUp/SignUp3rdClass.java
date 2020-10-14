package com.eujoh.uoeapp.Common.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.User.UserDashboard;

public class SignUp3rdClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up3rd_class);
    }

    public void callUserDashBoard(View view) {
        startActivity(new Intent(this,UserDashboard.class));
    }
}