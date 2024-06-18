package com.jeff.tovusacco.ui.goals.personalGoals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.R;
import com.jeff.tovusacco.adapter.PersonalGoalsAdapter;
import com.jeff.tovusacco.databinding.ActivityPersonalGoalsBinding;
import com.jeff.tovusacco.models.PersonalGoal;

import java.util.ArrayList;
import java.util.List;

public class PersonalGoals extends AppCompatActivity implements PersonalGoalsAdapter.OnGoalClickListener {
    private ActivityPersonalGoalsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_goals);
        EdgeToEdge.enable(this);

        binding = ActivityPersonalGoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSetNewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalGoals.this, NewPersonalGoal.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        List<PersonalGoal> goals = new ArrayList<>();
        goals.add(new PersonalGoal("300,000", "Binance savings", "", ""));
        goals.add(new PersonalGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new PersonalGoal("300,000", "Binance savings", "", ""));
        goals.add(new PersonalGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new PersonalGoal("300,000", "Binance savings", "", ""));
        goals.add(new PersonalGoal("150,000", "Emergency Fund", "", ""));
        goals.add(new PersonalGoal("300,000", "Binance savings", "", ""));
        goals.add(new PersonalGoal("150,000", "Emergency Fund", "", ""));

        RecyclerView recyclerView = binding.personalGoalRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        PersonalGoalsAdapter adapter = new PersonalGoalsAdapter(goals, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGoalClick(PersonalGoal goal, int position) {
        Intent intent = new Intent(this, PersonalGoalsDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
