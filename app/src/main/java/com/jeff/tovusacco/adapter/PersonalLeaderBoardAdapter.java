package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.PersonalLeaderBoardItemBinding;
import com.jeff.tovusacco.models.PersonalLeaderBoardData;
import java.util.List;

public class PersonalLeaderBoardAdapter extends RecyclerView.Adapter<PersonalLeaderBoardAdapter.PersonalLeaderBoardViewHolder> {

    private List<PersonalLeaderBoardData> list;

    public PersonalLeaderBoardAdapter(List<PersonalLeaderBoardData> list) {
        this.list = list;
    }

    public static class PersonalLeaderBoardViewHolder extends RecyclerView.ViewHolder {
        private final PersonalLeaderBoardItemBinding binding;

        public PersonalLeaderBoardViewHolder(PersonalLeaderBoardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(PersonalLeaderBoardData data) {
            binding.personName.setText(data.getPersonalName());
            binding.amount.setText(data.getAmount());
        }
    }

    @NonNull
    @Override
    public PersonalLeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PersonalLeaderBoardItemBinding binding = PersonalLeaderBoardItemBinding.inflate(layoutInflater, parent, false);
        return new PersonalLeaderBoardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalLeaderBoardViewHolder holder, int position) {
        PersonalLeaderBoardData data = list.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
