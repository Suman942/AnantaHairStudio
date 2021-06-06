package com.freelance.anantahairstudio.ongoingServices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityOnGoingBookingBinding;
import com.freelance.anantahairstudio.ongoingServices.adapter.BookingAdapter;
import com.freelance.anantahairstudio.ongoingServices.adapter.OngoingServiceAdapter;
import com.freelance.anantahairstudio.ongoingServices.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.ongoingServices.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.ongoingServices.viewModel.OngoingServiceViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;

public class OnGoingBookingActivity extends AppCompatActivity {

    ActivityOnGoingBookingBinding binding;
    ArrayList<OnGoingServiceResponse.Data> serviceArrayList = new ArrayList<>();
    OngoingServiceViewModel serviceViewModel ;
    BookingAdapter adapter;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_on_going_booking);
        serviceViewModel = new ViewModelProvider(this).get(OngoingServiceViewModel.class);
        initialise();
        clickViews();
        observer();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
                    case ItemTouchHelper.LEFT:
                        position = viewHolder.getAdapterPosition();
                        serviceViewModel.cancelBooking(PrefManager.getInstance().getString(R.string.authToken),serviceArrayList.get(position).getBookingId());
                        adapter.notifyDataSetChanged();
                        adapter.notifyItemChanged(position);
                        break;

                    case ItemTouchHelper.RIGHT:
                        position = viewHolder.getAdapterPosition();
                        Intent intent = new Intent(OnGoingBookingActivity.this,OngoingActivity.class);
                        intent.putExtra("bookingId",serviceArrayList.get(position).getBookingId());
                        startActivity(intent);
                        adapter.notifyDataSetChanged();
                        adapter.notifyItemChanged(position);
                        break;
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.serviceRecyclerView);

    }

    private void observer() {
        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken));

        serviceViewModel.ongoingServiceLiveData().observe(this, new Observer<OnGoingServiceResponse>() {
            @Override
            public void onChanged(OnGoingServiceResponse onGoingServiceResponse) {
                if (onGoingServiceResponse != null){
                    serviceArrayList.addAll(onGoingServiceResponse.getData());

                    adapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                }
            }
        });

        serviceViewModel.cancelBookingLiveData().observe(this, new Observer<CancelBookingResponse>() {
            @Override
            public void onChanged(CancelBookingResponse cancelBookingResponse) {
                Toast.makeText(OnGoingBookingActivity.this, "Booking Cancelled Successfully ", Toast.LENGTH_SHORT).show();
                serviceArrayList.clear();
                binding.loader.setVisibility(View.VISIBLE);
                serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken));

            }
        });
    }

    private void clickViews() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnGoingBookingActivity.this, HomeActivity.class);
                intent.putExtra("from",0);
                startActivity(intent);
            }
        });
    }

    private void initialise() {
        adapter = new BookingAdapter(this,serviceArrayList);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OnGoingBookingActivity.this, HomeActivity.class);
        intent.putExtra("from", 0);
        startActivity(intent);
    }
}