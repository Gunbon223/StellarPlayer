package com.example.stellarplayer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = (OnItemClickListener) listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = (OnItemLongClickListener) longClickListener;
    }


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
//        Songs song = playlist.getSongs().get(0);
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
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            longClickListener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
        });
    }
}
}
