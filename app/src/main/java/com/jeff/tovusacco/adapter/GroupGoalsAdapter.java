package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.ItemGroupGoalBinding;
import com.jeff.tovusacco.models.GroupGoal;
import java.util.List;

public class GroupGoalsAdapter extends RecyclerView.Adapter<GroupGoalsAdapter.GroupGoalsViewHolder> {

    private List<GroupGoal> goals;
    private OnGoalClickListener clickListener;

    public GroupGoalsAdapter(List<GroupGoal> goals, OnGoalClickListener clickListener) {
        this.goals = goals;
        this.clickListener = clickListener;
    }

    public static class GroupGoalsViewHolder extends RecyclerView.ViewHolder {
        private final ItemGroupGoalBinding binding;

        public GroupGoalsViewHolder(ItemGroupGoalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(GroupGoal goal, OnGoalClickListener action) {
            binding.currentSavingsAmount.setText(goal.getCurrentSavingsAmount());
            binding.goalTitle.setText(goal.getGoalTitle());

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
        ItemGroupGoalBinding binding = ItemGroupGoalBinding.inflate(layoutInflater, parent, false);
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

    public interface OnGoalClickListener {
        void onGoalClick(GroupGoal goal, int position);
    }
}
