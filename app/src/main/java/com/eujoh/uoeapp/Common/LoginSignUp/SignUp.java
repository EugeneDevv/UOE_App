package com.eujoh.uoeapp.Common.LoginSignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.User.UserDashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    private FirebaseAuth mAuth;

    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView titleText;
    TextInputEditText full_name, email, adm_number, password, confirm_password;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sign_up);

        //Hooks
        backBtn = findViewById(R.id.sign_up_back_arrow_btn);
        nextBtn = findViewById(R.id.sign_up_next_btn);
        loginBtn = findViewById(R.id.sign_up_login_btn);
        titleText = findViewById(R.id.sign_up_title_txt);
        full_name = findViewById(R.id.ed_full_name);
        email = findViewById(R.id.ed_email);
        adm_number = findViewById(R.id.ed_adm_number);
        password = findViewById(R.id.ed_pass);
        confirm_password = findViewById(R.id.ed_confirm_pass);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    public void callNextSignUpScreen(View view) {
        registerUser();
        Intent intent = new Intent(this,SignUp2ndClass.class);

        //Add Transition
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(nextBtn,"transition_next_btn");
        pairs[2] = new Pair<View,String>(loginBtn,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_txt");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,pairs);
        startActivity(intent,options.toBundle());
    }

    private void registerUser(){
        final String fullName = full_name.getText().toString().trim().toUpperCase();
        final String admNumber = adm_number.getText().toString().trim().toUpperCase();
        final String userEmail = email.getText().toString().trim().toLowerCase();
        String userPassword = password.getText().toString().trim();
        String confirmPassword = confirm_password.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)){
            full_name.setError("Password doesn't match");
            full_name.requestFocus();
            return;
        } else if (TextUtils.isEmpty(admNumber)){
            adm_number.setError("Admission number is required");
            adm_number.requestFocus();
            return;
        } else if (TextUtils.isEmpty(userEmail)){
            email.setError("Password doesn't match");
            email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Password doesn't match");
            email.requestFocus();
            return;
        } else if (TextUtils.isEmpty(userPassword)){
            password.setError("Password doesn't match");
            password.requestFocus();
            return;
        } else if (userPassword.length() < 6){
            password.setError("Password doesn't match");
            password.requestFocus();
             return;
        } else if (TextUtils.isEmpty(confirmPassword)){
            confirm_password.setError("Password doesn't match");
            confirm_password.requestFocus();
            return;
        } else if (!userPassword.equals(confirmPassword)){
            confirm_password.setError("Password doesn't match");
            confirm_password.requestFocus();
            return;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
//                                        progressBar.setVisibility(View.VISIBLE);
//                                        mAuth.createUserWithEmailAndPassword(userEmail, confirmPassword)
//                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                                        if (task.isSuccessful()){
//                                                            User user = new User(fullName, admNumber, userEmail);
//
//                                                            FirebaseDatabase.getInstance().getReference("Users")
//                                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                @Override
//                                                                public void onComplete(@NonNull Task<Void> task) {
//                                                                    if (task.isSuccessful()){
//                                                                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
//                                                                        progressBar.setVisibility(View.GONE);
//
//                                                                        //request to phone number
//                                                                    } else {
//                                                                        Toast.makeText(getApplicationContext(), "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                                                                        progressBar.setVisibility(View.GONE);
//                                                                    }
//                                                                }
//                                                            });
//                                                        } else{
//                                                            Toast.makeText(getApplicationContext(), "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                                                            progressBar.setVisibility(View.GONE);
//                                                        }
//                                                    }
//                                                });
//                                        return;
//                                    }
//                                    else{
//                                        confirm_password.setError("Password doesn't match");
//                                        full_name.requestFocus();
//                                    }
//                                    confirm_password.setError("Confirm password");
//                                    confirm_password.requestFocus();
//
//                                } else{
//                                    password.setError("Minimum 6 characters");
//                                    password.requestFocus();
////                                    return;
//                                }
//
//                            } else {
//                                password.setError("Password is required");
//                                password.requestFocus();
////                                return;
//                            }
//                        } else {
//                            email.setError("Please provide a valid Email");
//                            email.requestFocus();
////                            return;
//                        }
//                    } else {
//                        email.setError("Email is required");
//                        email.requestFocus();
////                        return;
//                    }
//                } else {
//                    adm_number.setError("");
//                    adm_number.requestFocus();
////                    return;
//                }
//            } else {
//                full_name.setError("Full name is required");
//                full_name.requestFocus();
////                return;
//            }
//        }
    }
    public void callLoginScreen(View view) {
        startActivity(new Intent(this, Login.class));
    }
}