package com.example.stellarplayer.Model;

import java.util.List;

public class Artists {
    private String name;
    private List<Song> songs;
    public Artists(){
        // Empty constructor required for Firebase
    }

    public Artists(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

}
