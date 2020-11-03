package com.eujoh.uoeapp.User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.eujoh.uoeapp.R;

public class contacts extends AppCompatActivity {

    //Variables
    ImageView bacBtn, ambulancecallLine1, ambulancecallLine2, securitycallLine1, securitycallLine2;

    TextView ambulanceLine1, ambulanceLine2, securityLine1, securityLine2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.contacts);

        //Hooks
        ambulanceLine1 = findViewById(R.id.text_ambulance_line1cont);
        ambulanceLine2 = findViewById(R.id.text_ambulance_line2cont);
        securityLine1 = findViewById(R.id.text_security_line1cont);
        securityLine2 = findViewById(R.id.text_ambulance_line2cont);

        ambulancecallLine1 = findViewById(R.id.ambulaance_line1_call);
        ambulancecallLine2 = findViewById(R.id.ambulance_line2_call);
        securitycallLine1 = findViewById(R.id.security_line1_call);
        securitycallLine2 = findViewById(R.id.security_line2_call);

        button = findViewById(R.id.back_pressed_contacts);

        ambulancecallLine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = ambulanceLine1.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phoneNumber));
                startActivity(intent);
            }
        });
        ambulancecallLine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = ambulanceLine2.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phoneNumber));
                startActivity(intent);
            }
        });
        securitycallLine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = securityLine1.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phoneNumber));
                startActivity(intent);
            }
        });
        securitycallLine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = securityLine2.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ phoneNumber));
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                contacts.super.onBackPressed();
                startActivity(new Intent(contacts.this, UserDashboard.class));
                finish();
            }
        });
    }
}