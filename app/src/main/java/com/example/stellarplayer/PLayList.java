package com.example.stellarplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.Adapter.SongAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Songs;

import java.util.ArrayList;
import java.util.List;

public class PLayList extends AppCompatActivity {
    public RecyclerView playList;
    public ImageView musicPic;
    public DBSql db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        playList.findViewById(R.id.musicList);
        musicPic.findViewById(R.id.imageView);
        db = new DBSql(this);

        List<Songs> songsList = new ArrayList<Songs>();
        songsList.add(new Songs("Ten bai hat", null, "ten tac gia", "nhac lozzz"));
        // Initialize the PlaylistAdapter
        playList.setAdapter(new SongAdapter(getApplicationContext(),songsList));
    }
}
