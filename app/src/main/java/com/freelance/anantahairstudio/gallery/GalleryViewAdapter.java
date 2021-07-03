package com.freelance.anantahairstudio.gallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;


public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.ProfessionalGalleryViewHolder>{

    Context context;
    ArrayList<FetchGalleryResponse.Data.Image> photoList;

    public GalleryViewAdapter(Context context, ArrayList<FetchGalleryResponse.Data.Image> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ProfessionalGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_image_view, parent, false);
        return new ProfessionalGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionalGalleryViewHolder holder, int position) {

        GlideHelper.setImageView(context,holder.images,""+photoList.get(position).getImage(), R.drawable.ic_image_placeholder);
        Log.i("file",""+photoList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ProfessionalGalleryViewHolder extends RecyclerView.ViewHolder {
        PhotoView images;
        public ProfessionalGalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);

        }
    }
}
