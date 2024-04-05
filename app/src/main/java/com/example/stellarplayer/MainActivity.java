package com.example.stellarplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Songs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference songsRef = database.getReference("Songs");

    // Tạo một đối tượng bài hát
        Songs song = new Songs("Beautiful Things", "path/to/song", "Benson Bone", "Album");

    // Lưu thông tin của bài hát vào Firebase Realtime Database
        songsRef.push().setValue(song);
    }
}