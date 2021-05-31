package com.freelance.anantahairstudio.profileedit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityEditDetailsBinding;

public class EditDetailsActivity extends AppCompatActivity {

    ActivityEditDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_details);

        binding.upadteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, HomeActivity.class);
                intent.putExtra("from",0);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditDetailsActivity.this, HomeActivity.class);
        intent.putExtra("from",0);
        startActivity(intent);    }
}