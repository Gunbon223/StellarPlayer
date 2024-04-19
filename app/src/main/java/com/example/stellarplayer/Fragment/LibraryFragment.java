package com.example.stellarplayer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.stellarplayer.ListActivity;
import com.example.stellarplayer.MainActivity;
import com.example.stellarplayer.PLayList;
import com.example.stellarplayer.R;

public class LibraryFragment extends Fragment {
    Button btnAlbums,btnPlaylists,btnAllSongs,btnFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        btnAlbums = view.findViewById(R.id.btnAlbums);
        btnPlaylists = view.findViewById(R.id.btnPlayLists);
        btnAllSongs = view.findViewById(R.id.btnAllSongs);
        btnFav = view.findViewById(R.id.btnFav);
        EdgeToEdge.enable(getActivity());
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btnAlbums.setOnClickListener(v1 -> {
                System.out.println("Hello, World!");
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.transition.slide_in_right, R.transition.slide_in_right);
            });
            btnPlaylists.setOnClickListener(v12 -> {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            });
            btnAllSongs.setOnClickListener(v13 -> {
                Intent intent = new Intent(getActivity(), PLayList.class);
                startActivity(intent);
            });
            btnFav.setOnClickListener(v14 -> {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            });
            return insets;
        });

        return view;
    }
}