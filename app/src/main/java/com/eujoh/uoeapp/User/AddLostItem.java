package com.eujoh.uoeapp.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import com.eujoh.uoeapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import android.net.Uri;

public class AddLostItem extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button buttonChooseImage;
    private Button buttonUpload;
    private TextView textViewCancelUpload;
    private TextInputEditText textInputItemName;
    private TextInputEditText textInputItemDescription;
    private ImageView imageView;
    private Uri imageUri;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lost_item);

        buttonChooseImage = findViewById(R.id.choose_pic_btn);
        buttonUpload = findViewById(R.id.lost_upload_btn);
        textViewCancelUpload = findViewById(R.id.lost_cancel_tv);
        textInputItemName = findViewById(R.id.item_name_tv);
        textInputItemDescription = findViewById(R.id.item_desc_tv);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.lost_progress_bar);

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        textViewCancelUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
        && data != null && data.getData() != null){
            imageUri = data.getData();

            Picasso.with(this).load(imageUri).into(imageView);
        }
    }
}