package com.example.stellarplayer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Songs;
import com.example.stellarplayer.R;

import java.util.List;
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<Playlists> playlistList;

    public PlaylistAdapter(List<Playlists> playlistList) {
        this.playlistList = playlistList;
    }

    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, int position) {
        Playlists playlist = playlistList.get(position);
        holder.txtTitle.setText(playlist.getName());
        Songs song = playlist.getSongs().get(0);
        // Assuming that getCover() returns the resource id of the cover image
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPlaylistName;
        public TextView txtTitle;
        public ImageView imgCover;

        public ViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtPlaylist);
        }
    }
}
