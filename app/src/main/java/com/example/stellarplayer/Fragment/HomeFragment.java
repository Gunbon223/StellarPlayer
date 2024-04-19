package com.example.stellarplayer.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.stellarplayer.Adapter.CategoryAdapter;
import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.Model.Category;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.R;
import com.example.stellarplayer.Service.DBSql;
import com.example.stellarplayer.Service.SongReader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    CategoryAdapter categoryAdapter;
    PlaylistAdapter playlistAdapter;
    DBSql dbSql;
    RecyclerView recyclerView;
    List<Category> categories;
    List<Playlists> playlists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.recycler_viewCategory);

        // Initialize the database helper
        dbSql = new DBSql(getActivity());

        // Use the member variable directly
        playlists = dbSql.getAllPlaylists();

        // Create 4 Category objects and add them to the categories list
        categories = new ArrayList<>();
        categories.add(new Category(1,"Recently Listened", playlists));
        categories.add(new Category(2,"Your Favourite", playlists));
        categories.add(new Category(3,"Popular ", playlists));
        categories.add(new Category(4,"Popular Podcast", playlists));

        // Initialize the CategoryAdapter with the categories list
        categoryAdapter = new CategoryAdapter(categories);
        // Set the adapter to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoryAdapter);
        ImageButton btnMenu = view.findViewById(R.id.imgBtnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), btnMenu);


                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.add_playlist) {
                            // Handle settings click
                            showAddPlaylistDialog();
                        } else if (itemId == R.id.add_song) {
                            // Create an intent to open the file browser
                            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            // Start the intent and expect a result
                            startActivityForResult(intent, 1);
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



        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData(); // Get the Uri of the selected directory
            // Use SongReader to read songs from the selected directory
            SongReader songReader = new SongReader();
            List<Song> songs = songReader.readSongsFromDirectory(uri, getActivity());
            // Add each song to the database
            dbSql.addSongs(songs);
        }
    }
    public void showPlaylistSelectionDialog(Song song, List<Playlists> playlists) {
        // Create an array of playlist names
        String[] playlistNames = new String[playlists.size()];
        for (int i = 0; i < playlists.size(); i++) {
            playlistNames[i] = playlists.get(i).getName();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a playlist");
        builder.setItems(playlistNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add the song to the selected playlist
                Playlists selectedPlaylist = playlists.get(which);
                selectedPlaylist.getSongs().add(song);
                // Update the playlist in the database
                dbSql.updatePlaylist(selectedPlaylist);
            }
        });
        builder.show();
    }

    public void showAddPlaylistDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new playlist");

        // Set up the input
        final EditText input = new EditText(getActivity());
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playlistName = input.getText().toString();
                // Create a new playlist with the entered name and an empty song list
                Playlists newPlaylist = new Playlists(playlistName, new ArrayList<>());
                // Add the new playlist to the database
                dbSql.addPlaylist(newPlaylist);
                // Add the new playlist to the existing list
                playlists.add(newPlaylist);
                playlistAdapter.notifyItemInserted(playlists.size() - 1);


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
