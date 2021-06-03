package com.freelance.anantahairstudio.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.FragmentHomeBinding;
import com.freelance.anantahairstudio.gallery.GalleryActivity;
import com.freelance.anantahairstudio.home.adapter.ImageAdapter;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    final String storeLocation = "Ananta Hair Studio,Plot no 270 ,4th B Road Near Suhag Jewellers Sardarpura raj, Jodhpur, Rajasthan 342001";


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

        initialise();
        clickViews();

        return binding.getRoot();
    }

    private void clickViews() {
        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                startActivity(intent);
            }
        });
        binding.haircut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","1");
                startActivity(intent);
            }
        });
        binding.hairColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","8");
                startActivity(intent);
            }
        });

        binding.blech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","8");
                startActivity(intent);
            }
        });

        binding.shaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","2");
                startActivity(intent);
            }
        });
        binding.straightening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","9");
                startActivity(intent);
            }
        });

        binding.pedicure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","11");
                startActivity(intent);
            }
        });

        binding.brideGroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","99");
                startActivity(intent);
            }
        });

        binding.manicure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , HomeActivity.class);
                intent.putExtra("fromHome",true);
                intent.putExtra("serviceName","10");
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
    }

    private void initialise() {
        ImageAdapter adapterView = new ImageAdapter(getContext());
        binding.viewPager.setAdapter(adapterView);
    }
}