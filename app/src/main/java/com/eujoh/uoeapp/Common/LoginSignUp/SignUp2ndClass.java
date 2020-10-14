package com.eujoh.uoeapp.Common.LoginSignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;

public class SignUp2ndClass extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_up2nd_class);

        //Hooks
        backBtn = findViewById(R.id.sign_up_back_arrow_btn);
        nextBtn = findViewById(R.id.sign_up_next_btn);
        loginBtn = findViewById(R.id.sign_up_login_btn);
        titleText = findViewById(R.id.sign_up_title_txt);
    }

    public void callVerificationCode(View view) {
        Intent intent = new Intent(this,VerifyOTP.class);

        //Add Transition
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(nextBtn,"transition_next_btn");
        pairs[2] = new Pair<View,String>(loginBtn,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_txt");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pairs);
        startActivity(intent,options.toBundle());
    }
}