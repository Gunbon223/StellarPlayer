package com.example.stellarplayer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.Adapter.SongAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Service.DBSql;

import java.util.List;

public class ItemMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private List<Playlists> playlistsList;
    DBSql dbSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_menu);
        playlistsList = dbSql.getAllPlaylists();

    }
}