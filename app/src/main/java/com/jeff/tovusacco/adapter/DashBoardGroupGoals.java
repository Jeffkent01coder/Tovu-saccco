package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.ItemDashGroupBinding;
import com.jeff.tovusacco.models.GroupGoal;

import java.util.List;

public class DashBoardGroupGoals extends RecyclerView.Adapter<DashBoardGroupGoals.GroupGoalsViewHolder> {

    private List<GroupGoal> goals;
    private OnGoalClickListener clickListener;

    public DashBoardGroupGoals(List<GroupGoal> goals, OnGoalClickListener clickListener) {
        this.goals = goals;
        this.clickListener = clickListener;
    }

    public static class GroupGoalsViewHolder extends RecyclerView.ViewHolder {
        private final ItemDashGroupBinding binding;

        public GroupGoalsViewHolder(ItemDashGroupBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(GroupGoal goal, OnGoalClickListener action) {
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

    public interface OnGoalClickListener {
        void onGoalClick(GroupGoal goal, int position);
    }

    @NonNull
    @Override
    public GroupGoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDashGroupBinding binding = ItemDashGroupBinding.inflate(layoutInflater, parent, false);
        return new GroupGoalsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupGoalsViewHolder holder, int position) {
        GroupGoal goal = goals.get(position);
        holder.setData(goal, clickListener);
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
}
