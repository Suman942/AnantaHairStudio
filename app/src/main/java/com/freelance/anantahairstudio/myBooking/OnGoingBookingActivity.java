package com.freelance.anantahairstudio.myBooking;

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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityOnGoingBookingBinding;
import com.freelance.anantahairstudio.myBooking.adapter.BookingAdapter;
import com.freelance.anantahairstudio.myBooking.pojo.CancelBookingResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.myBooking.viewModel.OngoingServiceViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OnGoingBookingActivity extends AppCompatActivity implements BookingAdapter.Callback, PaymentResultListener {

    ActivityOnGoingBookingBinding binding;
    ArrayList<OnGoingServiceResponse.Data.Booking> serviceArrayList = new ArrayList<>();
    OngoingServiceViewModel serviceViewModel;
    BookingAdapter adapter;
    int position;
    int totalItems, scrollOutItems, currentVisibleItems;
    LinearLayoutManager mLayoutManager;
    boolean isLoading = false;
    int page = 1;


    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_going_booking);
        serviceViewModel = new ViewModelProvider(this).get(OngoingServiceViewModel.class);
        initialise();
        clickViews();
        observer();

        mLayoutManager = new LinearLayoutManager(this);
        binding.serviceRecyclerView.setLayoutManager(mLayoutManager);

//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                switch (direction) {
//                    case ItemTouchHelper.LEFT:
//                        position = viewHolder.getAdapterPosition();
//                        serviceViewModel.cancelBooking(PrefManager.getInstance().getString(R.string.authToken), serviceArrayList.get(position).getBookingId());
//                        adapter.notifyDataSetChanged();
//                        adapter.notifyItemChanged(position);
//                        break;
//
//
//                }
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(binding.serviceRecyclerView);

        binding.serviceRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading ) {
                    currentVisibleItems = mLayoutManager.getChildCount();
                    totalItems = mLayoutManager.getItemCount();
                    scrollOutItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((currentVisibleItems + scrollOutItems == totalItems)) {

                        isLoading = true;
                        page++;
                        Log.i("page", "pageNo: " + page);
                        Log.i("data", "response1: ");

                        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken),"1",String.valueOf(page));
                        binding.paginationLoader.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    private void observer() {
        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken),"1",String.valueOf(page));

        serviceViewModel.ongoingServiceLiveData().observe(this, new Observer<OnGoingServiceResponse>() {
            @Override
            public void onChanged(OnGoingServiceResponse onGoingServiceResponse) {
                if (onGoingServiceResponse.getData().getBookings().size() > 0) {
                    serviceArrayList.addAll(onGoingServiceResponse.getData().getBookings());
                }
                if (onGoingServiceResponse.getData().getBookings().size() == 0)
                {
//                    serviceArrayList.clear();
                }
                adapter.notifyDataSetChanged();
                binding.loader.setVisibility(View.GONE);
                binding.serviceRecyclerView.setEmptyView(binding.empty);
                binding.paginationLoader.setVisibility(View.GONE);
                isLoading = false;
            }
        });

        serviceViewModel.cancelBookingLiveData().observe(this, new Observer<CancelBookingResponse>() {
            @Override
            public void onChanged(CancelBookingResponse cancelBookingResponse) {
                Toast.makeText(OnGoingBookingActivity.this, "Booking Cancelled Successfully ", Toast.LENGTH_SHORT).show();
                serviceArrayList.clear();
                binding.loader.setVisibility(View.VISIBLE);
                serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken),"1","1");

            }
        });
    }

    private void clickViews() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnGoingBookingActivity.this, HomeActivity.class);
//                intent.putExtra("from", 0);
                startActivity(intent);
            }
        });
    }

    private void initialise() {
        adapter = new BookingAdapter(this, serviceArrayList, this);
        binding.serviceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(OnGoingBookingActivity.this, HomeActivity.class);
//        intent.putExtra("from", 0);
        startActivity(intent);
        finish();
    }

    @Override
    public void detail(String bookingId,int position) {
        Intent intent = new Intent(OnGoingBookingActivity.this, OngoingActivity.class);
        intent.putExtra("bookingId", serviceArrayList.get(position).getBookingId());
        startActivity(intent);
        finish();
    }

    @Override
    public void cancel(String bookingId) {
        serviceViewModel.cancelBooking(PrefManager.getInstance().getString(R.string.authToken), serviceArrayList.get(position).getBookingId());
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.i("payment","Success: "+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.i("payment","Error: "+i+ " "+s);
    }
}





