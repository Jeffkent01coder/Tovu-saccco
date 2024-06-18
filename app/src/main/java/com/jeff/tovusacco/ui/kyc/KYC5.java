package com.jeff.tovusacco.ui.kyc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityKyc5Binding;
import com.jeff.tovusacco.ui.Home;


public class KYC5 extends AppCompatActivity {
    private ActivityKyc5Binding binding;
    private Uri idUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kyc5);

        binding = ActivityKyc5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage.launch("image/*");
            }
        });

        binding.employmentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                String checkedText = checkedRadioButton.getText().toString();
                Toast.makeText(KYC5.this, "Selected: " + checkedText, Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KYC5.this, Home.class));
                finish();
            }
        });

        binding.buttonSaveContinueLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KYC5.this, Home.class));
                finish();
            }
        });
    }

    private final ActivityResultLauncher<String> selectImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(), uri -> {
                binding.selectedImage.setVisibility(View.VISIBLE);
                idUri = uri;
                binding.selectedImage.setImageURI(idUri);
            });
}
