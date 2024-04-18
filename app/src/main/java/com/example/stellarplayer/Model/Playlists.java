package com.example.stellarplayer.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

import java.io.Serializable;
import java.util.List;

public class Playlists implements Serializable {
    private int id;
    private String name;
    private String imageUri;
    private List<Song> songs;
    private static int autId = 0;
    public Playlists(){
        // Empty constructor required for Firebase
    }

    public Playlists(String name, List<Song> songs) {
        this.id = autId++;
        this.name = name;
        this.songs = songs;
    }

    public Playlists(String name) {
        this.id = autId++;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    public Bitmap getAlbumCover(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        byte[] data = mmr.getEmbeddedPicture();
        if (data != null) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } else {
            return null;
        }
    }
}
