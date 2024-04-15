package com.example.stellarplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.SongAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.Service.DBSql;

import java.util.List;

public class PLayList extends AppCompatActivity {
    private RecyclerView playListRec;
    private ImageView musicPic;
    private Button playAll;
    private ImageButton btnImg,btnImg2,backBtn;
    private DBSql db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        Playlists playlist = (Playlists) getIntent().getSerializableExtra("playlist");
        // Now you can use the playlist

        playListRec = findViewById(R.id.musicList);
        musicPic = findViewById(R.id.imageView);
        playAll = findViewById(R.id.playButton);
        btnImg = findViewById(R.id.imageButton6);
        btnImg2 = findViewById(R.id.imageButton5);
        backBtn = findViewById(R.id.gobackBtt);
        db = new DBSql(this);

        List<Song> songs = playlist.getSongs();

        // Initialize the SongAdapter
        SongAdapter songAdapter = new SongAdapter(getApplicationContext(), songs);

        // Set the adapter to the RecyclerView
        playListRec.setLayoutManager(new LinearLayoutManager(this));
        playListRec.setAdapter(songAdapter);
    }
}