package com.example.stellarplayer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Category;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.tvName.setText(category.getNameCategory());

        // Create a horizontal LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.playlistsRecyclerView.getContext(), RecyclerView.HORIZONTAL, false);
        holder.playlistsRecyclerView.setLayoutManager(linearLayoutManager);

        // Create a PlaylistAdapter with the list of playlists from the current category
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(category.getPlaylists());

        // Set the adapter to the RecyclerView in the category item layout
        holder.playlistsRecyclerView.setAdapter(playlistAdapter);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerView playlistsRecyclerView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name_category);
            playlistsRecyclerView = itemView.findViewById(R.id.rev_music);
        }
    }
}