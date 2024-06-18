package com.jeff.tovusacco;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.databinding.ActivityMainBinding;
import com.jeff.tovusacco.ui.kyc.SignIn;
import com.jeff.tovusacco.ui.kyc.SignUp;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.signUpButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUp.class));
            finish();
        });

        binding.signInButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SignIn.class));
            finish();
        });
    }
}
