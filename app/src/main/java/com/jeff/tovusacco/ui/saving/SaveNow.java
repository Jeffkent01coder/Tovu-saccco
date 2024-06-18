package com.jeff.tovusacco.ui.saving;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jeff.tovusacco.databinding.ActivitySaveNowBinding;
import com.jeff.tovusacco.databinding.SavingBottomSheetBinding;
import com.jeff.tovusacco.ui.goals.groupgoals.NewGroupGoal;
import com.jeff.tovusacco.ui.goals.personalGoals.NewPersonalGoal;

public class SaveNow extends AppCompatActivity {

    private ActivitySaveNowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveNowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvBack.setOnClickListener(v -> onBackPressed());

        binding.buttonContinue.setOnClickListener(v -> {
            // Create a new bottom sheet dialog
            BottomSheetDialog dialog = new BottomSheetDialog(SaveNow.this);

            // Inflate the layout using ViewBinding
            SavingBottomSheetBinding savinSheetBinding = SavingBottomSheetBinding.inflate(getLayoutInflater());

            // Set up the dialog's content view
            dialog.setContentView(savinSheetBinding.getRoot());

            // Set click listener for the dismiss button
            savinSheetBinding.btnPersonalGoals.setOnClickListener(view -> {
                startActivity(new Intent(SaveNow.this, NewPersonalGoal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                dialog.dismiss();
            });

            savinSheetBinding.btnGroupGoals.setOnClickListener(view -> {
                startActivity(new Intent(SaveNow.this, NewGroupGoal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                dialog.dismiss();
            });

            // Set cancelable to avoid closing of dialog box when clicking on the screen
            dialog.setCancelable(true);

            // Show the dialog
            dialog.show();
        });
    }
}
