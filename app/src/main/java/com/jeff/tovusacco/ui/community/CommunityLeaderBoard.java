package com.jeff.tovusacco.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.jeff.tovusacco.R;
import com.jeff.tovusacco.adapter.GroupLeaderBoardAdapter;
import com.jeff.tovusacco.adapter.PersonalLeaderBoardAdapter;
import com.jeff.tovusacco.databinding.ActivityCommunityLeaderBoardBinding;
import com.jeff.tovusacco.models.GroupLeaderBoardData;
import com.jeff.tovusacco.models.PersonalLeaderBoardData;

import java.util.ArrayList;
import java.util.List;

public class CommunityLeaderBoard extends AppCompatActivity {

    private ActivityCommunityLeaderBoardBinding binding;
    private MaterialButton selectedButton;
    private List<String> months;
    private PersonalLeaderBoardAdapter adapter;
    private RecyclerView recyclerView;
    private List<PersonalLeaderBoardData> personalLeaderBoardArrayList;
    private GroupLeaderBoardAdapter adapter2;
    private RecyclerView recyclerView2;
    private List<GroupLeaderBoardData> groupLeaderBoardArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityLeaderBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        selectedButton = null;

        months = new ArrayList<>();
        months.add("January, 2024");
        months.add("February, 2024");
        months.add("March, 2024");
        months.add("April, 2024");
        months.add("May, 2024");
        months.add("June, 2024");
        months.add("July, 2024");
        months.add("August, 2024");
        months.add("September, 2024");
        months.add("October, 2024");
        months.add("November, 2024");
        months.add("December, 2024");

        binding.tvBack.setOnClickListener(view -> onBackPressed());

        personalLeaderBoardArrayList = new ArrayList<>();
        getPersonalLeaderBoard();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = binding.personalGoalsLeaderBoard;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PersonalLeaderBoardAdapter(personalLeaderBoardArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        groupLeaderBoardArrayList = new ArrayList<>();
        getGroupLeaderBoard();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView2 = binding.groupGoalsLeaderBoard;
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        adapter2 = new GroupLeaderBoardAdapter(groupLeaderBoardArrayList);
        recyclerView2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();

        setUpButtons();
    }

    private void getGroupLeaderBoard() {
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wababaz", "90000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wamamaz", "200000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Vijanaz", "60000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("SundaySchool", "500000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wababaz", "90000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wamamaz", "200000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Vijanaz", "60000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("SundaySchool", "500000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wababaz", "90000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Wamamaz", "200000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("Vijanaz", "60000000"));
        groupLeaderBoardArrayList.add(new GroupLeaderBoardData("SundaySchool", "500000"));
    }

    private void getPersonalLeaderBoard() {
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Brian", "100000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Jeff", "70000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Brian", "8000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Ham", "100000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Lee", "30000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Ian", "100000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Brian", "20000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Kevo", "1000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Brian", "30000"));
        personalLeaderBoardArrayList.add(new PersonalLeaderBoardData("Jian", "102000"));
    }

    private void setUpButtons() {
        for (String month : months) {
            MaterialButton button = new MaterialButton(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
            button.setText(month);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMarginEnd(dpToPx(16));
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(v -> selectButton(button));
            binding.buttonContainer.addView(button);
        }
    }

    private void selectButton(MaterialButton button) {
        if (selectedButton != null) {
            // Revert previously selected button to outlined style
            selectedButton.setBackgroundColor(ContextCompat.getColor(selectedButton.getContext(), R.color.outlined_button_color));
            ViewCompat.setElevation(selectedButton, 0f);
        }

        // Set newly selected button to elevated style
        button.setBackgroundColor(ContextCompat.getColor(button.getContext(), R.color.elevated_button_color));
        ViewCompat.setElevation(button, 8f);
        selectedButton = button;
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
