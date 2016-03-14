package com.example.aayushma.worklistmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextActivity();
            }
        }, 2000);
    }
    private void goToNextActivity(){
        Intent intent =new Intent(SplashScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
