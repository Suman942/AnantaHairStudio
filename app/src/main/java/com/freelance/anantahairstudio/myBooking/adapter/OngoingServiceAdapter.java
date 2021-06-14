package com.freelance.anantahairstudio.myBooking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.myBooking.pojo.OnGoingServiceResponse;

import java.util.ArrayList;

public class OngoingServiceAdapter extends RecyclerView.Adapter<OngoingServiceAdapter.OngoingViewHolder> {

    Context context;
    ArrayList<OnGoingServiceResponse.Data.Service> serviceArrayList ;

    public OngoingServiceAdapter(Context context, ArrayList<OnGoingServiceResponse.Data.Service> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
    }



    @NonNull
    @Override
    public OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ongoing_service_layout,parent,false);
        return new OngoingViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull OngoingViewHolder holder, int position) {


        holder.serviceName.setText(serviceArrayList.get(position).getName());
        holder.price.setText("\u20B9 "+serviceArrayList.get(position).getPrice());
        holder.discountPrice.setText("\u20B9 "+serviceArrayList.get(position).getDiscountedPrice()+" OFF");
        holder.individuals.setText("Individuals - "+serviceArrayList.get(position).getIndividuals());

    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class OngoingViewHolder extends RecyclerView.ViewHolder {
        View statusBackground;
        TextView statusTxt, serviceName,price,discountPrice,individuals,time,date;
        ImageView serviceImg;

        public OngoingViewHolder(@NonNull View itemView) {
            super(itemView);
            statusBackground = itemView.findViewById(R.id.statusBackground);
            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            serviceName = itemView.findViewById(R.id.serviceNameTxt);
            serviceImg = itemView.findViewById(R.id.serviceImg);
            price = itemView.findViewById(R.id.amountTxt);
            discountPrice = itemView.findViewById(R.id.discountAmount);
            individuals = itemView.findViewById(R.id.noOfIndividualText);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);

        }
    }
}
