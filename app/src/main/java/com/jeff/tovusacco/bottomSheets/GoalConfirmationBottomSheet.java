package com.jeff.tovusacco.bottomSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.databinding.GoalConfirmationBottomSheetBinding;
import com.jeff.tovusacco.ui.goals.groupgoals.GroupGoals;

public class GoalConfirmationBottomSheet extends BottomSheetDialogFragment {

    private GoalConfirmationBottomSheetBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GoalConfirmationBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.termsAndConditions.setOnCheckedChangeListener((buttonView, isChecked) ->
                binding.buttonYes.setVisibility(isChecked ? View.VISIBLE : View.GONE)
        );

        // Set onClickListener for Yes button
        binding.buttonYes.setOnClickListener(v ->
                new MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.baseline_check_circle_outline_24)
                        .setTitle("Success")
                        .setMessage("Goal Created Successfully")
                        .setPositiveButton("Continue", (dialog, which) ->
                                startActivity(new Intent(requireContext(), GroupGoals.class))
                        )
                        .show()
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
