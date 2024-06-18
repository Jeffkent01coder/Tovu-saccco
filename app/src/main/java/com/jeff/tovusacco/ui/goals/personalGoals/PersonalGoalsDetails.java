package com.jeff.tovusacco.ui.goals.personalGoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityPersonalGoalsDetailsBinding;
import com.jeff.tovusacco.ui.Home;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PersonalGoalsDetails extends AppCompatActivity {
    private ActivityPersonalGoalsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_goals_details);

        binding = ActivityPersonalGoalsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnAccessFinacing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(PersonalGoalsDetails.this)
                        .setIcon(R.drawable.baseline_cancel_24)
                        .setTitle("Failed to access your Credit")
                        .setMessage("You are not eligible for this service at the moment. Credit score not Reached")

                        .setPositiveButton("OK", (dialog, which) -> {
                            // Respond to positive button press
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        binding.btnDeleteGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(PersonalGoalsDetails.this)
                        .setIcon(R.drawable.baseline_cancel_24)
                        .setTitle("Are You sure you want to delete this goal")
                        .setMessage("This goal will be removed from your goals list")

                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Respond to positive button press
                            startActivity(new Intent(PersonalGoalsDetails.this, Home.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Respond to positive button press
                            dialog.dismiss();
                        })
                        .show();
            }
        });
    }
}
