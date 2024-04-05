package Model;

import java.util.List;

public class Playlists {
    private String name;
    private List<Songs> songs;
    public Playlists(){
        // Empty constructor required for Firebase
    }

    public Playlists(String name, List<Songs> songs) {
        this.name = name;
        this.songs = songs;
    }
}
