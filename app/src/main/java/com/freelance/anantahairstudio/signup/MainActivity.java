package com.freelance.anantahairstudio.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_zoom_in);
        binding.logo.startAnimation(aniSlide);
        splashScreenThread();
    }
    private void splashScreenThread() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }, 4000);
    }
}