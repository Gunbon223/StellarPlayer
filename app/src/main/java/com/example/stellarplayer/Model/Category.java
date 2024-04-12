package com.example.stellarplayer.Model;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<Playlists> playlists;

    public Category(String nameCategory, List<Playlists> playlists) {
        this.nameCategory = nameCategory;
        this.playlists = playlists;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Playlists> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlists> playlists) {
        this.playlists = playlists;
    }
}