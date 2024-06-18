package com.jeff.tovusacco.bottomSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jeff.tovusacco.databinding.BottomSheetLayoutBinding;
import com.jeff.tovusacco.ui.kyc.CodeVerification;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve phone number and email from arguments
        Bundle args = getArguments();
        String phoneNumber = args != null ? args.getString("phoneNumber") : null;
        String email = args != null ? args.getString("email") : null;

        // Set text for registered number TextView
        binding.textRegisteredNumber.setText(
                "Is this your registered number? " + phoneNumber + " and Correct Email Address? " + email
        );

        // Set onClickListener for Yes button
        binding.buttonYes.setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), CodeVerification.class))
        );

        // Set onClickListener for No button
        binding.buttonNo.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
