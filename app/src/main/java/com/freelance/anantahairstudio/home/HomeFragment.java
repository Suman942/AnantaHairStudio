package com.freelance.anantahairstudio.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.FragmentHomeBinding;
import com.freelance.anantahairstudio.gallery.FetchGalleryResponse;
import com.freelance.anantahairstudio.gallery.GalleryActivity;
import com.freelance.anantahairstudio.gallery.GalleryAdapter;
import com.freelance.anantahairstudio.gallery.GalleryViewModel;
import com.freelance.anantahairstudio.home.adapter.ImageAdapter;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    final String storeLocation = "Ananta Hair Studio,Plot no 270 ,4th B Road Near Suhag Jewellers Sardarpura raj, Jodhpur, Rajasthan 342001";
    GalleryViewModel galleryViewModel;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();


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
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        initialise();
        clickViews();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("homeScreen",1);
                startActivity(intent);
//                getActivity().finishAffinity();
//                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return binding.getRoot();
    }

    private void clickViews() {
        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("search",0);
                startActivity(intent);
            }
        });
        binding.haircut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","100");
                startActivity(intent);
            }
        });
        binding.facial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","103");
                startActivity(intent);
            }
        });

        binding.dTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","102");
                startActivity(intent);
            }
        });

        binding.shaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","101");
                startActivity(intent);
            }
        });
        binding.straightening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","104");
                startActivity(intent);
            }
        });

        binding.pedicure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","105");
                startActivity(intent);
            }
        });

        binding.brideGroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","106");
                startActivity(intent);
            }
        });

        binding.manicure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","107");
                startActivity(intent);
            }
        });

        binding.allServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                startActivity(intent);
            }
        });

        binding.packageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","116");
                startActivity(intent);
            }
        });

        binding.locateStudioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchAddress = new  Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+storeLocation));
                startActivity(searchAddress);
            }
        });

        binding.storeLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchAddress = new  Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+storeLocation));
                startActivity(searchAddress);
            }
        });

        binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GalleryActivity.class));
            }
        });

        binding.slideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GalleryActivity.class));
            }
        });
    }

    private void initialise() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(getViewLifecycleOwner(), new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                ImageAdapter adapterView = new ImageAdapter(getContext(),imageList);
                binding.viewPager.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                binding.viewPager.setSliderAdapter(adapterView);
                binding.viewPager.setScrollTimeInSec(3);
                binding.viewPager.setAutoCycle(true);
                binding.viewPager.startAutoCycle();            }
        });

    }


}