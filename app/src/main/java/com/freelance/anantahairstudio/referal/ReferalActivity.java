package com.freelance.anantahairstudio.referal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityReferalBinding;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.utils.PrefManager;

public class ReferalActivity extends AppCompatActivity {

    ActivityReferalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_referal);
        PrefManager.getInstance(this, true);
        if (PrefManager.getInstance().getBoolean(R.string.pref_isFirstTimeLaunch_key)){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
     clickView();

    }

    private void clickView() {
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager.getInstance().putBoolean(R.string.pref_isFirstTimeLaunch_key,true);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager.getInstance().putBoolean(R.string.pref_isFirstTimeLaunch_key,true);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }
}