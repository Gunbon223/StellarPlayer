package com.example.stellarplayer;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.SongAdapter;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.Service.DBSql;

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

        List<Song> songsList = new ArrayList<Song>();
        songsList.add(new Song("Ten bai hat","Hoang",2023213 , "ten tac gia", "ten path", null));
        // Initialize the PlaylistAdapter
        playList.setAdapter(new SongAdapter(getApplicationContext(),songsList));

    }
}
