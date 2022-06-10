package com.quannm18.quanlykho;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    int counter=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar.setMax(100);
        progressBar.setSecondaryProgress(40);
        CountDownTimer cdt = new CountDownTimer(3000,100) {
            @Override
            public void onTick(long l) {
                progressBar.setProgress(counter);
                counter++;
                progressBar.setProgress(Integer.parseInt(String.valueOf(l/10)));
            }

            @Override
            public void onFinish() {

                startActivity(new Intent(SplashActivity.this, LogintestActivity.class));
            }
        }.start();
    }


}