package com.jeff.tovusacco.ui.loanForms;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.ActivityLandPurchaseFormBinding;
import com.jeff.tovusacco.ui.loan.BorrowAmount;

public class LandPurchaseForm extends AppCompatActivity {

    private ActivityLandPurchaseFormBinding binding;
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
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Log.d(ContentValues.TAG, "PDF Picked");
                        Intent data = result.getData();
                        cvUri = data.getData();
                        cv2Uri = data.getData();
                    } else {
                        Log.d(ContentValues.TAG, "PDF Picking Cancelled");
                        Toast.makeText(LandPurchaseForm.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_purchase_form);
        binding = ActivityLandPurchaseFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().show();

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
