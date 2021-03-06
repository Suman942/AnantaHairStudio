package com.freelance.anantahairstudio.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityGalleryBinding;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.Callback {

    ActivityGalleryBinding binding;
    GalleryAdapter galleryAdapter;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();
    GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        observer();
        initialise();
    }

    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                galleryAdapter = new GalleryAdapter(getApplicationContext(), imageList, GalleryActivity.this::setImageToImageView);
                binding.galleryRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                binding.galleryRecyclerView.setAdapter(galleryAdapter);
                binding.loader.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GalleryActivity.this, HomeActivity.class));
        finish();
    }

    private void initialise() {


    }

    @Override
    public void setImageToImageView(String imageUrl) {
    }
}