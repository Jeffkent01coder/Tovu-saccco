package com.jeff.tovusacco.ui.goals.groupgoals;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityGroupGoalsDetailsBinding;
import com.jeff.tovusacco.ui.Home;

public class GroupGoalsDetails extends AppCompatActivity {

    private ActivityGroupGoalsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupGoalsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(v -> onBackPressed());

        binding.btnAccessFinacing.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(this)
                    .setIcon(R.drawable.baseline_cancel_24)
                    .setTitle("Failed to access your finance")
                    .setMessage("You are not eligible for this service at the moment. Credit score not Reached")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        binding.btnDeleteGoal.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(this)
                    .setIcon(R.drawable.baseline_cancel_24)
                    .setTitle("Are You sure you want to delete this goal")
                    .setMessage("This goal will be removed from your goals list")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        startActivity(new Intent(GroupGoalsDetails.this, Home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}
