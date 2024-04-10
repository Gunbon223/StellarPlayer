package com.example.stellarplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.stellarplayer.Fragment.HomeFragment;
import com.example.stellarplayer.Fragment.LibraryFragment;
import com.example.stellarplayer.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    LibraryFragment libraryFragment = new LibraryFragment();
    SearchFragment searchFragment = new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.menu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                } else if (id == R.id.library) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, libraryFragment).commit();
                } else if (id == R.id.search) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                }
                return true;
            }
        });
    }
}