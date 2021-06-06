package com.freelance.anantahairstudio.ongoingServices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityOngoingBinding;
import com.freelance.anantahairstudio.ongoingServices.adapter.OngoingServiceAdapter;
import com.freelance.anantahairstudio.ongoingServices.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.ongoingServices.repo.OngoingRepo;
import com.freelance.anantahairstudio.ongoingServices.viewModel.OngoingServiceViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;

public class OngoingActivity extends AppCompatActivity {
  ActivityOngoingBinding binding;
  OngoingServiceAdapter adapter;
  ArrayList<OnGoingServiceResponse.Data.Service> serviceArrayList = new ArrayList<>();
  ArrayList<OnGoingServiceResponse> bookingIdList = new ArrayList<>();
  OngoingServiceViewModel serviceViewModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_ongoing);
     serviceViewModel = new ViewModelProvider(this).get(OngoingServiceViewModel.class);
     initialise();
     clickViews();
     observer();
    }

    private void observer() {
        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken));

        serviceViewModel.ongoingServiceLiveData().observe(this, new Observer<OnGoingServiceResponse>() {
            @Override
            public void onChanged(OnGoingServiceResponse onGoingServiceResponse) {
                if (onGoingServiceResponse != null){
                    for (int i =0 ; i < onGoingServiceResponse.getData().size();i++) {
                        serviceArrayList.addAll(onGoingServiceResponse.getData().get(i).getServices());
                    }
//                    bookingIdList.add(onGoingServiceResponse);

                    adapter.notifyDataSetChanged();
                }
            }
        });
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
        adapter = new OngoingServiceAdapter(this,serviceArrayList);
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