package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.ItemDashPersonalBinding;
import com.jeff.tovusacco.models.PersonalGoal;
import java.util.List;

public class DashBoardPersonalGoals extends RecyclerView.Adapter<DashBoardPersonalGoals.GroupGoalsViewHolder> {

    private List<PersonalGoal> goals;
    private OnGoalClickListener clickListener;

    public DashBoardPersonalGoals(List<PersonalGoal> goals, OnGoalClickListener clickListener) {
        this.goals = goals;
        this.clickListener = clickListener;
    }

    public static class GroupGoalsViewHolder extends RecyclerView.ViewHolder {
        private final ItemDashPersonalBinding binding;

        public GroupGoalsViewHolder(ItemDashPersonalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(PersonalGoal goal, OnGoalClickListener action) {
            binding.goalTitle.setText(goal.getGoalTitle());
            binding.currentSavingsAmount.setText(goal.getCurrentSavingsAmount());
            binding.savingsGoalAmount.setText(goal.getSavingsGoalAmount());
            binding.progressPercentage.setText(goal.getProgressPercentage());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.onGoalClick(goal, getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public GroupGoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDashPersonalBinding binding = ItemDashPersonalBinding.inflate(layoutInflater, parent, false);
        return new GroupGoalsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupGoalsViewHolder holder, int position) {
        PersonalGoal goal = goals.get(position);
        holder.setData(goal, clickListener);
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public interface OnGoalClickListener {
        void onGoalClick(PersonalGoal goal, int position);
    }
}
