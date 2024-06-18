package com.jeff.tovusacco.ui.kyc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityCodeVerificationBinding;
import com.jeff.tovusacco.ui.Home;

public class CodeVerification extends AppCompatActivity {
    private ActivityCodeVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_code_verification);

        binding = ActivityCodeVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.termsAndConditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkFieldsAndToggleButton();
            }
        });

        binding.otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsAndToggleButton();
            }
        });

        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsAndToggleButton();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp1 = binding.otp.getText().toString();
                String otp2 = binding.otp2.getText().toString();
                boolean termsAccepted = binding.termsAndConditions.isChecked();

                if (otp1.isEmpty() || otp2.isEmpty() || !termsAccepted) {
                    Toast.makeText(CodeVerification.this, "All fields are required and Terms and Conditions must be accepted", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(CodeVerification.this, Home.class));
                    finish();
                }
            }
        });
    }

    private void checkFieldsAndToggleButton() {
        String otp1 = binding.otp.getText().toString();
        String otp2 = binding.otp2.getText().toString();
        boolean termsAccepted = binding.termsAndConditions.isChecked();

        binding.btnNext.setVisibility((otp1.isEmpty() || otp2.isEmpty() || !termsAccepted) ? View.GONE : View.VISIBLE);
    }
}
