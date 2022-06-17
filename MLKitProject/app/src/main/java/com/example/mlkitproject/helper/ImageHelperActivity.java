package com.example.mlkitproject.helper;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mlkitproject.R;

import java.io.IOException;

public class ImageHelperActivity extends AppCompatActivity {

    private int REQUEST_PICK_IMAGE = 1000;
    private ImageView inputImageView;
    private TextView outputTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_helper);

        inputImageView = findViewById(R.id.imageViewInput);
        outputTextView = findViewById(R.id.textViewOutput);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }

    public void onClickImage(View view){

    }

    public void onStartCamera(View view){

    }

    public void onPickImage(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_PICK_IMAGE){
                Uri uri = data.getData();
                Bitmap bitmap = loadFromUri(uri);
                inputImageView.setImageBitmap(bitmap);
            }
        }
    }


    private Bitmap loadFromUri(Uri uri){
        Bitmap bitmap = null;

        try {
            //for API version greater then 27
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1){
                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), uri);
                bitmap = ImageDecoder.decodeBitmap(source);
            }
//            for api version less than or equal 27
            else{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return bitmap;
    }
}