package com.freelance.anantahairstudio.myBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityOngoingBinding;
import com.freelance.anantahairstudio.myBooking.adapter.OngoingServiceAdapter;
import com.freelance.anantahairstudio.myBooking.pojo.BookingDetailsResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.myBooking.viewModel.OngoingServiceViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OngoingActivity extends AppCompatActivity implements PaymentResultListener {
    ActivityOngoingBinding binding;
    OngoingServiceAdapter adapter;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList = new ArrayList<>();
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


        serviceViewModel.bookingDetails(bookingId);
        serviceViewModel.bookingDetailsLiveData().observe(this, new Observer<BookingDetailsResponse>() {
            @Override
            public void onChanged(BookingDetailsResponse bookingDetailsResponse) {
                if (bookingDetailsResponse != null) {
//                    try {
//                        if (bookingDetailsResponse.getData().getName() != null) {
//                            binding.name.setText("Name: " + bookingDetailsResponse.getData().getName());
//                        } else {
//                            binding.name.setText("Name: N/A");
//                        }
//                        if (bookingDetailsResponse.getData().getPhone() != null) {
//                            binding.phone.setText("Contact: " + bookingDetailsResponse.getData().getPhone());
//                        } else {
//                            binding.phone.setText("Contact: N/A");
//                        }
//                    } catch (Exception e) {
//                    }
//                    binding.bookingId.setText("BookingId: #" + bookingDetailsResponse.getData().getId());
//                    long slot = Long.parseLong(bookingDetailsResponse.getData().getSlot());
//                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                    String dateString = formatter.format(new Date(slot));
//                    binding.dateTxt.setText("Date: " + dateString);
//                    binding.timeTxt.setText("Time: " + LocalTime.getLocalTime(slot));
//                    topic = bookingDetailsResponse.getData().getEmail().substring(0, bookingDetailsResponse.getData().getEmail().indexOf("@")).trim();
                    serviceArrayList.addAll(bookingDetailsResponse.getData().getServices());

                    binding.loader.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });



//        serviceViewModel.ongoingService(PrefManager.getInstance().getString(R.string.authToken),"1","0");
//
//        serviceViewModel.ongoingServiceLiveData().observe(this, new Observer<OnGoingServiceResponse>() {
//            @Override
//            public void onChanged(OnGoingServiceResponse onGoingServiceResponse) {
//                if (onGoingServiceResponse != null) {
//                    for (int i = 0; i < onGoingServiceResponse.getData().getBookings().size(); i++) {
//                        if (onGoingServiceResponse.getData().getBookings().get(i).getBookingId().equals(bookingId)){
//                            for (int j = 0 ; j < onGoingServiceResponse.getData().getBookings().get(i).getServices().size();j++) {
//                                serviceArrayList.add(onGoingServiceResponse.getData().getBookings().get(i).getServices().get(j));
//                            }
//                        }
//                    }
//                    adapter.notifyDataSetChanged();
//                    binding.loader.setVisibility(View.GONE);
//
//                }
//            }
//        });
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

        binding.paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment();
            }
        });
    }

    private void makePayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_i9JXCe3R8lHDsn");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","Ananta Hair Studio");
            jsonObject.put("description","test payment");
            jsonObject.put("currency","INR");
            jsonObject.put("amount",100);
            jsonObject.put( "upi_link", true);
            jsonObject.put("prefill.contact",PrefManager.getInstance().getString(R.string.phone));
            jsonObject.put("prefill.email",PrefManager.getInstance().getString(R.string.email));
            checkout.open(OngoingActivity.this,jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("payment","Exception: "+e.getMessage());

        }

    }

    private void initialise() {
        adapter = new OngoingServiceAdapter(this, serviceArrayList);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.serviceRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OngoingActivity.this, OnGoingBookingActivity.class);
//        intent.putExtra("from", 0);
        startActivity(intent);
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