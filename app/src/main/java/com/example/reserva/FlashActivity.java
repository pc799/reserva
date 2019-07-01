package com.example.reserva;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(FlashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIMEOUT);
    }
}