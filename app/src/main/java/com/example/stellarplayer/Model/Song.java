package com.example.stellarplayer.Model;

import java.util.Date;

public class Song {

    private int id;
    private String title;
    private String artist;
    private String album;
    private long duration;
    private String path;

    private byte[] coverArtPath; // Change this from byte[] to String

    public Song() {
        // Empty constructor required for Firebase
    }

    public Song( String title, String artist,long duration, String album, String path, byte[] coverArtPath) {
        this.id = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(4));
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.path = path;
        this.coverArtPath = coverArtPath;
    }

    public Song(int id, String title,long duration, String artist, String path, byte[] coverArtPath) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.path = path;
        this.coverArtPath = coverArtPath;
    }

    // Getters and setters for each field
    // ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getCoverArtPath() {
        return coverArtPath;
    }

    public void setCoverArtPath(byte[] coverArtPath) {
        this.coverArtPath = coverArtPath;
    }
}