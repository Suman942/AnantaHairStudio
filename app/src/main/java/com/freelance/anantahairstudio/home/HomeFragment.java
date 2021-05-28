package com.freelance.anantahairstudio.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.FragmentHomeBinding;
import com.freelance.anantahairstudio.home.adapter.ImageAdapter;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;


    public HomeFragment() {
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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);

        ImageAdapter adapterView = new ImageAdapter(getContext());
        binding.viewPager.setAdapter(adapterView);
        return binding.getRoot();
    }
}