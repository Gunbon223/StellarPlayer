package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Category;
import com.example.stellarplayer.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context nContext;
    private List<Category> nListCategory;

    public CategoryAdapter(Context nContext) {
        this.nContext = nContext;
    }
    public void setData(List<Category> list){
        this.nListCategory=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_category,parent,false);

        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = nListCategory.get(position);
        if(category==null){
            return;
        }
        holder.txtNameCategory.setText(category.getNameCategory());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(nContext,RecyclerView.HORIZONTAL,false);
        holder.rcvPlaylist.setLayoutManager(linearLayoutManager);
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(category.getPlaylists());
        holder.rcvPlaylist.setAdapter(playlistAdapter);
    }

    @Override
    public int getItemCount() {
        if(nListCategory!=null){
            return nListCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNameCategory;
        public RecyclerView rcvPlaylist;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameCategory=itemView.findViewById(R.id.tv_name_category);
            rcvPlaylist=itemView.findViewById(R.id.rev_music);
        }
    }
}