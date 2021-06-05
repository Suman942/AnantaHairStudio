package com.freelance.anantahairstudio.myInfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.freelance.anantahairstudio.BuildConfig;
import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CheckoutCartActivity;
import com.freelance.anantahairstudio.contactUs.ContactUsActivity;
import com.freelance.anantahairstudio.databinding.FragmentMeBinding;
import com.freelance.anantahairstudio.myInfo.pojo.MyAccountResponse;
import com.freelance.anantahairstudio.myInfo.viewModel.MyAccountInfoViewModel;
import com.freelance.anantahairstudio.ongoingServices.OngoingActivity;
import com.freelance.anantahairstudio.profileedit.EditDetailsActivity;
import com.freelance.anantahairstudio.signup.LoginActivity;
import com.freelance.anantahairstudio.utils.GlideHelper;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.google.firebase.auth.FirebaseAuth;


public class MeFragment extends Fragment {

    FragmentMeBinding binding;
    MyAccountInfoViewModel myAccountInfoViewModel;
    String referralCode;

    public MeFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me, container, false);
        myAccountInfoViewModel = new ViewModelProvider(getActivity()).get(MyAccountInfoViewModel.class);
        try {
            binding.nameTxt.setText(PrefManager.getInstance().getString(R.string.fullname));
            binding.emailTxt.setText(PrefManager.getInstance().getString(R.string.email));
            GlideHelper.setImageView(getContext(), binding.profileImg, PrefManager.getInstance().getString(R.string.profileUrl), R.drawable.ic_user);
        } catch (Exception e) {
        }

        clickViews();
        observers();

        return binding.getRoot();
    }

    private void observers() {
    }

    private void clickViews() {
        binding.contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });

        binding.editLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditDetailsActivity.class);
                startActivity(intent);
            }
        });

        binding.ongoingServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OngoingActivity.class);
                startActivity(intent);
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Referral Code", referralCode);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Referral code copied successfully", Toast.LENGTH_SHORT).show();
            }
        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "AnantaHairStudio");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n"+"You will earn bonus points on using/ "+referralCode;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myAccountInfoViewModel.myAccount(PrefManager.getInstance().getString(R.string.authToken));
        myAccountInfoViewModel.myAccountLiveData().observe(getViewLifecycleOwner(), new Observer<MyAccountResponse>() {
            @Override
            public void onChanged(MyAccountResponse myAccountResponse) {

                try {
                    binding.phoneTxt.setText(myAccountResponse.getData().getBasic().getPhone().toString());
                    PrefManager.getInstance().putString(R.string.phone,myAccountResponse.getData().getBasic().getPhone());
                } catch (Exception e) {
                }

                try {
                    binding.addressTxt.setText(myAccountResponse.getData().getAddress().get(0).getAddress());
                    PrefManager.getInstance().putString(R.string.address,myAccountResponse.getData().getAddress().get(0).getAddress());

                } catch (Exception e) {
                }

                try {
                    binding.pointsTxt.setText(myAccountResponse.getData().getPoints().toString());
                } catch (Exception e) {
                }

                try {
                    referralCode = myAccountResponse.getData().getBasic().getReferral().toString();
                } catch (Exception e) {

                }
            }
        });
    }
}