package com.freelance.anantahairstudio.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.cart.CartFragment;
import com.freelance.anantahairstudio.databinding.ActivityHomeBinding;
import com.freelance.anantahairstudio.home.HomeFragment;
import com.freelance.anantahairstudio.myInfo.MeFragment;
import com.freelance.anantahairstudio.services.ServicesFragment;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    PagerAdapter pagerAdapter;
    ActivityHomeBinding binding;
    private MenuItem bottomTabMenuItem;
    boolean fromHome;
    String emailSplit[];
    String topic;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        PrefManager.getInstance(this, true);
        intialise();
        getIntents();
        topic = PrefManager.getInstance().getString(R.string.email).substring(0, PrefManager.getInstance().getString(R.string.email).indexOf("@")).trim();
        Log.i("token", topic + "\n");
        notifications();

    }

    @Override
    protected void onStart() {
        super.onStart();
        UpdateApp();
    }

    public void UpdateApp() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);

        com.google.android.play.core.tasks.Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("Newer version is available ");
                alertDialogBuilder.setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, 0);
                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void notifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel(
                    "AnantaHairStudioNotification",
                    "AnantaHairStudio",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(HomeActivity.this, "Notification Sent", Toast.LENGTH_SHORT).show();
                        }
////                        Log.d(TAG, msg);
                    }
                });
    }

    private void getIntents() {
        current = getIntent().getIntExtra("homeScreen", -1);
        if (current == 0) {
            binding.viewPager.setCurrentItem(0);
        } else if (current == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finishAffinity();
                }
            });
            finishAffinity();
            finish();
        }

        try {
            fromHome = getIntent().getBooleanExtra("fromHome", false);
            if (fromHome) {
                binding.viewPager.setCurrentItem(1);
            }
            if (getIntent().getIntExtra("from", -1) == 0) {
                binding.viewPager.setCurrentItem(3);
            }
            if (getIntent().getIntExtra("from", -1) == 1) {
                binding.viewPager.setCurrentItem(1);
            }
        } catch (Exception e) {
        }
    }


    private void intialise() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        binding.viewPager.setPagingEnabled(false);
        binding.viewPager.setAdapter(pagerAdapter);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_tab:
                                binding.viewPager.setCurrentItem(0);
                                break;
                            case R.id.service_tab:
                                binding.viewPager.setCurrentItem(1);
                                break;
                            case R.id.cart_tab:
                                binding.viewPager.setCurrentItem(2);
                                break;
                            case R.id.me_tab:
                                binding.viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (bottomTabMenuItem != null) {
                    bottomTabMenuItem.setChecked(false);
                } else {
                    binding.bottomNavigation.getMenu().getItem(0).setChecked(false);
                }

                binding.bottomNavigation.getMenu().getItem(position).setChecked(true);
                bottomTabMenuItem = binding.bottomNavigation.getMenu().getItem(position);

//                switch (position) {
//                    case 0:
//                        txtToolbarTitle.setText("All Card");
//                        break;
//                    case 1:
//                        txtToolbarTitle.setText("Explore");
//                        break;
//                    case 2:
//                        txtToolbarTitle.setText("Saved Cards");
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        binding.viewPager.setOffscreenPageLimit(3);
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {

        public final int PAGE_COUNT = 4;
        private SparseArray<Fragment> registeredFragments = new SparseArray<>();


        public PagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new HomeFragment();
                case 1:
                    Bundle bundle = new Bundle();
                    if (getIntent().getStringExtra("serviceName") != null) {
                        bundle.putString("serviceName", getIntent().getStringExtra("serviceName"));
                        bundle.putBoolean("fromHome", fromHome);
                    }
                    bundle.putInt("search", getIntent().getIntExtra("search", -1));
                    ServicesFragment servicesFragment = new ServicesFragment();
                    servicesFragment.setArguments(bundle);
                    return servicesFragment;
                case 2:
                    return new CartFragment();
                case 3:
                    return new MeFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }


        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }


}