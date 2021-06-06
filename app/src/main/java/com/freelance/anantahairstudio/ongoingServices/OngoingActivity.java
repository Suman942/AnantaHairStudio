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
    OngoingServiceViewModel serviceViewModel;
    String bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ongoing);
        serviceViewModel = new ViewModelProvider(this).get(OngoingServiceViewModel.class);
        getIntentData();
        initialise();
        clickViews();
        observer();
    }

    private void getIntentData() {
        bookingId = getIntent().getStringExtra("bookingId");
    }

    private void observer() {
        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken));

        serviceViewModel.ongoingServiceLiveData().observe(this, new Observer<OnGoingServiceResponse>() {
            @Override
            public void onChanged(OnGoingServiceResponse onGoingServiceResponse) {
                if (onGoingServiceResponse != null) {
                    for (int i = 0; i < onGoingServiceResponse.getData().size(); i++) {
                        if (onGoingServiceResponse.getData().get(i).getBookingId().equals(bookingId)){
                            for (int j = 0 ; j < onGoingServiceResponse.getData().get(i).getServices().size();j++) {
                                serviceArrayList.add(onGoingServiceResponse.getData().get(i).getServices().get(j));
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);

                }
            }
        });
    }

    private void clickViews() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OngoingActivity.this, OnGoingBookingActivity.class);
                intent.putExtra("from", 0);
                startActivity(intent);
            }
        });
    }

    private void initialise() {
        adapter = new OngoingServiceAdapter(this, serviceArrayList);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OngoingActivity.this, OnGoingBookingActivity.class);
        intent.putExtra("from", 0);
        startActivity(intent);
    }
}