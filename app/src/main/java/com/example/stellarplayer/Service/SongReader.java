package com.example.stellarplayer.Service;

import android.media.MediaMetadataRetriever;

import com.example.stellarplayer.Model.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongReader {
    public List<Song> readSongsFromDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<Song> songs = new ArrayList<>();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                metadataRetriever.setDataSource(file.getAbsolutePath());

                String title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                byte[] coverArt = metadataRetriever.getEmbeddedPicture();

                songs.add(new Song(title, artist, album, file.getAbsolutePath(), coverArt.toString()));
            }
        }

        return songs;
    }
}