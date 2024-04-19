// SongReader.java
package com.example.stellarplayer.Service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.R;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SongReader {
    public List<Song> readSongsFromDirectory(Uri directoryUri, Context context) {
        List<Song> songs = new ArrayList<>();
        DocumentFile directory = DocumentFile.fromTreeUri(context, directoryUri);
        DocumentFile[] files = directory.listFiles();

        for (DocumentFile file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                metadataRetriever.setDataSource(context, file.getUri());

                                String title = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                String artist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String album = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                String duration = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//                byte[] coverArt = metadataRetriever.getEmbeddedPicture();
//
//                if (coverArt == null) {
//                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.note_music);
//                    if (bitmap != null) {
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                        coverArt = stream.toByteArray();
//                    } else {
//                        coverArt = new byte[0]; // Set a default byte array
//                    }
//                }

                songs.add(new Song(title, artist, duration, album, file.getUri().toString()));
            }
        }

        return songs;
    }
}