package com.freelance.anantahairstudio.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityPhotoViewBinding;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;

public class PhotoViewActivity extends AppCompatActivity {
    ActivityPhotoViewBinding binding;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();
    GalleryViewModel galleryViewModel;
    int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_view);
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        observer();
        getIntentData();
    }
    private void getIntentData() {
        current = getIntent().getIntExtra("position",0);
        binding.viewPager.setCurrentItem(current);
    }
    private void observer() {
        galleryViewModel.fetchGalleryImg(PrefManager.getInstance().getString(R.string.authToken));
        galleryViewModel.fetchGalleryImgLiveData().observe(this, new Observer<FetchGalleryResponse>() {
            @Override
            public void onChanged(FetchGalleryResponse fetchGalleryResponse) {
                imageList.addAll(fetchGalleryResponse.getData().getImages());
                GalleryViewAdapter galleryAdapter = new GalleryViewAdapter(getApplicationContext(), imageList);
                binding.viewPager.setAdapter(galleryAdapter);
                binding.viewPager.setCurrentItem(current);
                galleryAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}