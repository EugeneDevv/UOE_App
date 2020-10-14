package com.eujoh.uoeapp.Common.LoginSignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;

public class StudentStartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.student_start_up_screen);
    }

    public void callLoginScreen(View view) {
        Intent intent = new Intent(this,Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.start_up_login_btn),"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StudentStartUpScreen.this,pairs);
        startActivity(intent,options.toBundle());
    }

    public void callSignupScreen(View view) {
        Intent intent = new Intent(this,SignUp.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.start_up_signup_btn),"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StudentStartUpScreen.this,pairs);
        startActivity(intent,options.toBundle());
    }
}