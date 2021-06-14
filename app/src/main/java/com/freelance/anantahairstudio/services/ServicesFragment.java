package com.freelance.anantahairstudio.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
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

//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT ) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                switch (direction){
//                    case ItemTouchHelper.RIGHT:
//                        position = viewHolder.getAdapterPosition();
//                        Intent intent = new Intent(getContext(),ServiceDetailsActivity.class);
//                        intent.putExtra("serviceName",serviceList.get(position).getName());
//                        intent.putExtra("serviceImg",serviceList.get(position).getImg());
//                        intent.putExtra("id",serviceList.get(position).getId());
//                        intent.putExtra("price",serviceList.get(position).getPrice());
//                        intent.putExtra("discountedPrice",serviceList.get(position).getDiscountedPrice());
//                        getContext().startActivity(intent);
//                        serviceAdapter.notifyDataSetChanged();
//                        serviceAdapter.notifyItemChanged(position);
//                        break;
//                }
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(binding.serviceRecyclerView);

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                insertServiceListToLocal();
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
               filtercards(newText);
                return false;
            }
        });
//        binding.serviceRecyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                binding.serviceDetails.setVisibility(View.GONE);
//                return false;
//            }
//        });
        return binding.getRoot();
    }

    private void filtercards(String newText) {
        final ArrayList<LocalServiceResponse> filteredList = new ArrayList<>();
        String serviceName;
        for (LocalServiceResponse result : serviceList) {
            serviceName = result.getName();
            if (serviceName.toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(result);
            }
        }
        serviceAdapter.filterList(filteredList);
        binding.serviceRecyclerView.setAdapter(serviceAdapter);
        binding.serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                }
            });

        }
        else {
            servicesDao.getAllServicesLiveData().observe(getViewLifecycleOwner(), new Observer<List<LocalServiceResponse>>() {
                @Override
                public void onChanged(List<LocalServiceResponse> localServiceResponses) {
                    serviceList.addAll(localServiceResponses);
                    serviceAdapter.notifyDataSetChanged();
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