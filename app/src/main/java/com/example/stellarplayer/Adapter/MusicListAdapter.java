package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.MyMediaPlayer;
import com.example.stellarplayer.R;
import com.example.stellarplayer.SongPlayer;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    ArrayList<Song> songsList;
    Context context;

    public MusicListAdapter(ArrayList<Song> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_cat_playlist,parent,false);
        return new MusicListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position) {
        Song songData = songsList.get(position);
        holder.titleTextView.setText(songData.getTitle());
        holder.ArtTextView.setText(songData.getArtist());

        if(MyMediaPlayer.currentIndex==position){
            holder.titleTextView.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current position
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition == RecyclerView.NO_POSITION) {
                    // This can happen if the item is clicked just as it is being removed from the adapter.
                    return;
                }

                // Navigate to SongPlayer activity
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = currentPosition;
                Intent intent = new Intent(context, SongPlayer.class);
                intent.putExtra("SONG", songsList.get(currentPosition)); // Use the current position
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView ArtTextView;

        ImageView iconImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.songTitle);
            ArtTextView = itemView.findViewById(R.id.songArtist);
            iconImageView = itemView.findViewById(R.id.songImage);
        }
    }
}
