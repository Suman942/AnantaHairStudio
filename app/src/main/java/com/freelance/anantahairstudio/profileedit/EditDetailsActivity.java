package com.freelance.anantahairstudio.profileedit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityEditDetailsBinding;
import com.freelance.anantahairstudio.profileedit.ppojo.UpdateData;
import com.freelance.anantahairstudio.profileedit.viewModel.ProfileEditViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;

public class EditDetailsActivity extends AppCompatActivity {

    ActivityEditDetailsBinding binding;
    ProfileEditViewModel profileEditViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_details);
        profileEditViewModel = new ViewModelProvider(this).get(ProfileEditViewModel.class);

        clickView();
        observer();

    }

    private void observer() {
        profileEditViewModel.updatePhoneLiveData().observe(this, new Observer<UpdateData>() {
            @Override
            public void onChanged(UpdateData updateData) {
                Toast.makeText(EditDetailsActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditDetailsActivity.this, HomeActivity.class);
                intent.putExtra("from",0);
                startActivity(intent);
            }
        });
    }

    private void clickView() {
        binding.upadteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.editPhno.getText().toString().isEmpty()) {
                    profileEditViewModel.updatePhone(PrefManager.getInstance().getString(R.string.authToken), "+91 " + binding.editPhno.getText().toString());
                }
                if (!binding.editAddress.getText().toString().isEmpty() && !binding.editLandamrk.getText().toString().isEmpty()) {
                    profileEditViewModel.updateAddress(PrefManager.getInstance().getString(R.string.authToken), binding.editLandamrk.getText().toString() + " , " + binding.editAddress.getText().toString());
                }
                if (binding.editAddress.getText().toString().isEmpty() && binding.editLandamrk.getText().toString().isEmpty() && binding.editPhno.getText().toString().isEmpty()) {
                    Intent intent = new Intent(EditDetailsActivity.this, HomeActivity.class);
                    intent.putExtra("from", 0);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditDetailsActivity.this, HomeActivity.class);
        intent.putExtra("from",0);
        startActivity(intent);    }
}