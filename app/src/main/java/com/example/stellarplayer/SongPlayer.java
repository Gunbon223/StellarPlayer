package com.example.stellarplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.Service.DBSql;

import java.io.IOException;

public class SongPlayer extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Song currentSong;
    private Playlists playlists;
    private DBSql dbSql;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);
        dbSql = new DBSql(this);
        playlists = dbSql.getPlaylistAllSong();
        ImageButton playPauseButton = findViewById(R.id.playPauseButton);
        TextView songTitle = findViewById(R.id.SongTitle);

        // Get the current intent
        Intent intent = getIntent();

        // Get the song from the intent
        Song currentSong = (Song) intent.getSerializableExtra("SONG");

        // Check if the song is not null before trying to use it
        if (currentSong != null) {
            songTitle.setText(currentSong.getTitle());

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(currentSong.getPath());
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlaying) {
                        mediaPlayer.pause();
                        playPauseButton.setImageResource(R.drawable.play); // Change to your play drawable
                    } else {
                        mediaPlayer.start();
                        playPauseButton.setImageResource(R.drawable.pause); // Change to your pause drawable
                    }
                    isPlaying = !isPlaying;
                }
            });
        } else {
            System.out.println("The song is null");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}