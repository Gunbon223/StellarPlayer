package com.example.stellarplayer.Model;

public class Song {
    private String title;
    private String artist;
    private String album;
    private String path;
    private String coverArtPath; // Change this from byte[] to String

    public Song() {
        // Empty constructor required for Firebase
    }

    public Song(String title, String artist, String album, String path, String coverArtPath) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.path = path;
        this.coverArtPath = coverArtPath;
    }

    // Getters and setters for each field
    // ...

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

    public String getCoverArtPath() {
        return coverArtPath;
    }

    public void setCoverArtPath(String coverArtPath) {
        this.coverArtPath = coverArtPath;
    }
}