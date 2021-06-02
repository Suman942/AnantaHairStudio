package com.freelance.anantahairstudio.ongoingServices;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;

public class OngoingServiceAdapter extends RecyclerView.Adapter<OngoingServiceAdapter.OngoingViewHolder> {

    Context context;

    public OngoingServiceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ongoing_service_layout,parent,false);
        return new OngoingViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull OngoingViewHolder holder, int position) {

        if (position == 0){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_reject));
            holder.statusTxt.setText("Rejected");
        }
        if (position == 1){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_accept));
            holder.statusTxt.setText("Confirmed");
        }
        if (position == 2){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_pending));
            holder.statusTxt.setText("Pending");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class OngoingViewHolder extends RecyclerView.ViewHolder {
        View statusBackground;
        TextView statusTxt;
        public OngoingViewHolder(@NonNull View itemView) {
            super(itemView);
            statusBackground = itemView.findViewById(R.id.statusBackground);
            statusTxt = itemView.findViewById(R.id.adStatusTxt);

        }
    }
}
