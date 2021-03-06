package com.freelance.anantahairstudio.myBooking.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
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
import com.freelance.anantahairstudio.utils.LocalTime;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    Context context;
    ArrayList<OnGoingServiceResponse.Data.Booking> serviceArrayList ;

    public interface Callback{
        void detail(String bookingId,int position) ;
        void cancel(String bookingId);
    }
    Callback callback;
    public BookingAdapter(Context context, ArrayList<OnGoingServiceResponse.Data.Booking> serviceArrayList,Callback callback) {
        this.context = context;
        this.serviceArrayList = serviceArrayList;
        this.callback = callback;
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

        holder.bookingId.setText("BookingId: #"+serviceArrayList.get(position).getBookingId());
        if (serviceArrayList.get(position).getStatus().equals("new")){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_pending));
            holder.statusTxt.setText("Pending");
        }


        if (serviceArrayList.get(position).getStatus().equals("accepted")){
            holder.statusBackground.setBackground(context.getDrawable(R.drawable.curved_view_accept));
            holder.statusTxt.setText("Confirmed");
        }

        long slot = Long.parseLong(serviceArrayList.get(position).getSlot());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(slot));
        holder.date.setText("Date: "+dateString);

        Log.i("slot",""+slot);
        holder.time.setText("Time: "+ LocalTime.getLocalTime(slot));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.detail(serviceArrayList.get(position).getBookingId(),position);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callback.cancel(serviceArrayList.get(position).getBookingId());
                }
                catch (Exception e){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class BookingViewHolder  extends RecyclerView.ViewHolder {
        View statusBackground;
        TextView statusTxt,time,date,bookingId,pay;
        MaterialCardView layout;
        ImageView cancel;
        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            statusBackground = itemView.findViewById(R.id.statusBackground);
            statusTxt = itemView.findViewById(R.id.adStatusTxt);
            time = itemView.findViewById(R.id.timeTxt);
            date = itemView.findViewById(R.id.dateTxt);
            bookingId = itemView.findViewById(R.id.serviceNameTxt);
            pay = itemView.findViewById(R.id.payTxt);
            layout = itemView.findViewById(R.id.layout);
            cancel = itemView.findViewById(R.id.cancel);

        }
    }
}
