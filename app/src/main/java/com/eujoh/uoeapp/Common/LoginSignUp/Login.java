package com.eujoh.uoeapp.Common.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
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

public class Login extends AppCompatActivity {

    TextInputEditText email, password;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        password = findViewById(R.id.ed_password_login);
        email = findViewById(R.id.ed_email_login);
        progressBar = findViewById(R.id.progressBar);
        loginBtn = findViewById(R.id.login_button);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(this, UserDashboard.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
//                finish();
            }
        });
    }

    public void callForgetPassword(View view) {
        startActivity(new Intent(this, ForgetPassword.class));
    }


    private void loginUser() {
        final String userEmail = email.getText().toString().trim().toLowerCase();
        String userPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(userEmail)) {
            email.setError("Field cannot be empty");
            email.requestFocus();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
//            return;
        } else if (TextUtils.isEmpty(userPassword)) {
            password.setError("Field cannot be empty");
            password.requestFocus();
//            return;
        } else if (userPassword.length() < 6) {
            password.setError("Password is at least 6 characters");
            password.requestFocus();
//            return;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Sign in failed!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public void callSignUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }
}