package com.freelance.anantahairstudio.contactUs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void observer() {
        adminInfoViewModel.getAdminDetailsLiveData().observe(this, new Observer<ContactUpdateResponse>() {
            @Override
            public void onChanged(ContactUpdateResponse contactUpdateResponse) {
                try {
                    if (!contactUpdateResponse.getData().getBusinessInfo().getPhone().isEmpty()) {
                        binding.callTxt.setText("+91 "+contactUpdateResponse.getData().getBusinessInfo().getPhone());
                    }
                    else {
                        binding.callTxt.setText("N/A");
                    }
                    if (!contactUpdateResponse.getData().getBusinessInfo().getWhatsapp().isEmpty()) {
                        binding.contactus.setText("+91 "+contactUpdateResponse.getData().getBusinessInfo().getWhatsapp());
                    }
                    else {
                        binding.contactus.setText("N/A");
                    }
                    if (!contactUpdateResponse.getData().getBusinessInfo().getEmail().isEmpty()) {
                        binding.emailTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getEmail());
                    }
                    else {
                        binding.emailTxt.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getFacebook().isEmpty()) {
                        binding.fbTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getFacebook());
                    }
                    else {
                        binding.fbTxt.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getInstagram().isEmpty()) {
                        binding.instaTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getInstagram());
                    }
                    else {
                        binding.instaTxt.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getYoutube().isEmpty()) {
                        binding.youtubeTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getYoutube());
                    }
                    else {
                        binding.youtubeTxt.setText("N/A");
                    }

                    if (!contactUpdateResponse.getData().getBusinessInfo().getWebsite().isEmpty()) {
                        binding.websiteTxt.setText(contactUpdateResponse.getData().getBusinessInfo().getWebsite());
                    }
                    else {
                        binding.websiteTxt.setText("N/A");
                    }
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
        binding.callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.callTxt.getText().toString().isEmpty()){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+binding.callTxt.getText().toString()));
                    startActivity(callIntent);
                }
            }
        });

        binding.callTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.callTxt.getText().toString().isEmpty()){
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+binding.callTxt.getText().toString()));
                    startActivity(callIntent);
                }
            }
        });

        binding.whatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.contactus.getText().toString().isEmpty()){
                    Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=+91" + binding.contactus.getText().toString() + "&text=" + "Hello, Ananta Hair Studio");
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(sendIntent);
                }
            }
        });

        binding.contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.contactus.getText().toString().isEmpty()){
                    Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=+91" + binding.contactus.getText().toString() + "&text=" + "Hello, Ananta Hair Studio");
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(sendIntent);
                }
            }
        });

        binding.emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.emailTxt.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String[] recipients={binding.emailTxt.getText().toString()};
                    intent.putExtra(Intent.EXTRA_EMAIL,recipients );
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment Booking Query");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello, \n Ananta Hair Studio");
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send mail"));
                }
            }
        });
        binding.emailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.emailTxt.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String[] recipients={binding.emailTxt.getText().toString()};
                    intent.putExtra(Intent.EXTRA_EMAIL,recipients );
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Appointment Booking Query");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello, \n Ananta Hair Studio");
                    intent.setType("text/html");
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send mail"));
                }
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}