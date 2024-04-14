package com.example.stellarplayer.Service;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.stellarplayer.Model.Category;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;

import java.util.ArrayList;
import java.util.List;

public class DBSql extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StellarPlayer.db";
    private static final int DATABASE_VERSION = 1;

    public DBSql(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Playlists (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT)";
        db.execSQL(createTableSql);
        // Create Category table
        String createCategoryTableSql = "CREATE TABLE IF NOT EXISTS Category (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT)";
        db.execSQL(createCategoryTableSql);
        String createSongTableSql = "CREATE TABLE IF NOT EXISTS Song (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT," +
                "artist TEXT," +
                "album TEXT," +
                "path TEXT)";
        db.execSQL(createSongTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Playlists");
        onCreate(db);
    }

    public List<Playlists> getAllPlaylists() {
        List<Playlists> playlists = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Playlists", null);

        if (cursor.moveToFirst()) {
            do {
                Playlists playlist = new Playlists();
                playlist.setId(cursor.getInt(0));
                playlist.setName(cursor.getString(1));
                playlists.add(playlist);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return playlists;
    }

    public void addPlaylist(Playlists playlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO Playlists (name) VALUES ('" + playlist.getName() + "')");
    }

    public void deletePlaylist(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Playlists WHERE id = " + id);
    }

    public void updatePlaylist(Playlists playlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE Playlists SET name = '" + playlist.getName() + "' WHERE id = " + playlist.getId());
    }

    public void deleteAllPlaylists() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Playlists");
    }

    public void addSongToPlaylist(int playlistId, int songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO PlaylistSongs (playlistId, songId) VALUES (" + playlistId + ", " + songId + ")");
    }

    public void removeSongFromPlaylist(int playlistId, int songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM PlaylistSongs WHERE playlistId = " + playlistId + " AND songId = " + songId);
    }

    public List<Integer> getSongsInPlaylist(int playlistId) {
        List<Integer> songIds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT songId FROM PlaylistSongs WHERE playlistId = " + playlistId, null);

        if (cursor.moveToFirst()) {
            do {
                songIds.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return songIds;
    }

    public void deleteAllSongsFromPlaylist(int playlistId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM PlaylistSongs WHERE playlistId = " + playlistId);
    }

    public void deleteAllSongsFromAllPlaylists() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM PlaylistSongs");
    }


    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO Category (name) VALUES ('" + category.getNameCategory() + "')");
    }

    public void updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE Category SET name = '" + category.getNameCategory() + "' WHERE id = " + category.getId());
    }

    public void deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Category WHERE id = " + id);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Category", null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setNameCategory(cursor.getString(1));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO Song (title, artist, album, path) VALUES ('" + song.getTitle() + "', '" + song.getArtist() + "', '" + song.getAlbum() + "' , '" + song.getPath() + "')");
        addSongToAllSongsPlaylist(song);
    }

    public void deleteSong(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        Song song = getSongByTitle(title); // You need to implement this method to get a Song by its id
        if (song != null) {
            db.execSQL("DELETE FROM Song WHERE title = " + title);
            removeSongFromAllSongsPlaylist(song);
        }
    }

    public Song getSongByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Song WHERE title = " + title, null);

        if (cursor.moveToFirst()) {
            Song song = new Song();
            song.setTitle(cursor.getString(1));
            song.setArtist(cursor.getString(2));
            song.setAlbum(cursor.getString(3));
            song.setPath(cursor.getString(4));
            return song;
        }
        cursor.close();
        return null;
    }





    public void addSongToAllSongsPlaylist(Song song) {
        // Get the "Allsong" playlist
        Playlists allSongsPlaylist = null;
        List<Playlists> playlists = getAllPlaylists();
        for (Playlists playlist : playlists) {
            if (playlist.getName().equals("Allsong")) {
                allSongsPlaylist = playlist;
                break;
            }
        }


        // If the "Allsong" playlist doesn't exist, create it
        if (allSongsPlaylist == null) {
            allSongsPlaylist = new Playlists("Allsong", new ArrayList<>());
            addPlaylist(allSongsPlaylist);
        }

        // Add the song to the "Allsong" playlist
        allSongsPlaylist.getSongs().add(song);
        updatePlaylist(allSongsPlaylist);
    }
    public void removeSongFromAllSongsPlaylist(Song song) {
        // Get the "Allsong" playlist
        Playlists allSongsPlaylist = null;
        List<Playlists> playlists = getAllPlaylists();
        for (Playlists playlist : playlists) {
            if (playlist.getName().equals("Allsong")) {
                allSongsPlaylist = playlist;
                break;
            }
        }

        // If the "Allsong" playlist exists, remove the song from it
        if (allSongsPlaylist != null) {
            allSongsPlaylist.getSongs().remove(song);
            updatePlaylist(allSongsPlaylist);
        }
    }


}