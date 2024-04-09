package com.example.stellarplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LibaryActivity extends AppCompatActivity {
    Button btnAlbums,btnPlaylists,btnAllSongs,btnFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libary);
        btnAlbums = findViewById(R.id.btnAlbums);
        btnPlaylists = findViewById(R.id.btnPlayLists);
        btnAllSongs = findViewById(R.id.btnAllSongs);
        btnFav = findViewById(R.id.btnFav);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btnAlbums.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Hello, World!");
                    Intent intent = new Intent(LibaryActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.transition.slide_in_right, R.transition.slide_in_right);

                }
            });
            btnPlaylists.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LibaryActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btnAllSongs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LibaryActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LibaryActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            });
            return insets;


        });
    }
}