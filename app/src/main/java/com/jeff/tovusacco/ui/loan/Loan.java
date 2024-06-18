package com.jeff.tovusacco.ui.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityLoanBinding;
import com.jeff.tovusacco.ui.loanForms.BusinessWorkingCapitalForm;
import com.jeff.tovusacco.ui.loanForms.CarLoanForm;
import com.jeff.tovusacco.ui.loanForms.LandPurchaseForm;
import com.jeff.tovusacco.ui.loanForms.LoanRegistrationForm;
import com.jeff.tovusacco.ui.loanForms.LogBookForm;

public class Loan extends AppCompatActivity {

    private ActivityLoanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan);
        binding = ActivityLoanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(v -> onBackPressed());

        binding.carLoan.setOnClickListener(v -> {
            startActivity(new Intent(this, CarLoanForm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.logBookLoan.setOnClickListener(v -> {
            startActivity(new Intent(this, LogBookForm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.personalLoan.setOnClickListener(v -> {
            startActivity(new Intent(this, LoanRegistrationForm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.landPurchaseLoan.setOnClickListener(v -> {
            startActivity(new Intent(this, LandPurchaseForm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        binding.buisnessWorkingCapitalLoan.setOnClickListener(v -> {
            startActivity(new Intent(this, BusinessWorkingCapitalForm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }
}
