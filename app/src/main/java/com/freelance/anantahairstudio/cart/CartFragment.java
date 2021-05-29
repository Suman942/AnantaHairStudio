package com.freelance.anantahairstudio.cart;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.adapter.CartAdapter;
import com.freelance.anantahairstudio.databinding.FragmentCartBinding;


public class CartFragment extends Fragment {


    FragmentCartBinding binding;
    CartAdapter cartAdapter ;
    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false);
        initialise();
        return binding.getRoot();
    }

    private void initialise() {
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecyclerView.setEmptyView(binding.emptyCart);
        cartAdapter = new CartAdapter(getContext());
        binding.cartRecyclerView.setAdapter(cartAdapter);
    }
}