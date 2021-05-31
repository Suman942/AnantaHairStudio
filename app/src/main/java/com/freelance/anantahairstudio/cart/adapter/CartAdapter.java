package com.freelance.anantahairstudio.cart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CheckoutCartActivity;
import com.google.android.material.card.MaterialCardView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cartLayout;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartLayout= itemView.findViewById(R.id.cartLayout);
        }
    }
}
