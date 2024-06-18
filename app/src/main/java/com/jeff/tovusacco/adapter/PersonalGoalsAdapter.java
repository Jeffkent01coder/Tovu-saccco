package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.ItemPersonalGoalBinding;
import com.jeff.tovusacco.models.PersonalGoal;
import java.util.List;

public class PersonalGoalsAdapter extends RecyclerView.Adapter<PersonalGoalsAdapter.PersonalGoalsViewHolder> {

    private List<PersonalGoal> goals;
    private OnGoalClickListener clickListener;

    public PersonalGoalsAdapter(List<PersonalGoal> goals, OnGoalClickListener clickListener) {
        this.goals = goals;
        this.clickListener = clickListener;
    }

    public static class PersonalGoalsViewHolder extends RecyclerView.ViewHolder {
        private final ItemPersonalGoalBinding binding;

        public PersonalGoalsViewHolder(ItemPersonalGoalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(PersonalGoal goal, OnGoalClickListener action) {
            binding.currentSavingsAmount.setText(goal.getCurrentSavingsAmount());
            binding.goalTitle.setText(goal.getGoalTitle());

            binding.getRoot().setOnClickListener(view -> action.onGoalClick(goal, getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public PersonalGoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPersonalGoalBinding binding = ItemPersonalGoalBinding.inflate(layoutInflater, parent, false);
        return new PersonalGoalsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalGoalsViewHolder holder, int position) {
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
