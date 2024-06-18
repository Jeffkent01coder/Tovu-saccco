package com.jeff.tovusacco.ui.goals.personalGoals;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.bottomSheets.GoalConfirmationBottomSheet;
import com.jeff.tovusacco.databinding.ActivityNewPersonalGoalBinding;
import com.jeff.tovusacco.ui.Home;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.jeff.tovusacco.R;

public class NewPersonalGoal extends AppCompatActivity {

    private ActivityNewPersonalGoalBinding binding;

    private final Calendar calendar = Calendar.getInstance();
    private Uri goalUri;

    private final ActivityResultLauncher<String> selectImage =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                binding.selectedImage.setVisibility(View.VISIBLE);
                goalUri = uri;
                binding.selectedImage.setImageURI(goalUri);
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPersonalGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnShowDatePicker.setOnClickListener(v -> showDatePicker());

        binding.selectImage.setOnClickListener(v -> selectImage.launch("image/*"));

        binding.btnSave.setOnClickListener(v -> showConfirmationDialog());

        binding.buttonSaveContinueLater.setOnClickListener(v -> {
            startActivity(new Intent(this, Home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

//        binding.newPersonalGoalRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            switch (checkedId) {
//                case R.id.daily:
//                    Toast.makeText(this, "Daily Selected", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.Weekly:
//                    Toast.makeText(this, "Weekly Selected", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.Monthly:
//                    Toast.makeText(this, "Monthly Selected", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        });
    }

    private void showConfirmationDialog() {
        GoalConfirmationBottomSheet dialog = new GoalConfirmationBottomSheet();
        dialog.show(getSupportFragmentManager(), "ConfirmationDialog");
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, month, dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedDate.getTime());
                    binding.tvSelectDate.setText(formattedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}
