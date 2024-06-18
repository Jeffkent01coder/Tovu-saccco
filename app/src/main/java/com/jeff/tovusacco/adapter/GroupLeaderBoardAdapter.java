package com.jeff.tovusacco.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeff.tovusacco.databinding.GroupLeaderBoardItemBinding;
import com.jeff.tovusacco.models.GroupLeaderBoardData;
import java.util.List;

public class GroupLeaderBoardAdapter extends RecyclerView.Adapter<GroupLeaderBoardAdapter.ViewHolder> {

    private List<GroupLeaderBoardData> list;

    public GroupLeaderBoardAdapter(List<GroupLeaderBoardData> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final GroupLeaderBoardItemBinding binding;

        public ViewHolder(GroupLeaderBoardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(GroupLeaderBoardData data) {
            binding.groupName.setText(data.getGroupName());
            binding.amount.setText(data.getAmount());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        GroupLeaderBoardItemBinding binding = GroupLeaderBoardItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupLeaderBoardData data = list.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
