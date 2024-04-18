package com.example.stellarplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.MusicListAdapter;
import com.example.stellarplayer.Adapter.SongAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.Service.DBSql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PLayList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView musicPic;
    private Button playAll;
    private ImageButton btnImg,btnImg2,backBtn;
    private DBSql db;
    private TextView noMusicTextView;
    ArrayList<Song> songsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);

        //Playlists playlist = (Playlists) getIntent().getSerializableExtra("playlist");
        // Now you can use the playlist

        musicPic = findViewById(R.id.imageView);
//        playAll = findViewById(R.id.playButton);
        btnImg = findViewById(R.id.imageButton6);
        btnImg2 = findViewById(R.id.imageButton5);
        backBtn = findViewById(R.id.gobackBtt);
        db = new DBSql(this);

        //List<Song> songs = playlist.getSongs();
        recyclerView = findViewById(R.id.musicList);
        noMusicTextView = findViewById(R.id.no_songs_text);

        if(checkPermission() == false){
            requestPermission();
            return;
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";
        int count = 0;
        try {
            Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
            while(cursor.moveToNext()){
                try {
                    Song songData = new Song(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));                    if(new File(songData.getPath()).exists() && songData.getPath().endsWith(".mp3")) // chỉ xử lý các tệp có đuôi .mp3
                        songsList.add(songData);
                } catch (Exception e) {
                    // Log hoặc xử lý ngoại lệ tại đây nếu cần
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
            //recyclerview
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(songsList,getApplicationContext()));

        System.out.println(songsList.size());
    }

    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(PLayList.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(PLayList.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(PLayList.this,"READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(PLayList.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdapter(songsList,getApplicationContext()));
        }
    }
}