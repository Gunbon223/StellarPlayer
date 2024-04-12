package com.example.stellarplayer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SongPlayerFragment extends Fragment {



    public static SongPlayerFragment newInstance() {
        return new SongPlayerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Replace 'fragment_now_playing' with your actual layout
        return inflater.inflate(R.layout.fragment_song_player, container, false);

    }
    }