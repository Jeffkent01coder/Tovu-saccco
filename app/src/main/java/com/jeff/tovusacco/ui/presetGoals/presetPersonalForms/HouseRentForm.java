package com.jeff.tovusacco.ui.presetGoals.presetPersonalForms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityHouseRentFormBinding;
import com.jeff.tovusacco.ui.Home;
import com.jeff.tovusacco.ui.goals.personalGoals.PersonalGoals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HouseRentForm extends AppCompatActivity {

    private ActivityHouseRentFormBinding binding;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHouseRentFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding.btnShowDatePicker.setOnClickListener(view -> showDatePicker());

        binding.btnSave.setOnClickListener(view ->
                new MaterialAlertDialogBuilder(this)
                        .setIcon(R.drawable.baseline_check_circle_outline_24)
                        .setTitle("Success")
                        .setMessage("Goal Created Successfully")
                        .setPositiveButton("Continue", (dialog, which) ->
                                startActivity(new Intent(this, PersonalGoals.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        )
                        .show()
        );

        binding.buttonSaveContinueLater.setOnClickListener(view ->
                startActivity(new Intent(this, Home.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

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

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, monthOfYear, dayOfMonth);
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
