package com.freelance.anantahairstudio.contactUs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityContactUsBinding;
import com.freelance.anantahairstudio.utils.PrefManager;

public class ContactUsActivity extends AppCompatActivity {
    ActivityContactUsBinding binding;
    final String storeLocation = "Ananta Hair Studio,Plot no 270 ,4th B Road Near Suhag Jewellers Sardarpura raj, Jodhpur, Rajasthan 342001";
    AdminInfoViewModel adminInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        adminInfoViewModel = new ViewModelProvider(this).get(AdminInfoViewModel.class);

        clickView();
        fetchData();
        observer();
    }

    private void observer() {
        adminInfoViewModel.getAdminDetailsLiveData().observe(this, new Observer<ContactUpdateResponse>() {
            @Override
            public void onChanged(ContactUpdateResponse contactUpdateResponse) {
                try {
                    binding.callTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getPhone());
                    binding.contactus.setText(contactUpdateResponse.getData().getBusinessInfo().getWhatsapp());
                    binding.emailTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getEmail());
                    binding.loader.setVisibility(View.GONE);
                }
                catch (Exception e){
                    binding.loader.setVisibility(View.GONE);
                }
            }
        });
    }

    private void fetchData() {
        adminInfoViewModel.getAdminDetails(PrefManager.getInstance().getString(R.string.authToken));
    }

    private void clickView() {
        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchAddress = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + storeLocation));
                startActivity(searchAddress);
            }
        });
    }
}