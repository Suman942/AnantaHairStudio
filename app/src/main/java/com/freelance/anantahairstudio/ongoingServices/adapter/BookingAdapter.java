package com.freelance.anantahairstudio.ongoingServices.adapter;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.ongoingServices.pojo.OnGoingServiceResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    Context context;
    ArrayList<OnGoingServiceResponse.Data> serviceArrayList ;

    public BookingAdapter(Context context, ArrayList<OnGoingServiceResponse.Data> serviceArrayList) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_layout,parent,false);
        return new BookingViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
//        if (position == 0){
//            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_reject));
//            holder.statusTxt.setText("Rejected");
//        }
//        if (position == 1){
//            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_accept));
//            holder.statusTxt.setText("Confirmed");
//        }
//        if (position == 2){
//
//        }
        holder.bookingId.setText("BookingId: #"+serviceArrayList.get(position).getBookingId());
        if (serviceArrayList.get(position).getStatus().equals("new")){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_pending));
            holder.statusTxt.setText("Pending");
        }
        long slot = Long.parseLong(serviceArrayList.get(position).getSlot());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(slot));
        holder.date.setText("Date: "+dateString);

        Date date = new Date(slot);
        String hr = String.valueOf(date.getHours());
        String mm = String.valueOf(date.getMinutes());
        if (date.getHours() >= 12) {
            holder.time.setText("Time: " + hr + ":" + mm+" PM");
        }
        else {
            holder.time.setText("Time: " + hr + ":" + mm+" AM");
        }



    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class BookingViewHolder  extends RecyclerView.ViewHolder {
        View statusBackground;
        TextView statusTxt,time,date,bookingId;
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            statusBackground = itemView.findViewById(R.id.statusBackground);
            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);
            bookingId = itemView.findViewById(R.id.serviceNameTxt);

        }
    }
}
