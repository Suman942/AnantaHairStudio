package com.freelance.anantahairstudio.myInfo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.FragmentMeBinding;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.PrefManager;


public class MeFragment extends Fragment {

     FragmentMeBinding binding;

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
        try {
            binding.nameTxt.setText(PrefManager.getInstance().getString(R.string.fullname));
            binding.emailTxt.setText(PrefManager.getInstance().getString(R.string.email));
            GlideHelper.setImageView(getContext(),binding.profileImg,PrefManager.getInstance().getString(R.string.profileUrl),R.drawable.ic_user);
        }
        catch (Exception e){}
        return binding.getRoot();
    }
}