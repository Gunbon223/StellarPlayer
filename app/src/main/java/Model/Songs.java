package Model;

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
}

