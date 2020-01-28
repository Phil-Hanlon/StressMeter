package com.example.stressmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

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



    public void cancelClicked(View view) {

        finish();
    }


    public void submitClicked(View view) {

        Random random = new Random();

        // Write to the CSV file here
        File file = new File(getExternalFilesDir(null), "stress_timestamp.csv");


        // Creates a row in the file for this new entry
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file, true));

            String[] entry = {
                    String.valueOf(random.nextInt(100000)),
                    String.valueOf(random.nextInt(16))
            };


            writer.writeNext(entry);

            writer.close();
        }
        catch (Exception e){}




        finish();
    }
}
