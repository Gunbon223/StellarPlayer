package com.example.stellarplayer.Model;

import java.util.List;

public class Albums {
    private String name;
    private List<Song> songs;
    public Albums(){
        // Empty constructor required for Firebase
    }

    public Albums(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

}
