package com.example.stellarplayer.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.MusicPlayerActivity;
import com.example.stellarplayer.MyMediaPlayer;
import com.example.stellarplayer.R;
import com.example.stellarplayer.Service.DBSql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    ArrayList<Song> songsList;

    DBSql dbSql;
    Context context;
    private Playlists[] playlistList;
    private List<Playlists> playlistsArray;

    public MusicListAdapter(ArrayList<Song> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
        this.dbSql = new DBSql(context);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_cat_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Song songData = songsList.get(position);
        playlistsArray = dbSql.getAllPlaylists();
        try {
            holder.bindData(songData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                        if (item.getItemId() == R.id.deleteSong) {
                            songsList.remove(position);
                            notifyItemRemoved(position);
                        } else if (item.getItemId() == R.id.addtoPlaylist) {
//                            showPlaylistDialog(songData);
                        }
                        return true;
                    }
                });
                // Show the popup menu
                popup.show();
                return true;
            }
        });
    }
    private void showPlaylistDialog(Song song) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Playlist");

        // Get the list of playlist names
        List<String> playlistNames = new ArrayList<>();
        for (Playlists playlist : playlistsArray) {
            playlistNames.add(playlist.getName());
        }

        // Convert the list to an array
        String[] playlistNamesArray = new String[playlistNames.size()];
        playlistNames.toArray(playlistNamesArray);

        builder.setItems(playlistNamesArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get the selected playlist
                Playlists selectedPlaylist = playlistsArray.get(which);

                // Add the song to the selected playlist
                selectedPlaylist.getSongs().add(song);

                // Update the playlist in the database
//                dbSql.updatePlaylist(selectedPlaylist);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView, ArtistTextView;
        ImageView iconImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.songTitle);
            ArtistTextView = itemView.findViewById(R.id.songArtist);
            iconImageView = itemView.findViewById(R.id.songImage); // Đảm bảo rằng bạn đã thêm ImageView này vào layout của bạn
        }

        public void bindData(Song song) throws IOException {
            titleTextView.setText(song.getTitle());
            ArtistTextView.setText(song.getArtist());

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(song.getPath());
            byte[] coverArt = retriever.getEmbeddedPicture();

            if (coverArt != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(coverArt, 0, coverArt.length);
                iconImageView.setImageBitmap(bitmap);
            } else {
                iconImageView.setImageResource(R.drawable.note_music); // Đặt ảnh mặc định nếu không có ảnh cover
            }

            retriever.release();
        }
    }
}
