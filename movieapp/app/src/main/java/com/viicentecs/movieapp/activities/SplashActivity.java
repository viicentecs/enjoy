package com.viicentecs.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.viicentecs.movieapp.R;
import com.viicentecs.movieapp.Utils.FileUtils;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waitAndCreateDirectories();
    }

    public void waitAndCreateDirectories(){
        createDirectory();
        new Handler().postDelayed(this::openLoginActivity, 250);
    }

    public void createDirectory(){
        FileUtils.makeAllDirectory("");
    }

    public void openLoginActivity(){
        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
        this.finish();
    }
}
