package com.freelance.anantahairstudio.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.cart.adapter.CartAdapter;
import com.freelance.anantahairstudio.cart.pojo.CartListResponse;
import com.freelance.anantahairstudio.cart.pojo.RemoveCartResponse;
import com.freelance.anantahairstudio.cart.viewModel.AddToCartViewModel;
import com.freelance.anantahairstudio.databinding.FragmentCartBinding;
import com.freelance.anantahairstudio.utils.PrefManager;

import java.util.ArrayList;


public class CartFragment extends Fragment implements CartAdapter.Callback{


    FragmentCartBinding binding;
    CartAdapter cartAdapter ;
    ArrayList<CartListResponse.Cart> cartList = new ArrayList<>();
    AddToCartViewModel cartViewModel;
    int position;
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
       cartViewModel = new ViewModelProvider(getActivity()).get(AddToCartViewModel.class);
        initialise();
        clickiew();
        observers();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("homeScreen",0);
                startActivity(intent);
//                homeScreen.homeScreen();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction){
                    case ItemTouchHelper.LEFT:
                        position = viewHolder.getAdapterPosition();
                        cartViewModel.removeCart(PrefManager.getInstance().getString(R.string.authToken),cartList.get(position).getId());
//                        cartAdapter.notifyDataSetChanged();
                        cartAdapter.notifyItemChanged(position);
                        binding.loader.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.cartRecyclerView);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        cartList.clear();
        cartViewModel.getCartList(PrefManager.getInstance().getString(R.string.authToken));

    }

    private void observers() {
        cartViewModel.removeCartLiveData().observe(getViewLifecycleOwner(), new Observer<RemoveCartResponse>() {
            @Override
            public void onChanged(RemoveCartResponse removeCartResponse) {
                if (removeCartResponse != null){
                    Toast.makeText(getContext(), "Item removed successfully", Toast.LENGTH_SHORT).show();
                    cartList.clear();
                    cartViewModel.getCartList(PrefManager.getInstance().getString(R.string.authToken));
                    binding.loader.setVisibility(View.GONE);
                }
            }
        });

        cartViewModel.getCartListLiveData().observe(getViewLifecycleOwner(), new Observer<CartListResponse>() {
            @Override
            public void onChanged(CartListResponse cartListResponse) {
                if (cartListResponse != null || cartListResponse.getData().size() > 0){
                    try {
                        cartList.addAll(cartListResponse.getData());
                        cartAdapter.notifyDataSetChanged();
                        binding.loader.setVisibility(View.GONE);
                    }
                    catch (Exception e){}
                    if (cartList.size()==0) {
                        binding.checkout.setVisibility(View.GONE);
                        binding.loader.setVisibility(View.GONE);

                    }
                    else {
                        binding.checkout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void clickiew() {
        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CheckoutCartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initialise() {
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecyclerView.setEmptyView(binding.emptyCart);
        cartAdapter = new CartAdapter(getContext(),cartList,this);
        binding.cartRecyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void delete(String id) {
        cartViewModel.removeCart(PrefManager.getInstance().getString(R.string.authToken),cartList.get(position).getId());
    }
}