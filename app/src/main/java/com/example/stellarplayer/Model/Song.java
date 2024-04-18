package com.example.stellarplayer.Model;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String artist;
    private String album;
    private String duration;
    private String path;

//    private byte[] coverArtPath; // Change this from byte[] to String
    public  Song() {}
    public Song(String path, String title, String duration) {
        this.path = path;
        this.title = title;
        this.duration = duration;
    }
    public Song(String title,String artist,String path,  String duration) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Song(String title, String artist, String duration, String album, String path) {
        this.id = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(4));
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.path = path;
//        this.coverArtPath = coverArtPath;
    }

    public Song(int id, String title,String duration, String artist, String path, byte[] coverArtPath) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.artist = artist;
        this.path = path;
//        this.coverArtPath = coverArtPath;
    }

    // Getters and setters for each field
    // ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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

//    public byte[] getCoverArt() {
//        return coverArtPath;
//    }
//
//    public void setCoverArt(byte[] coverArtPath) {
//        this.coverArtPath = coverArtPath;
//    }
}