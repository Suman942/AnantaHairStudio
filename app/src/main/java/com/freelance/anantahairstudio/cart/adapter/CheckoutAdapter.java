package com.freelance.anantahairstudio.cart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CheckoutModel;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.NoOfIndividuals;

import java.util.ArrayList;
import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    Context context;
//    ArrayList<CheckoutModel> arrayList;
    ArrayList<CartListResponse.Cart> cartList = new ArrayList<>();

    public CheckoutAdapter(Context context, ArrayList<CartListResponse.Cart> cartList) {
        this.context = context;
        this.cartList = cartList;

    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_list_adapter, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {


//            noOfindividualsFunc( holder,  position);

        holder.noOfIndividuals.setText("Individuals - "+cartList.get(position).getIndividuals());
        holder.serviceName.setText(cartList.get(position).getName());
        holder.price.setText("\u20B9 "+cartList.get(position).getPrice());
        holder.discountPrice.setText("\u20B9 "+cartList.get(position).getDiscountedPrice()+" OFF");
        GlideHelper.setImageView(context,holder.serviceImg,cartList.get(position).getImg(),R.drawable.ic_image_placeholder);

    }


    private void setSpinnerTextColor(AdapterView<?> parent){
        try {
            TextView textView = (TextView) parent.getChildAt(0);
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(12);
        } catch (Exception c) {
            Log.e("MatrimonyCreateProfile", "Unable to set text color" );
        }
    }
    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImg;
        TextView serviceName, price, discountPrice,noOfIndividuals;
        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImg= itemView.findViewById(R.id.serviceImg);
            serviceName= itemView.findViewById(R.id.serviceNameTxt);
            price= itemView.findViewById(R.id.amountTxt);
            discountPrice= itemView.findViewById(R.id.discountAmount);
            noOfIndividuals= itemView.findViewById(R.id.noOfIndividualText);

        }
    }
}
