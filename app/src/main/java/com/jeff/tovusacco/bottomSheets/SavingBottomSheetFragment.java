package com.jeff.tovusacco.bottomSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jeff.tovusacco.databinding.SavingBottomSheetBinding;
import com.jeff.tovusacco.ui.goals.groupgoals.NewGroupGoal;
import com.jeff.tovusacco.ui.goals.personalGoals.NewPersonalGoal;

public class SavingBottomSheetFragment extends BottomSheetDialogFragment {

    private SavingBottomSheetBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SavingBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set onClickListener for Personal Goals button
        binding.btnPersonalGoals.setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), NewPersonalGoal.class))
        );

        // Set onClickListener for Group Goals button
        binding.btnGroupGoals.setOnClickListener(v ->
                startActivity(new Intent(requireActivity(), NewGroupGoal.class))
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
