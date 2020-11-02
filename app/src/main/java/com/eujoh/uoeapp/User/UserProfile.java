package com.eujoh.uoeapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.eujoh.uoeapp.Common.LoginSignUp.User;
import com.eujoh.uoeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;

    TextView Name, AdmNo, Email, PhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_user_profile);

        Name = findViewById(R.id.adm_number_tv);
        AdmNo = findViewById(R.id.adm_number_tv);
        Email = findViewById(R.id.email_tv);
        PhoneNo = findViewById(R.id.phone_number_tv);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        userID = user.getUid();

        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userPrfile = snapshot.getValue(User.class);

                if (userPrfile != null){
                    String name = userPrfile.fullName;
                    String admNo = userPrfile.admNumber;
                    String email = userPrfile.email;
                    String phoneNo = "+254" + userPrfile.phoneNo;

                    Name.setText(name);
                    AdmNo.setText(admNo);
                    Email.setText(email);
                    PhoneNo.setText(phoneNo);
                } else {
                    Toast.makeText(getApplicationContext(), "something went wrong!", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}