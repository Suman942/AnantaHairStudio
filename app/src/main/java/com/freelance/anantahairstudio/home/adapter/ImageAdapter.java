package com.freelance.anantahairstudio.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.gallery.FetchGalleryResponse;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class ImageAdapter extends SliderViewAdapter<ImageAdapter.SliderAdapterViewHolder> {
    Context mContext;
    ArrayList<FetchGalleryResponse.Data.Image> imageList = new ArrayList<>();

    public ImageAdapter(Context context,    ArrayList<FetchGalleryResponse.Data.Image> imageList) {
        this.mContext = context;
        this.imageList = imageList;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderAdapterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.image_slider_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        GlideHelper.setImageView(mContext,viewHolder.imageViewBackground,imageList.get(position).getImage(),R.drawable.ic_image_placeholder);
    }

    @Override
    public int getCount() {
        return 5;
    }


    public  class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
       // Adapter class for initializing
       // the views of our slider view.
       View itemView;
       ImageView imageViewBackground;

       public SliderAdapterViewHolder(View itemView) {
           super(itemView);
           imageViewBackground = itemView.findViewById(R.id.myimage);
           this.itemView = itemView;
       }
   }
}
