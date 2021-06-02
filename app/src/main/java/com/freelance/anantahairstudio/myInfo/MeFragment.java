package com.freelance.anantahairstudio.myInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CheckoutCartActivity;
import com.freelance.anantahairstudio.contactUs.ContactUsActivity;
import com.freelance.anantahairstudio.databinding.FragmentMeBinding;
import com.freelance.anantahairstudio.ongoingServices.OngoingActivity;
import com.freelance.anantahairstudio.profileedit.EditDetailsActivity;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.PrefManager;


public class MeFragment extends Fragment {

     FragmentMeBinding binding;
    InputMethodManager imm;
    public MeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_me, container, false);
         imm = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));

        try {
            binding.nameTxt.setText(PrefManager.getInstance().getString(R.string.fullname));
            binding.emailTxt.setText(PrefManager.getInstance().getString(R.string.email));
            GlideHelper.setImageView(getContext(),binding.profileImg,PrefManager.getInstance().getString(R.string.profileUrl),R.drawable.ic_user);
        }
        catch (Exception e){}

        clickViews();

        return binding.getRoot();
    }

    private void clickViews() {
        binding.contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });

        binding.editLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditDetailsActivity.class);
                startActivity(intent);
            }
        });

        binding.ongoingServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OngoingActivity.class);
                startActivity(intent);
            }
        });



    }
}