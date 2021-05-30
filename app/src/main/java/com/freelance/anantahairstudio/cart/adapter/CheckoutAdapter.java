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
import com.freelance.anantahairstudio.utils.NoOfIndividuals;

import java.util.ArrayList;
import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    Context context;
    ArrayList<CheckoutModel> arrayList;
    private ArrayAdapter<String> genderAdapter;

    public CheckoutAdapter(Context context, ArrayList<CheckoutModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_list_adapter, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {


            noOfindividualsFunc( holder,  position);
    }

    private void noOfindividualsFunc(CheckoutViewHolder holder, int position) {
        genderAdapter = new ArrayAdapter<String>(context
                , android.R.layout.simple_spinner_dropdown_item, NoOfIndividuals.individuals){

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.DKGRAY);
                }
                return view;
            }


        };

        holder.noOfIndi.setAdapter(genderAdapter);
        holder.noOfIndi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setSpinnerTextColor(parent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.noOfIndi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                holder.noOfIndi.showDropDown();
                return false;
            }
        });

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
        return arrayList.size();
    }

    public class CheckoutViewHolder extends RecyclerView.ViewHolder {
       AutoCompleteTextView noOfIndi;
        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            noOfIndi = itemView.findViewById(R.id.noOfIndividual);

        }
    }
}
