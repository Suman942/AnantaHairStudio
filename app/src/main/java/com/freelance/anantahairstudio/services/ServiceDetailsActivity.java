package com.freelance.anantahairstudio.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityServiceDetailsBinding;
import com.freelance.anantahairstudio.utils.GlideHelper;

public class ServiceDetailsActivity extends AppCompatActivity {
    ActivityServiceDetailsBinding binding;
    int counter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_details);

        clickAllView();
        binding.counterTxt.setText(String.valueOf(counter));


    }

    private void clickAllView() {
        binding.addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter != 4){
                    counter ++;
                    binding.counterTxt.setText(String.valueOf(counter));
                }
            }
        });
        binding.minusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter != 1){
                    counter --;
                    binding.counterTxt.setText(String.valueOf(counter));
                }
            }
        });

        binding.addtocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              binding.addtocartBtn.setBackgroundColor(Color.parseColor("#FF3B70"));
              binding.addtocartBtn.setText("ADDED");
            }
        });
    }
}