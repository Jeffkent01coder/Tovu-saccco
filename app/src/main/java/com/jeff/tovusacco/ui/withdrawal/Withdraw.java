package com.jeff.tovusacco.ui.withdrawal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityWithdrawBinding;
import com.jeff.tovusacco.ui.Home;

public class Withdraw extends AppCompatActivity {

    private ActivityWithdrawBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithdrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(v -> onBackPressed());

        binding.buttonContinue.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(Withdraw.this)
                    .setIcon(R.drawable.baseline_check_circle_outline_24)
                    .setTitle("Success")
                    .setMessage("You have withdrawn KES.10,000 from Rent Goal. You’ll receive KES. 9,950 within 48 HRS")
                    .setPositiveButton("Continue", (dialog, which) -> {
                        startActivity(new Intent(Withdraw.this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    })
                    .show();
        });
    }
}
