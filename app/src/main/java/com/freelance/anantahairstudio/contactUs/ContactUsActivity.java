package com.freelance.anantahairstudio.contactUs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {
   ActivityContactUsBinding binding;
    final String storeLocation = "Ananta Hair Studio,Plot no 270 ,4th B Road Near Suhag Jewellers Sardarpura raj, Jodhpur, Rajasthan 342001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = DataBindingUtil.setContentView(this,R.layout.activity_contact_us);

      clickView();

    }

    private void clickView() {
        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchAddress = new  Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+storeLocation));
                startActivity(searchAddress);
            }
        });
    }
}