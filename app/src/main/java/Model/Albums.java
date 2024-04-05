package Model;

import java.util.List;

public class Albums {
    private String name;
    private List<Songs> songs;
    public Albums(){
        // Empty constructor required for Firebase
    }

    public Albums(String name, List<Songs> songs) {
        this.name = name;
        this.songs = songs;
    }

}
