package com.example.stellarplayer.Service;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.stellarplayer.Model.Playlists;

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



}