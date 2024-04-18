package com.example.stellarplayer.Model;

import java.util.List;

public class Category {
    private int id;
    private String nameCategory;
    private List<Playlists> playlists;

    public Category(int id ,String nameCategory, List<Playlists> playlists) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.playlists = playlists;
    }

    public Category() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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