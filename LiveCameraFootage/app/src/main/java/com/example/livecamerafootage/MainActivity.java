package com.example.livecamerafootage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO ask for camera permissions
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 121);
        } else{
            //TODO show live camera footage

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //TODO show live camera footage
        if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //TODO show
        } else{
            finish();
        }
    }
}