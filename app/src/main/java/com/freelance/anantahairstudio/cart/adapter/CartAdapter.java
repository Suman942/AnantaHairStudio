package com.freelance.anantahairstudio.cart.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CheckoutCartActivity;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<CartListResponse.Cart> cartList = new ArrayList<>();

    public interface Callback {
        void delete(String id);
    }

    Callback callback;

    public CartAdapter(Context context, ArrayList<CartListResponse.Cart> cartList, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.noOfIndividuals.setText("Individuals - " + cartList.get(position).getIndividuals());
        holder.serviceName.setText(cartList.get(position).getName());
        holder.price.setText("\u20B9 " + cartList.get(position).getPrice());
        holder.discountPrice.setText("\u20B9 " + cartList.get(position).getDiscountedPrice() + " OFF");
        GlideHelper.setImageView(context, holder.serviceImg, cartList.get(position).getImg(), R.drawable.ic_image_placeholder);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callback.delete(cartList.get(position).getId());
                }
                catch (Exception e){}
            }

        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        ImageView serviceImg;
        TextView serviceName, price, discountPrice, noOfIndividuals;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.delete);
            serviceImg = itemView.findViewById(R.id.serviceImg);
            serviceName = itemView.findViewById(R.id.serviceNameTxt);
            price = itemView.findViewById(R.id.amountTxt);
            discountPrice = itemView.findViewById(R.id.discountAmount);
            noOfIndividuals = itemView.findViewById(R.id.noOfIndividualText);

        }
    }
}
