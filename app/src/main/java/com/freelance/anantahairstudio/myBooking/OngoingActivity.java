package com.freelance.anantahairstudio.myBooking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityOngoingBinding;
import com.freelance.anantahairstudio.myBooking.adapter.OngoingServiceAdapter;
import com.freelance.anantahairstudio.myBooking.pojo.BookingDetailsResponse;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;
import com.freelance.anantahairstudio.myBooking.pojo.PaymentDoneResponse;
import com.freelance.anantahairstudio.myBooking.viewModel.OngoingServiceViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OngoingActivity extends AppCompatActivity implements PaymentResultListener ,OngoingServiceAdapter.Callback{
    ActivityOngoingBinding binding;
    OngoingServiceAdapter adapter;
    ArrayList<BookingDetailsResponse.Data.Service> serviceArrayList = new ArrayList<>();
    OngoingServiceViewModel serviceViewModel;
    String bookingId;
    String priceTobePaid = null;
    Integer price;
    Integer points;
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
//
                    serviceArrayList.addAll(bookingDetailsResponse.getData().getServices());
                    binding.loader.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
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

    private void makePayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_AhfLKFC3Kd9d7N");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","Ananta Hair Studio");
            jsonObject.put("description","Payment for bookingId: #"+bookingId);
            jsonObject.put("currency","INR");
//            jsonObject.put("image", );
            jsonObject.put("amount",price);
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
        adapter = new OngoingServiceAdapter(this, serviceArrayList,this::totalPrice);
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
        price /= 100;
        serviceViewModel.paymentDone(PrefManager.getInstance().getString(R.string.authToken),bookingId,String.valueOf(price),String.valueOf(points));
        serviceViewModel.paymentDoneLiveData().observe(this, new Observer<PaymentDoneResponse>() {
            @Override
            public void onChanged(PaymentDoneResponse paymentDoneResponse) {
                Toast.makeText(getApplicationContext(), "Payment done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OngoingActivity.this,OnGoingBookingActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.i("payment","Error: "+i+ " "+s);

        Toast.makeText(this, "Please try again later ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void totalPrice(int subtotal) {
         price = subtotal * 100;
//        priceTobePaid = String.valueOf(price);
//        Toast.makeText(this, ""+price, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        binding.paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Use Points");
                builder.setMessage("Do you want to use your points?")

                //Setting message manually and performing action on button click
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                 points = Integer.parseInt(PrefManager.getInstance().getString(R.string.points));
                                Double pointsToRs = points * 0.1;

                                price  -=  pointsToRs.intValue();
                                if (price > 0) {
                                    makePayment();
                                }
                                else {
                                    Toast.makeText(OngoingActivity.this, "Price cannot be \u20B9 0", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                points = 0;
                                if (price > 0) {
                                    makePayment();
                                }
                                else {
                                    Toast.makeText(OngoingActivity.this, "Price cannot be \u20B9 0", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.show();



            }
        });
    }
}