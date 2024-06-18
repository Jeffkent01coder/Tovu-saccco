package com.jeff.tovusacco.ui.goals.groupgoals;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.adapter.GroupGoalsAdapter;
import com.jeff.tovusacco.databinding.ActivityGroupGoalsBinding;
import com.jeff.tovusacco.models.GroupGoal;

import java.util.ArrayList;
import java.util.List;

public class GroupGoals extends AppCompatActivity implements GroupGoalsAdapter.OnGoalClickListener {
    private ActivityGroupGoalsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupGoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(v -> onBackPressed());

        binding.btnSetNewGoal.setOnClickListener(v -> startActivity(new Intent(GroupGoals.this, NewGroupGoal.class)));

        List<GroupGoal> goals = new ArrayList<>();
        goals.add(new GroupGoal("300,000", "Binance savings", "", ""));
        goals.add(new GroupGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new GroupGoal("300,000", "Binance savings", "", ""));
        goals.add(new GroupGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new GroupGoal("300,000", "Binance savings", "", ""));
        goals.add(new GroupGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new GroupGoal("300,000", "Binance savings", "", ""));
        goals.add(new GroupGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new GroupGoal("300,000", "Binance savings", "", ""));
        goals.add(new GroupGoal("150,000", "Emergency Fund", "", ""));
        // Add more goals here

        RecyclerView recyclerView = binding.groupGoalRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new GroupGoalsAdapter(goals, (GroupGoalsAdapter.OnGoalClickListener) this));
    }

    @Override
    public void onGoalClick(GroupGoal goal, int position) {
        Toast.makeText(this, "You clicked on this category", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GroupGoalsDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
