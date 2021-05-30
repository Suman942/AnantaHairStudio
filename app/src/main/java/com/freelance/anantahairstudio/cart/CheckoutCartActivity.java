package com.freelance.anantahairstudio.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.adapter.CheckoutAdapter;
import com.freelance.anantahairstudio.databinding.ActivityCheckoutCartBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class CheckoutCartActivity extends AppCompatActivity {

    ActivityCheckoutCartBinding binding;
    CheckoutAdapter checkoutAdapter;
    ArrayList<CheckoutModel> arrayList = new ArrayList<>();
    DatePickerDialog picker;
    TimePickerDialog timePickerDialog;
    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_checkout_cart);
       intialise();
       clickViews();
       setDefaultDate();
    }

    private void setDefaultDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        binding.setDate.setText(String.valueOf(currentDay));
        binding.setMonth.setText( String.valueOf(MONTHS[currentMonth]));
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
                                binding.setMonth.setText( String.valueOf(MONTHS[monthOfYear]));
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        binding.timeVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Calendar c = Calendar.getInstance();
              int  mHour = c.get(Calendar.HOUR_OF_DAY);
              int  mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                 timePickerDialog = new TimePickerDialog(CheckoutCartActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                binding.setTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    private void intialise() {
        arrayList.add(new CheckoutModel("Hair Cut",1));
        arrayList.add(new CheckoutModel("Shaving",2));

        checkoutAdapter = new CheckoutAdapter(getApplicationContext(),arrayList);
        binding.checkOutRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.checkOutRecyclerView.setAdapter(checkoutAdapter);
    }
}