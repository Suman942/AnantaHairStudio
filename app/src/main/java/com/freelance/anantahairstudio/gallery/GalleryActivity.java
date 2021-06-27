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
        clickViews();
    }

    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                if (imageList.size() > 0) {
                    GlideHelper.setImageView(getApplicationContext(), binding.galleryImg, imageList.get(0).getImage(), R.drawable.ic_image_placeholder);
                }

                galleryAdapter = new GalleryAdapter(getApplicationContext(), imageList, GalleryActivity.this::setImageToImageView);
                binding.galleryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                binding.galleryRecyclerView.setAdapter(galleryAdapter);
            }
        });

    }

    private void clickViews() {
        binding.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GalleryActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void initialise() {


    }

    @Override
    public void setImageToImageView(String imageUrl) {
        GlideHelper.setImageView(getApplicationContext(), binding.galleryImg, imageUrl, R.drawable.ic_image_placeholder);
    }
}