package com.jeff.tovusacco.ui.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityBorrowAmountBinding;
import com.jeff.tovusacco.ui.Home;

public class BorrowAmount extends AppCompatActivity {

    private ActivityBorrowAmountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrow_amount);
        binding = ActivityBorrowAmountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.termsAndConditions.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.buttonContinue.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        binding.buttonContinue.setOnClickListener(v -> {
            Toast.makeText(this, "Continue", Toast.LENGTH_SHORT).show();
            new MaterialAlertDialogBuilder(this)
                    .setIcon(R.drawable.baseline_check_circle_outline_24)
                    .setTitle("Credit Approved")
                    .setMessage("You will receive your funds shortly. Confirm KES. 60,000 should be deposited to Account No xxxxxxxx xxx Equity Bank, Ruaka")
                    .setNegativeButton("Edit", (dialog, which) -> {
                        startActivity(new Intent(this, BorrowAmount.class));
                    })
                    .setPositiveButton("Continue", (dialog, which) -> {
                        startActivity(new Intent(this, Home.class));
                    })
                    .show();
        });

        final int[] loanAmount = {0};
        final int availableCredit = 60000;
        binding.loanAmount.setText(String.valueOf(loanAmount[0]));
        binding.availableCredit.setText(String.valueOf(availableCredit));

        binding.btnAdd.setOnClickListener(v -> {
            if (loanAmount[0] + 1000 > availableCredit) {
                Toast.makeText(this, "You have reached your maximum limit", Toast.LENGTH_SHORT).show();
                binding.btnAdd.setEnabled(false);
            } else {
                loanAmount[0] += 1000;
                binding.loanAmount.setText(String.valueOf(loanAmount[0]));
                if (loanAmount[0] >= availableCredit) {
                    binding.btnAdd.setEnabled(false);
                }
            }
        });

        binding.btnSubtract.setOnClickListener(v -> {
            if (loanAmount[0] <= 0) {
                loanAmount[0] = 0;
            } else {
                loanAmount[0] -= 1000;
                if (loanAmount[0] < availableCredit) {
                    binding.btnAdd.setEnabled(true);
                }
            }
            binding.loanAmount.setText(String.valueOf(loanAmount[0]));
        });
    }
}
