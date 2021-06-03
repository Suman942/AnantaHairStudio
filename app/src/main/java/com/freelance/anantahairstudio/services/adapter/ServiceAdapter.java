package com.freelance.anantahairstudio.services.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.services.ServiceDetailsActivity;
import com.freelance.anantahairstudio.services.local.LocalServiceResponse;
import com.freelance.anantahairstudio.services.pojo.ServicesResponse;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    Context context;
    ArrayList<LocalServiceResponse> serviceList;
    public ServiceAdapter(Context context,ArrayList<LocalServiceResponse> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.servicelist_layout,parent,false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        GlideHelper.setImageView(context,holder.serviceImg,"",R.drawable.ic_image_placeholder);
        if (serviceList.get(position).getCategoryId().equals("1")) {
            holder.categoryTxt.setText("Hair");
        }
        holder.amount.setText("\u20B9 "+serviceList.get(position).getPrice());
        holder.discountAmount.setText("\u20B9 "+serviceList.get(position).getDiscountedPrice() + " OFF");
        holder.serviceName.setText(serviceList.get(position).getName());

        holder.serviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ServiceDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImg;
        TextView serviceName, categoryTxt,amount,originalAmount, discountAmount;
        MaterialCardView serviceLayout;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg = itemView.findViewById(R.id.serviceImg);
            serviceName = itemView.findViewById(R.id.serviceNameTxt);
            categoryTxt = itemView.findViewById(R.id.categoryText);
            serviceLayout = itemView.findViewById(R.id.serviceLayout);
            amount = itemView.findViewById(R.id.amountTxt);
            discountAmount = itemView.findViewById(R.id.discountAmount);
        }
    }
    public void filterList(ArrayList<LocalServiceResponse> filterNames){
        this.serviceList = filterNames;
        notifyDataSetChanged();
    }
}
