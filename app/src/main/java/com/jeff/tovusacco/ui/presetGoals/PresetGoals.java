package com.jeff.tovusacco.ui.presetGoals;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.databinding.ActivityPresetGoalsBinding;
import com.jeff.tovusacco.ui.presetGoals.presetPersonalForms.BusinessForm;
import com.jeff.tovusacco.ui.presetGoals.presetPersonalForms.HouseRentForm;
import com.jeff.tovusacco.ui.presetGoals.presetPersonalForms.NewCarForm;
import com.jeff.tovusacco.ui.presetGoals.presetPersonalForms.PresetLandPurchaseForm;
import com.jeff.tovusacco.ui.presetGoals.presetPersonalForms.SchoolFeesForm;


public class PresetGoals extends AppCompatActivity {

    private ActivityPresetGoalsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPresetGoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.landGoal.setOnClickListener(v -> startActivity(new Intent(this, PresetLandPurchaseForm.class)));
        binding.shoolFeesGoal.setOnClickListener(v -> startActivity(new Intent(this, SchoolFeesForm.class)));
        binding.newCarGoal.setOnClickListener(v -> startActivity(new Intent(this, NewCarForm.class)));
        binding.businessGoal.setOnClickListener(v -> startActivity(new Intent(this, BusinessForm.class)));
        binding.houseGoal.setOnClickListener(v -> startActivity(new Intent(this, HouseRentForm.class)));
    }
}
