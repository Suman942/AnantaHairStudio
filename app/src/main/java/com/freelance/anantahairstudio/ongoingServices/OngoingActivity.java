package com.freelance.anantahairstudio.ongoingServices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityOngoingBinding;
import com.freelance.anantahairstudio.ongoingServices.adapter.OngoingServiceAdapter;

public class OngoingActivity extends AppCompatActivity {
  ActivityOngoingBinding binding;
  OngoingServiceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_ongoing);
     initialise();
     clickViews();
    }

    private void clickViews() {

      binding.back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(OngoingActivity.this, HomeActivity.class);
              intent.putExtra("from",0);
              startActivity(intent);
          }
      });
    }



    private void initialise() {
        adapter = new OngoingServiceAdapter(this);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OngoingActivity.this, HomeActivity.class);
        intent.putExtra("from",0);
        startActivity(intent);
    }
}