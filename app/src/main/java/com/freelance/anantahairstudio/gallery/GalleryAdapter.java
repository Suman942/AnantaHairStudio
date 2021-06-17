package com.freelance.anantahairstudio.gallery;

import android.content.Context;
import android.media.Image;
import android.telecom.Call;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.utils.GlideHelper;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    Context context;
    ArrayList<FetchGalleryResponse.Data.Image> imageList ;
    String url ;
    public interface Callback{
        void setImageToImageView(String imageUrl);
    }
    Callback callback;

    public GalleryAdapter(Context context, ArrayList<FetchGalleryResponse.Data.Image> imageList,Callback callback,String url) {
        this.context = context;
        this.imageList = imageList;
        this.callback  = callback;
        this.url = url;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_view_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        GlideHelper.setImageView(context,holder.galleryImg,url+imageList.get(position).getImage(),R.drawable.ic_image_placeholder);
        holder.galleryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.setImageToImageView(url+imageList.get(position).getImage());
            }
        });
        Log.i("file",""+url+imageList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryImg;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImg = itemView.findViewById(R.id.galleryImg);
        }
    }
}
