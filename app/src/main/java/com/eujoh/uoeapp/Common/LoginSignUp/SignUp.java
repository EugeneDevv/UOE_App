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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variables
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    ImageView backBtn;
    Button nextBtn, loginBtn;
    TextView titleText;
    TextInputEditText full_name, email, adm_number, password, phoneNumber;
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
        phoneNumber = findViewById(R.id.ed_phone);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
//        firebaseUser = mAuth.getCurrentUser();
//            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

    }

    public void callNextSignUpScreen(View view) {
        registerUser();
//        Intent intent = new Intent(this,SignUp2ndClass.class);
//
//        //Add Transition
//        Pair[] pairs = new Pair[4];
//        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
//        pairs[1] = new Pair<View,String>(nextBtn,"transition_next_btn");
//        pairs[2] = new Pair<View,String>(loginBtn,"transition_login_btn");
//        pairs[3] = new Pair<View,String>(titleText,"transition_title_txt");
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,pairs);
//        startActivity(intent,options.toBundle());
    }

    private void registerUser(){
        final String fullName = full_name.getText().toString().trim().toUpperCase();
        final String admNumber = adm_number.getText().toString().trim().toUpperCase();
        final String userEmail = email.getText().toString().trim().toLowerCase();
        final String userPassword = password.getText().toString().trim();
        final String phoneNo = phoneNumber.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)){
            full_name.setError("Name is required");
            full_name.requestFocus();
            return;
        } else if (TextUtils.isEmpty(admNumber)){
            adm_number.setError("Admission number is required");
            adm_number.requestFocus();
            return;
        } else if (TextUtils.isEmpty(userEmail)){
            email.setError("Email is required");
            email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Not a valid email");
            email.requestFocus();
            return;
        } else if (TextUtils.isEmpty(userPassword)){
            password.setError("Password cannot be empty");
            password.requestFocus();
            return;
        } else if (userPassword.length() < 6){
            password.setError("Weak password!");
            password.requestFocus();
             return;
        } else if (TextUtils.isEmpty(phoneNo)){
            phoneNumber.setError("Enter phone Number");
            phoneNumber.requestFocus();
            return;
        } else if (phoneNo.length() < 9){
            phoneNumber.setError("Enter valid phone number");
            phoneNumber.requestFocus();
            return;
        } else if (phoneNo.length() > 9){
            phoneNumber.setError("Enter valid phone number");
            phoneNumber.requestFocus();
            return;
        } else {
//            final String phoneNofinal = phoneNo;
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User(fullName, admNumber, userEmail, phoneNo);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);

                                    //request to phone number
                                    Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
//                                    intent.putExtra("phoneNo", phoneNo);
//                                    //Add Transition
//                                    Pair[] pairs = new Pair[4];
//                                    pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
//                                    pairs[1] = new Pair<View,String>(nextBtn,"transition_next_btn");
//                                    pairs[2] = new Pair<View,String>(loginBtn,"transition_login_btn");
//                                    pairs[3] = new Pair<View,String>(titleText,"transition_title_txt");

//                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,pairs);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
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