package com.example.stellarplayer.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stellarplayer.Adapter.CategoryAdapter;
import com.example.stellarplayer.Adapter.PlaylistAdapter;
import com.example.stellarplayer.DBSql;
import com.example.stellarplayer.Model.Category;
import com.example.stellarplayer.Model.Playlists;
import com.example.stellarplayer.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    CategoryAdapter categoryAdapter;
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
        List<Playlists> playlists = dbSql.getAllPlaylists();

        // Create 4 Category objects and add them to the categories list
        categories = new ArrayList<>();
        categories.add(new Category(1,"Category 1", playlists));
        categories.add(new Category(2,"Category 2", playlists));
        categories.add(new Category(3,"Category 3", playlists));
        categories.add(new Category(4,"Category 4", playlists));

        // Initialize the CategoryAdapter with the categories list
        categoryAdapter = new CategoryAdapter(categories);

        // Set the adapter to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoryAdapter);

        return view;
    }
}