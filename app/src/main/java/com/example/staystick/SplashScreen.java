package com.example.staystick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hanks.htextview.typer.TyperTextView;

public class SplashScreen extends AppCompatActivity {
TyperTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView=findViewById(R.id.typer);
        textView.animateText("AGOMI_INDIA");
        textView.setCharIncrease(1);
        textView.setTyperSpeed(60);
        textView.animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.clearAnimation();
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }
}