package com.freelance.anantahairstudio.services;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.FragmentServicesBinding;
import com.freelance.anantahairstudio.services.adapter.ServiceAdapter;


public class ServicesFragment extends Fragment {

    FragmentServicesBinding binding;
    ServiceAdapter serviceAdapter;
    String serviceName;
    public ServicesFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_services, container, false);

        initialise();
        getIntents();


        return binding.getRoot();
    }

    private void getIntents() {
        if (getArguments().getBoolean("fromHome")) {
            serviceName = getArguments().getString("serviceName");
            binding.searchView.setQuery(serviceName, true);
        }
    }

    private void initialise() {
            serviceAdapter = new ServiceAdapter(getContext());
            binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.serviceRecyclerView.setAdapter(serviceAdapter);
        }

}