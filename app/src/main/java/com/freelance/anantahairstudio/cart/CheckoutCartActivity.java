package com.freelance.anantahairstudio.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.cart.adapter.CheckoutAdapter;
import com.freelance.anantahairstudio.cart.pojo.BookingResponse;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.cart.viewModel.AddToCartViewModel;
import com.freelance.anantahairstudio.databinding.ActivityCheckoutCartBinding;
import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.myInfo.viewModel.MyAccountInfoViewModel;
import com.freelance.anantahairstudio.network.RequestFormatter;
import com.freelance.anantahairstudio.notification.FcmResponse;
import com.freelance.anantahairstudio.notification.NotificationViewModel;
import com.freelance.anantahairstudio.profileedit.EditDetailsActivity;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CheckoutCartActivity extends AppCompatActivity {

    ActivityCheckoutCartBinding binding;
    CheckoutAdapter checkoutAdapter;
    ArrayList<CartListResponse.Cart> cartList = new ArrayList<>();
    AddToCartViewModel cartViewModel;
    DatePickerDialog picker;
    TimePickerDialog timePickerDialog;
    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int position;
    String finalDate, finalTime, finalTimeSlot;
    NotificationViewModel notificationViewModel;
    ProgressDialog progressDialog;
    int currentDay;
    MyAccountInfoViewModel myAccountInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout_cart);
        cartViewModel = new ViewModelProvider(this).get(AddToCartViewModel.class);
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        myAccountInfoViewModel = new ViewModelProvider(this).get(MyAccountInfoViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait..");
        setDefaultData();
        intialise();
        clickViews();
        setDefaultDate();
        observer();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        position = viewHolder.getAdapterPosition();
                        cartViewModel.removeCart(PrefManager.getInstance().getString(R.string.authToken), cartList.get(position).getId());
                        checkoutAdapter.notifyDataSetChanged();
                        checkoutAdapter.notifyItemChanged(position);
                        break;
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.checkOutRecyclerView);


        cartViewModel.getCartList(PrefManager.getInstance().getString(R.string.authToken));

    }

    private void observer() {
        cartViewModel.getCartListLiveData().observe(this, new Observer<CartListResponse>() {
            @Override
            public void onChanged(CartListResponse cartListResponse) {
                if (cartListResponse != null) {
                    cartList.addAll(cartListResponse.getData());
                    checkoutAdapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                }
            }
        });


        cartViewModel.removeCartLiveData().observe(this, new Observer<RemoveCartResponse>() {
            @Override
            public void onChanged(RemoveCartResponse removeCartResponse) {
                if (removeCartResponse != null) {
                    Toast.makeText(getApplicationContext(), "Item removed successfully", Toast.LENGTH_SHORT).show();
                    cartList.clear();
                    cartViewModel.getCartList(PrefManager.getInstance().getString(R.string.authToken));

                }
            }
        });

        cartViewModel.bookingLiveData().observe(this, new Observer<BookingResponse>() {
            @Override
            public void onChanged(BookingResponse bookingResponse) {
                if (bookingResponse != null) {
                    Toast.makeText(CheckoutCartActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
                    binding.book.setEnabled(true);
                    notificationViewModel.sendNotification(RequestFormatter.sendNotification("/topics/Booking", "New Booking Request", "From: "+PrefManager.getInstance().getString(R.string.fullname),R.drawable.main_logo));
                    startActivity(new Intent(CheckoutCartActivity.this, HomeActivity.class));
                    finish();
                    progressDialog.dismiss();
                }
            }
        });
        myAccountInfoViewModel.myAccount(PrefManager.getInstance().getString(R.string.authToken));
        myAccountInfoViewModel.myAccountLiveData().observe(this, new Observer<MyAccountResponse>() {
            @Override
            public void onChanged(MyAccountResponse myAccountResponse) {

                try {
                    binding.callTxt.setText(myAccountResponse.getData().getBasic().getPhone().toString());
                    PrefManager.getInstance().putString(R.string.phone,myAccountResponse.getData().getBasic().getPhone());
                } catch (Exception e) {
                }

                try {
                    binding.addressTxt.setText(myAccountResponse.getData().getAddress().get(0).getAddress());
                    PrefManager.getInstance().putString(R.string.address,myAccountResponse.getData().getAddress().get(0).getAddress());

                } catch (Exception e) {
                }


            }
        });


    }

    private void setDefaultData() {
        try {
            if( PrefManager.getInstance().getString(R.string.address) != null) {
                binding.addressTxt.setText(PrefManager.getInstance().getString(R.string.address));
            }
        } catch (Exception e) {
        }
        try {
            if (PrefManager.getInstance().getString(R.string.phone) != null) {
                binding.callTxt.setText(PrefManager.getInstance().getString(R.string.phone));
            }
        } catch (Exception e) {
        }
    }

    private void setDefaultDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentMonth = calendar.get(Calendar.MONTH);
         currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        binding.setDate.setText(String.valueOf(currentDay));
        binding.setMonth.setText(String.valueOf(MONTHS[currentMonth]));

//        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        binding.setTime.setText("00:00");
        finalDate = String.valueOf(currentDay) + "-" + String.valueOf(MONTHS[currentMonth]) + "-" + String.valueOf(calendar.get(Calendar.YEAR));
    }

    private void clickViews() {
        binding.dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                picker = new DatePickerDialog(CheckoutCartActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                binding.setDate.setText(String.valueOf(dayOfMonth));
                                binding.setMonth.setText(String.valueOf(MONTHS[monthOfYear]));
                                Log.i("time", " " + String.valueOf(dayOfMonth) + "-" + String.valueOf(MONTHS[monthOfYear]) + "-" + String.valueOf(year));
                                monthOfYear ++;

                                finalDate = String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                picker.show();
            }
        });

        binding.timeVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                timePickerDialog = new TimePickerDialog(CheckoutCartActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                Time time = new Time(hourOfDay, minute, 0);

                                //little h uses 12 hour format and big H uses 24 hour format
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h : mm a");

                                //format takes in a Date, and Time is a sublcass of Date
                                String s = simpleDateFormat.format(time);
                                binding.setTime.setText(s);
//                                finalTime =
//                                binding.setTime.setText(String.valueOf(hourOfDay) + "h" + ":" + String.valueOf(minute) + "m");
//                                Log.i("time", " " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                                finalTime = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });

        binding.editLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutCartActivity.this, EditDetailsActivity.class);
                intent.putExtra("from",2);
                startActivity(intent);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckoutCartActivity.this, HomeActivity.class));
                finish();
            }
        });

        binding.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalTime != null && finalDate != null) {
                    finalTimeSlot = finalDate + " " + finalTime;
                    Log.i("time", " " + finalTimeSlot);
                    cartViewModel.booking(PrefManager.getInstance().getString(R.string.authToken), finalTimeSlot, "1");
                    binding.book.setEnabled(false);
                    progressDialog.show();
                } else {
                    Toast.makeText(CheckoutCartActivity.this, "Select date and time slot", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intialise() {
        checkoutAdapter = new CheckoutAdapter(getApplicationContext(), cartList);
        binding.checkOutRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.checkOutRecyclerView.setAdapter(checkoutAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CheckoutCartActivity.this, HomeActivity.class));
        finish();
    }
}