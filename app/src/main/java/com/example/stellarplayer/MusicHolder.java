package com.example.stellarplayer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView nameView;
    public MusicHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.songImage);
        nameView = itemView.findViewById(R.id.txtPlaylist);
    }
}
