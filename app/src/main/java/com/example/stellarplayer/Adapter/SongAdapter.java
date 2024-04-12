package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Songs;
import com.example.stellarplayer.MusicHolder;
import com.example.stellarplayer.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MusicHolder> {

    Context context;
    List<Songs> songsList;

    public SongAdapter(Context context, List<Songs> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(LayoutInflater.from(context).inflate(R.layout.activity_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
        holder.nameView.setText(songsList.get(position).getName());
        holder.imageView.setImageBitmap(songsList.get(position).getAlbumCover(songsList.get(position).getPath()));
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
