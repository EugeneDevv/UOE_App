package com.eujoh.uoeapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eujoh.uoeapp.R;

public class LostFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);
    }

    public void addItemscreen(View view) {
        startActivity(new Intent(this, AddLostItem.class));
    }
}