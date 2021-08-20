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
    String serviceName,serviceImg,price,discountedPrice,id,categoryId,description;
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
                        finish();

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
        if (description != null){
            binding.serviceDescription.setText(description.replaceAll("\\\\n", "\n"));
        }
        else {
            binding.serviceDescription.setText("No description available");
        }
        if (categoryId.equals("100")) {
            binding.categoryText.setText("Hair cut");
        } else if (categoryId.equals("101")) {
            binding.categoryText.setText("Shaving");
        } else if (categoryId.equals("102")) {
            binding.categoryText.setText("D-tan");
        } else if (categoryId.equals("103")) {
            binding.categoryText.setText("Facial");
        } else if (categoryId.equals("104")) {
            binding.categoryText.setText("Straightening");
        } else if (categoryId.equals("105")) {
            binding.categoryText.setText("Pedicure");
        } else if (categoryId.equals("106")) {
            binding.categoryText.setText("Bride/Groom");
        } else if (categoryId.equals("107")) {
            binding.categoryText.setText("Manicure");
        } else if (categoryId.equals("108")) {
            binding.categoryText.setText("Massage");
        } else if (categoryId.equals("109")) {
            binding.categoryText.setText("Waxing");
        } else if (categoryId.equals("110")) {
            binding.categoryText.setText("Hair");
        } else if (categoryId.equals("111")) {
            binding.categoryText.setText("Child mundan");
        } else if (categoryId.equals("112")) {
            binding.categoryText.setText("Eye brow");
        } else if (categoryId.equals("113")) {
            binding.categoryText.setText("Dandruff");
        } else if (categoryId.equals("114")) {
            binding.categoryText.setText("Spa");
        } else if (categoryId.equals("115")) {
            binding.categoryText.setText("Colour");
        } else if (categoryId.equals("116")) {
            binding.categoryText.setText("Other");
        }
    }

    private void getIntentData() {
        serviceName = getIntent().getStringExtra("serviceName");
        serviceImg = getIntent().getStringExtra("serviceImg");
        price = getIntent().getStringExtra("price");
        discountedPrice = getIntent().getStringExtra("discountedPrice");
        id = getIntent().getStringExtra("id");
        categoryId = getIntent().getStringExtra("categoryId");
        description = getIntent().getStringExtra("description");
        GlideHelper.setImageView(this,binding.serviceImage,serviceImg,R.drawable.ic_image_placeholder);
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
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}