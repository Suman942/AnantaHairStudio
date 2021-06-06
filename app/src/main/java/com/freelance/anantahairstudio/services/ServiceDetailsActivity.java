package com.freelance.anantahairstudio.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.cart.pojo.AddtoCartResponse;
import com.freelance.anantahairstudio.cart.viewModel.AddToCartViewModel;
import com.freelance.anantahairstudio.databinding.ActivityServiceDetailsBinding;
import com.freelance.anantahairstudio.profileedit.EditDetailsActivity;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.PrefManager;

public class ServiceDetailsActivity extends AppCompatActivity {
    ActivityServiceDetailsBinding binding;
    int counter = 1;
    String serviceName,serviceImg,price,discountedPrice,id;
    AddToCartViewModel addToCartViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_details);
        addToCartViewModel = new ViewModelProvider(this).get(AddToCartViewModel.class);
        getIntentData();
        clickAllView();
        binding.counterTxt.setText(String.valueOf(counter));
        setData();
        observers();

    }

    private void observers() {
        addToCartViewModel.addTOCartLiveData().observe(this, new Observer<AddtoCartResponse>() {
            @Override
            public void onChanged(AddtoCartResponse addtoCartResponse) {
                try {
                    binding.addtocartBtn.setBackgroundColor(Color.parseColor("#FF3B70"));
                    binding.addtocartBtn.setText("ADDED");
                    Toast.makeText(ServiceDetailsActivity.this, "Successfully added to cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ServiceDetailsActivity.this, HomeActivity.class);
                    intent.putExtra("from",1);
                    startActivity(intent);
                }
                catch (Exception e){

                }
            }
        });
    }

    private void setData() {
        binding.serviceNameTxt.setText(serviceName);
        binding.amountTxt.setText("\u20B9 "+price);
        binding.discountAmount.setText("\u20B9 "+discountedPrice+" OFF");
        GlideHelper.setImageView(this,binding.serviceImage,serviceImg,R.drawable.ic_image_placeholder);
    }

    private void getIntentData() {
        serviceName = getIntent().getStringExtra("serviceName");
        serviceImg = getIntent().getStringExtra("serviceImg");
        price = getIntent().getStringExtra("price");
        discountedPrice = getIntent().getStringExtra("discountedPrice");
        id = getIntent().getStringExtra("id");
    }

    private void clickAllView() {
        binding.addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter != 4){
                    counter ++;
                    binding.counterTxt.setText(String.valueOf(counter));
                }
            }
        });
        binding.minusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter != 1){
                    counter --;
                    binding.counterTxt.setText(String.valueOf(counter));
                }
            }
        });

        binding.addtocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCartViewModel.addToCart(PrefManager.getInstance().getString(R.string.authToken),id,String.valueOf(counter));
                Log.i("referral",""+PrefManager.getInstance().getString(R.string.authToken));
            }
        });
    }
}