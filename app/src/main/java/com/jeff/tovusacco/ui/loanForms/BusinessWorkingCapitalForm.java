package com.jeff.tovusacco.ui.loanForms;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityBusinessWorkingCapitalFormBinding;
import com.jeff.tovusacco.ui.loan.BorrowAmount;

public class BusinessWorkingCapitalForm extends AppCompatActivity {

    private ActivityBusinessWorkingCapitalFormBinding binding;

    private Uri goalUri;
    private Uri cvUri;
    private Uri cv2Uri;

    private final ActivityResultLauncher<String> selectImage = registerForActivityResult(new ActivityResultContracts.GetContent(),
            uri -> {
                binding.selectedImage.setVisibility(View.VISIBLE);
                goalUri = uri;
                binding.selectedImage.setImageURI(goalUri);
            });

    private final ActivityResultLauncher<Intent> pdfActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Log.d(ContentValues.TAG, "PDF Picked");
                    Intent data = result.getData();
                    cvUri = data.getData();
                    cv2Uri = data.getData();
                } else {
                    Log.d(ContentValues.TAG, "PDF Picking Cancelled");
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_business_working_capital_form);
        binding = ActivityBusinessWorkingCapitalFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.nextOfKinDetails.setOnClickListener(v -> selectImage.launch("image/*"));

        binding.selectContract.setOnClickListener(v -> pickPdfIntent());
        binding.selectBankStatement.setOnClickListener(v -> pickPdfIntent());

        binding.buttonContinue.setOnClickListener(v -> {
            startActivity(new Intent(this, BorrowAmount.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }

    private void pickPdfIntent() {
        Log.d(ContentValues.TAG, "Starting pdf picking Intent");
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pdfActivityResultLauncher.launch(intent);
    }
}
