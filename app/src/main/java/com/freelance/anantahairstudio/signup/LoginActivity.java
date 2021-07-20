package com.freelance.anantahairstudio.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.freelance.anantahairstudio.R;
import com.freelance.anantahairstudio.activities.HomeActivity;
import com.freelance.anantahairstudio.databinding.ActivityLoginBinding;
import com.freelance.anantahairstudio.referal.ReferalActivity;
import com.freelance.anantahairstudio.signup.pojo.Authentication;
import com.freelance.anantahairstudio.signup.viewModel.AuthenticationLoginViewModel;
import com.freelance.anantahairstudio.utils.PrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth mAuth;
    private  static final int RC_SIGN_IN = 234;
    GoogleSignInClient googleSignInClient;

    AuthenticationLoginViewModel loginViewModel;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        PrefManager.getInstance(this, true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        loginViewModel = new ViewModelProvider(this).get(AuthenticationLoginViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        initializationOfGoogleSigninOption();
        clickViews();
        observer();
    }

    private void observer() {
        loginViewModel.authenticationLiveData().observe(this, new Observer<Authentication>() {
            @Override
            public void onChanged(Authentication authentication) {
                if (authentication != null){
                    PrefManager.getInstance().putString(R.string.authToken,authentication.getData().getToken());
                    if (authentication.getData().getIsNewUser() == 1) {
                        Intent intent = new Intent(LoginActivity.this, ReferalActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                        Log.i("authentication","id: "+authentication.getData().getIsNewUser().intValue());

                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    Log.i("authentication","token: "+authentication.getData().getToken());
                }
            }
        });
    }

    private void clickViews() {
        binding.googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                signIn();
            }
        });
    }

    private void initializationOfGoogleSigninOption() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);

                firebaseAuthwithGoogle(googleSignInAccount);
            } catch (ApiException e) {
                e.printStackTrace();

                Toast.makeText(this, "Technical error has occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            progressDialog.show();
            String name = mAuth.getCurrentUser().getDisplayName();
            String email = mAuth.getCurrentUser().getEmail();
            Uri uri = mAuth.getCurrentUser().getPhotoUrl();
            String profileImg = uri.toString();
            PrefManager.getInstance().putString(R.string.fullname,name);
            PrefManager.getInstance().putString(R.string.email,email);
            PrefManager.getInstance().putString(R.string.profileUrl,profileImg);
            Log.i("authentication","token: "+PrefManager.getInstance().getString(R.string.authToken));

            startActivity(new Intent(this, HomeActivity.class));
            finish();
           progressDialog.dismiss();
        }
    }

    private void firebaseAuthwithGoogle(GoogleSignInAccount accnt) {
//        Toast.makeText(this, "firebaseAuthwithGoogle" + accnt.getId(), Toast.LENGTH_SHORT).show();
        AuthCredential credential = GoogleAuthProvider.getCredential(accnt.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String name = mAuth.getCurrentUser().getDisplayName();
                            String email = mAuth.getCurrentUser().getEmail();
                            Uri uri = mAuth.getCurrentUser().getPhotoUrl();
                            String profileImg = uri.toString();
                            PrefManager.getInstance().putString(R.string.fullname,name);
                            PrefManager.getInstance().putString(R.string.email,email);
                            PrefManager.getInstance().putString(R.string.profileUrl,profileImg);

                            loginViewModel.authentication(email,name);

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

//                            Toast.makeText(LoginActivity.this, "Successfully logged", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}