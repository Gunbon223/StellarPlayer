package com.example.stellarplayer.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stellarplayer.Adapter.MusicListAdapter;
import com.example.stellarplayer.Model.Song;
import com.example.stellarplayer.R;
import com.example.stellarplayer.Service.DBSql;

import java.io.File;
import java.util.ArrayList;

public class AllSongs extends Fragment {
    private RecyclerView recyclerView;
    private ImageView musicPic;
    private ImageButton btnImg, btnImg2, backBtn;
    private DBSql db;
    private TextView noMusicTextView;
    ArrayList<Song> songsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_play_list, container, false);

        musicPic = view.findViewById(R.id.PlaylistImg);
        musicPic.setImageResource(R.drawable.note_music);
        btnImg = view.findViewById(R.id.imageButton6);
        btnImg2 = view.findViewById(R.id.imageButton5);
        backBtn = view.findViewById(R.id.gobackBtt);
        db = new DBSql(getActivity());

        recyclerView = view.findViewById(R.id.musicList);
        noMusicTextView = view.findViewById(R.id.no_songs_text);

        if (checkPermission() == false) {
            requestPermission();
            return view;
        }

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        int count = 0;
        try {
            Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);
            while (cursor.moveToNext()) {
                try {
                    Song songData = new Song(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    if (new File(songData.getPath()).exists() && songData.getPath().endsWith(".mp3")) // chỉ xử lý các tệp có đuôi .mp3
                        songsList.add(songData);
                } catch (Exception e) {
                    // Log hoặc xử lý ngoại lệ tại đây nếu cần
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
        //recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MusicListAdapter(songsList, getActivity()));

        System.out.println(songsList.size());

        return view;
    }

    boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS", Toast.LENGTH_SHORT).show();
        } else
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerView != null) {
            recyclerView.setAdapter(new MusicListAdapter(songsList, getActivity()));
        }
    }
}