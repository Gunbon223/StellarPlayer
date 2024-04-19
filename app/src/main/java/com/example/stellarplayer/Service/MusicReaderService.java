package com.example.stellarplayer;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import com.example.stellarplayer.Model.Song;

import java.io.File;
import java.util.ArrayList;

public class MusicReaderService extends Service {

    ArrayList<Song> songsList = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC +" != 0";

        try {
            Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
            while(cursor.moveToNext()){
                Song songData = new Song(cursor.getString(1),cursor.getString(0),cursor.getString(2));
                if(new File(songData.getPath()).exists())
                    songsList.add(songData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    public ArrayList<Song> getSongsList() {
        return songsList;
    }
}