package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.MusicPlayerActivity;
import com.example.stellarplayer.MyMediaPlayer;
import com.example.stellarplayer.R;

import java.util.ArrayList;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Song songData = songsList.get(position);
        holder.titleTextView.setText(songData.getTitle());
        holder.ArtistTextView.setText(songData.getArtist());
        if(MyMediaPlayer.currentIndex==position){
            holder.titleTextView.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to another acitivty

                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = position;
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("LIST",songsList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Create a new PopupMenu instance
                PopupMenu popup = new PopupMenu(context, v);
                // Inflate the menu resource file
                popup.getMenuInflater().inflate(R.menu.song_popup, popup.getMenu());
                // Set a click listener on the delete item
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        // Remove the item from the songsList and notify the adapter
                        songsList.remove(position);
                        notifyItemRemoved(position);
                        return true;
                    }
                });
                // Show the popup menu
                popup.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,ArtistTextView;
        ImageView iconImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.songTitle);
            ArtistTextView = itemView.findViewById(R.id.songArtist);
        }
    }
}
