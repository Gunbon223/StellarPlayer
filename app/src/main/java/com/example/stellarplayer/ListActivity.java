package com.example.stellarplayer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Songs;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private List<Playlists> playlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.listItems);


        // Create a list of Playlists objects
        playlists = new ArrayList<>();
        List<Songs> songs = new ArrayList<>();
        songs.add(new Songs("Playlist 1", "2", "MC","album 1"));
        playlists.add(new Playlists("Playlist 1", songs));
        playlists.add(new Playlists("Playlist cua toi", songs));
        playlists.add(new Playlists("Yeu thich", songs));
        playlists.add(new Playlists("Em", songs));

        // Initialize the PlaylistAdapter
        adapter = new PlaylistAdapter(playlists);

        // Set the adapter to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ListActivity.this, btnMenu);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }

                });

                popup.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}