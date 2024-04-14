package com.example.stellarplayer.Service;

import android.media.MediaMetadataRetriever;

import com.example.stellarplayer.Model.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;

import androidx.documentfile.provider.DocumentFile;

public class SongReader {
    public List<Song> readSongsFromDirectory(Uri directoryUri, Context context, DBSql dbSql) {
        List<Song> songs = new ArrayList<>();

        // Get the document tree from the Uri
        DocumentFile directory = DocumentFile.fromTreeUri(context, directoryUri);
        // Get the children of the directory
        DocumentFile[] files = directory.listFiles();

        for (DocumentFile file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                metadataRetriever.setDataSource(context, file.getUri());

                String title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                long duration = Long.parseLong(metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                byte[] coverArt = metadataRetriever.getEmbeddedPicture();
                songs.add(new Song(title, artist, duration, album, file.getUri().toString(), coverArt));
            }
        }

        return songs;
    }
}