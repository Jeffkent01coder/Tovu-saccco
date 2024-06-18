package com.jeff.tovusacco.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.adapter.DashBoardGroupGoals;
import com.jeff.tovusacco.adapter.DashBoardPersonalGoals;
import com.jeff.tovusacco.databinding.ActivityHomeBinding;
import com.jeff.tovusacco.models.GroupGoal;
import com.jeff.tovusacco.models.PersonalGoal;
import com.jeff.tovusacco.ui.community.CommunityLeaderBoard;
import com.jeff.tovusacco.ui.goals.groupgoals.GroupGoals;
import com.jeff.tovusacco.ui.goals.groupgoals.GroupGoalsDetails;
import com.jeff.tovusacco.ui.goals.personalGoals.PersonalGoals;
import com.jeff.tovusacco.ui.goals.personalGoals.PersonalGoalsDetails;
import com.jeff.tovusacco.ui.kyc.KYC5;
import com.jeff.tovusacco.ui.loan.Loan;
import com.jeff.tovusacco.ui.presetGoals.PresetGoals;
import com.jeff.tovusacco.ui.saving.SaveNow;
import com.jeff.tovusacco.ui.withdrawal.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements DashBoardGroupGoals.OnGoalClickListener,
        DashBoardPersonalGoals.OnGoalClickListener {

    private ActivityHomeBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        setupNavigationView();

        binding.btnPersonalGoals.setOnClickListener(v -> startActivity(new Intent(Home.this, PersonalGoals.class)));
        binding.btnGroupGoals.setOnClickListener(v -> startActivity(new Intent(Home.this, GroupGoals.class)));
        binding.btnPresetGoals.setOnClickListener(v -> startActivity(new Intent(Home.this, PresetGoals.class)));
        binding.btnJoinSacco.setOnClickListener(v -> startActivity(new Intent(Home.this, KYC5.class)));
        binding.btnSaveNow.setOnClickListener(v -> startActivity(new Intent(Home.this, SaveNow.class)));
        binding.btnCommunity.setOnClickListener(v -> startActivity(new Intent(Home.this, CommunityLeaderBoard.class)));

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                binding.myDrawerLayout,
                R.string.nav_open,
                R.string.nav_close
        );
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.sideMenu.setOnClickListener(v -> {
            if (binding.myDrawerLayout.isDrawerOpen(binding.navigationView)) {
                binding.myDrawerLayout.closeDrawer(binding.navigationView);
            } else {
                binding.myDrawerLayout.openDrawer(binding.navigationView);
            }
        });

        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.personalGoalsRecyclerView.setLayoutManager(layoutManagerHorizontal);
        binding.personalGoalsRecyclerView.setAdapter(new DashBoardPersonalGoals(getPersonalGoals(), (DashBoardPersonalGoals.OnGoalClickListener) this));

        LinearLayoutManager layoutManagerHorizontal2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.groupGoalsRecyclerView.setLayoutManager(layoutManagerHorizontal2);
        binding.groupGoalsRecyclerView.setAdapter(new DashBoardPersonalGoals(getGroupGoals(), (DashBoardPersonalGoals.OnGoalClickListener) this));
    }

    private void setupNavigationView() {
        NavigationView navigationView = binding.navigationView;
        navigationView.inflateMenu(R.menu.navigation_drawer_menu);

        View headerView = getLayoutInflater().inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);

        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem menuItem = navigationView.getMenu().getItem(i);
            MaterialButton customButton = (MaterialButton) getLayoutInflater().inflate(R.layout.menu_item_layout, null);
            customButton.setText(menuItem.getTitle());
            customButton.setOnClickListener(view -> onNavigationItemSelected(menuItem));
            menuItem.setActionView(customButton);
        }
    }

    private void onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.menu_item_1:
//                startActivity(new Intent(Home.this, Withdraw.class));
//                break;
//            case R.id.menu_item_2:
//                startActivity(new Intent(Home.this, CommunityLeaderBoard.class));
//                break;
//            case R.id.menu_item_3:
//                startActivity(new Intent(Home.this, Loan.class));
//                break;
//            case R.id.menu_item_4:
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                String shareBody = "You have been invited to join Tovusacco";
//                String shareSubject = "Your world class sacco";
//                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
//                startActivity(Intent.createChooser(sharingIntent, "Share using"));
//                break;
//            case R.id.menu_item_5:
//                startActivity(new Intent(Home.this, CommunityLeaderBoard.class));
//                break;
//            // Add more cases as needed
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGoalClick(GroupGoal goal, int position) {
        startActivity(new Intent(this, GroupGoalsDetails.class));
    }

    @Override
    public void onGoalClick(PersonalGoal goal, int position) {
        startActivity(new Intent(this, PersonalGoalsDetails.class));
    }

    private List<PersonalGoal> getPersonalGoals() {
        List<PersonalGoal> personalGoals = new ArrayList<>();
        personalGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Crypto savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Car savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Boat savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Rent savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("House savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        personalGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        return personalGoals;
    }

    private List<PersonalGoal> getGroupGoals() {
        List<PersonalGoal> groupGoals = new ArrayList<>();
        groupGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Crypto savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Car savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Boat savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Rent savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("House savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        groupGoals.add(new PersonalGoal("Binance savings", "150,000", "15,0000,000", "80"));
        return groupGoals;
    }
}

