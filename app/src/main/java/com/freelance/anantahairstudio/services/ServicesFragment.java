package com.freelance.anantahairstudio.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.FragmentServicesBinding;
import com.freelance.anantahairstudio.services.adapter.ServiceAdapter;
import com.freelance.anantahairstudio.services.local.AllServicesDao;
import com.freelance.anantahairstudio.services.local.LocalServiceResponse;
import com.freelance.anantahairstudio.services.local.ServicesDatabase;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.services.viewModel.ServicesViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;


public class ServicesFragment extends Fragment implements ServiceAdapter.Callback{

    FragmentServicesBinding binding;
    ServiceAdapter serviceAdapter;
    String serviceNameId;
    ServicesViewModel servicesViewModel;
    ArrayList<LocalServiceResponse> serviceList = new ArrayList<>();

    ServicesDatabase serviceDatabase;
    AllServicesDao servicesDao;
    int position;


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
        binding.searchView.setFocusable(false);
        InputMethodManager imm = ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
        servicesViewModel  = new ViewModelProvider(getActivity()).get(ServicesViewModel.class);
        serviceDatabase = ServicesDatabase.getDatabase(getContext());
        servicesDao = serviceDatabase.getAllServices();

        initialise();
        getIntents();

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                insertServiceListToLocal();
                servicesDao.getAllServicesLiveData().observe(getViewLifecycleOwner(), new Observer<List<LocalServiceResponse>>() {
                    @Override
                    public void onChanged(List<LocalServiceResponse> localServiceResponses) {
                        serviceList.clear();
                        serviceList.addAll(localServiceResponses);
                        serviceAdapter.notifyDataSetChanged();
                        binding.swipe.setRefreshing(false);
                    }
                });
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    filterServices(newText);
                }
                return false;
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("homeScreen",0);
                startActivity(intent);
//                homeScreen.homeScreen();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return binding.getRoot();
    }

    private void filterServices(String newText) {
         ArrayList<LocalServiceResponse> filteredList = new ArrayList<>();
        String serviceName;
        for (LocalServiceResponse result : serviceList) {
            serviceName = result.getName();
            if (serviceName.toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(result);
            }
        }
        serviceAdapter.filterList(filteredList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ServicesDatabase.getDatabase(getContext()).clearAllTables();
        insertServiceListToLocal();
    }


    private void insertServiceListToLocal() {
        servicesViewModel.getAllServices(PrefManager.getInstance().getString(R.string.authToken));
        servicesViewModel.getAllServicesLiveData().observe(getViewLifecycleOwner(), new Observer<ServicesResponse>() {
            @Override
            public void onChanged(ServicesResponse servicesResponse) {
                if (servicesResponse != null){
                    for (int i = 0; i < servicesResponse.getData().getServices().size(); i++){
                        LocalServiceResponse savedCardData = new LocalServiceResponse(servicesResponse.getData().getServices().get(i).getId(),
                                servicesResponse.getData().getServices().get(i).getCategoryId(),servicesResponse.getData().getServices().get(i).getName(),
                                servicesResponse.getData().getServices().get(i).getPrice(),servicesResponse.getData().getServices().get(i).getDiscountedPrice(),
                                servicesResponse.getData().getServices().get(i).getImg(),servicesResponse.getData().getServices().get(i).getInfo());
                        servicesDao.insert(savedCardData);
                        binding.loader.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void getIntents() {
        if (getArguments().getBoolean("fromHome")) {
            serviceNameId = getArguments().getString("serviceName");

            servicesDao.filterLiveData(serviceNameId).observe(getViewLifecycleOwner(), new Observer<List<LocalServiceResponse>>() {
                @Override
                public void onChanged(List<LocalServiceResponse> localServiceResponses) {
                    serviceList.clear();
                    serviceList.addAll(localServiceResponses);
                    serviceAdapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                }
            });

        }
        else {
            servicesDao.getAllServicesLiveData().observe(getViewLifecycleOwner(), new Observer<List<LocalServiceResponse>>() {
                @Override
                public void onChanged(List<LocalServiceResponse> localServiceResponses) {
                    serviceList.clear();
                    serviceList.addAll(localServiceResponses);
                    serviceAdapter.notifyDataSetChanged();
                    binding.loader.setVisibility(View.GONE);
                }
            });
        }
    }

    private void initialise() {
            serviceAdapter = new ServiceAdapter(getContext(),serviceList,this);
            binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.serviceRecyclerView.setAdapter(serviceAdapter);
        }

    @Override
    public void serviceDetails(String id) {
//        binding.serviceDetails.setVisibility(View.VISIBLE);
    }


}