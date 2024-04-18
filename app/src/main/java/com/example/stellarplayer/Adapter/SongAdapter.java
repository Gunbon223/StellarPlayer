package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.MusicHolder;
import com.example.stellarplayer.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MusicHolder> {

    Context context;
    List<Song> songsList;

    public SongAdapter(Context context, List<Song> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_cat_playlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
        holder.nameView.setText(songsList.get(position).getTitle());
//        holder.imageView.setImageBitmap(songsList.get(position).getCoverArtPath(songsList.get(position).getPath()));
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
