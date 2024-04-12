package com.example.stellarplayer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Songs;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;
    private List<Playlists> playlists;
    private DBSql db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        db = new DBSql(this);
        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.listItems);
        // Create a list of Playlists objects
        playlists = db.getAllPlaylists();
        // Initialize the PlaylistAdapter
        adapter = new PlaylistAdapter(playlists);


        // Set the adapter to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Open playlist
        adapter.setOnItemClickListener(new PlaylistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click
            }
        });


        //Delete
        adapter.setOnItemLongClickListener(new PlaylistAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                // Create a new AlertDialog
                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Delete Playlist")
                        .setMessage("Are you sure you want to delete this playlist?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Delete the item from the data list and notify the adapter
                                playlists.remove(position);
                                adapter.notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ListActivity.this, btnMenu);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.add_playlist) {
                            // Handle settings click
                            showAddPlaylistDialog();
                        } else if (itemId == R.id.delete_playlist) {
                            // Handle about click
                            return true;
                        } else {
                            return false;
                        }
                        return false;
                    }

                });

                popup.show();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void showAddPlaylistDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new playlist");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playlistName = input.getText().toString();
                // Create a new playlist with the entered name and an empty song list
                Playlists newPlaylist = new Playlists(playlistName);
                // Add the new playlist to the database
                db.addPlaylist(newPlaylist);
                // Add the new playlist to the existing list
                playlists.add(newPlaylist);
                // Notify the adapter about the new item inserted
                adapter.notifyItemInserted(playlists.size() - 1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}