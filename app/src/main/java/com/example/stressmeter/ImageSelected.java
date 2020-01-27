package com.example.stressmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageSelected extends AppCompatActivity {


    ImageView selectedImage;
    ImageView selectedImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selected);


        Resources resources = getResources();

        selectedImage = new ImageView(this);
        selectedImageView = new ImageView(this);


        // Gets the intent that started this activity
        Intent intent = getIntent();

        int image_id = intent.getIntExtra("image_id", -1);


        Drawable drawable = resources.getDrawable(image_id);


        selectedImageView = findViewById(R.id.selected_image);

        selectedImageView.setImageDrawable(drawable);
    }
}
