package com.example.stellarplayer.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

public class Songs {
    private String name;
    private String path;
    private String artist;
    private String album;

    public Songs() {
        // Empty constructor required for Firebase
    }

    public Songs(String name, String path, String artist, String album) {
        this.name = name;
        this.path = path;
        this.artist = artist;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
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

