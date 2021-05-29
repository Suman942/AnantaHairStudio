package com.freelance.anantahairstudio.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.databinding.ActivityGalleryBinding;
import com.freelance.anantahairstudio.utils.GlideHelper;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.Callback {

    ActivityGalleryBinding binding;
    GalleryAdapter galleryAdapter ;
    ArrayList<GalleryModel> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_gallery);
       initialise();
    }

    private void initialise() {
        imageList.add(new GalleryModel("https://thumbs.dreamstime.com/z/male-hairdresser-work-female-cutting-hair-smiling-men-client-beauty-parlour-34402169.jpg"));
        imageList.add(new GalleryModel("https://www.rd.com/wp-content/uploads/2016/08/07-make-manicure-last.jpg"));
        imageList.add(new GalleryModel("https://thumbs.dreamstime.com/z/man-hair-washing-hairdressing-salon-client-beauty-parlour-33444905.jpg"));
        imageList.add(new GalleryModel("https://thumbs.dreamstime.com/z/male-hairdresser-work-female-cutting-hair-smiling-men-client-beauty-parlour-34402169.jpg"));
        imageList.add(new GalleryModel("https://d2zdpiztbgorvt.cloudfront.net/us/13371/521c96dae3364629a2809a89e2751674-Urban-Fellow-Barbershop-Shave-Parlour-inspiration.PNG"));

        galleryAdapter = new GalleryAdapter(this,imageList,this::setImageToImageView);
       binding.galleryRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
       binding.galleryRecyclerView.setAdapter(galleryAdapter);

        GlideHelper.setImageView(this,binding.galleryImg,imageList.get(0).getImageUrl(),R.drawable.ic_image_placeholder);

    }

    @Override
    public void setImageToImageView(String imageUrl) {
        GlideHelper.setImageView(this,binding.galleryImg,imageUrl,R.drawable.ic_image_placeholder);
    }
}