package com.freelance.anantahairstudio.referal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityReferalBinding;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.referal.pojo.ReferalResponse;
import com.freelance.anantahairstudio.referal.viewmodel.ReferralViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;

public class ReferalActivity extends AppCompatActivity {

    ActivityReferalBinding binding;
    ReferralViewModel referralViewModel;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = DataBindingUtil.setContentView(this,R.layout.activity_referal);
        PrefManager.getInstance(this, true);
        referralViewModel= new ViewModelProvider(this).get(ReferralViewModel.class);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
//        if (PrefManager.getInstance().getBoolean(R.string.pref_isFirstTimeLaunch_key)){
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//            finish();
//        }
        clickView();
        observer();

    }

    private void observer() {
        referralViewModel.referralCodeLiveData().observe(this, new Observer<ReferalResponse>() {
            @Override
            public void onChanged(ReferalResponse referalResponse) {

                try {
                    Toast.makeText(ReferalActivity.this, "Congratulations you earned "+referalResponse.getData().getPoints().intValue()+" points", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                    binding.submitBtn.setEnabled(true);
                    progressDialog.dismiss();
                }
              catch (Exception e){
                  Toast.makeText(ReferalActivity.this, "Technical issue", Toast.LENGTH_SHORT).show();
              }

            }
        });
    }

    private void clickView() {
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager.getInstance().putBoolean(R.string.pref_isFirstTimeLaunch_key,true);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtReferralCode.getText().toString().isEmpty()) {
                    referralViewModel.referralCode(PrefManager.getInstance().getString(R.string.authToken), binding.edtReferralCode.getText().toString());
                    binding.submitBtn.setEnabled(false);
                    progressDialog.show();
                }
            }
        });
    }
}