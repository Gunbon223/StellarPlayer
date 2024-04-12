package com.example.stellarplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.stellarplayer.Fragment.HomeFragment;
import com.example.stellarplayer.Fragment.LibraryFragment;
import com.example.stellarplayer.Fragment.SearchFragment;
import com.example.stellarplayer.Fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/*
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
}*/public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewPager2 and set its adapter
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set the ViewPager2's page change callback to update the selected item of the BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        // Set the BottomNavigationView's on item selected listener to update the current item of the ViewPager2
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.menu) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.library) {
                    viewPager.setCurrentItem(1);
                } else if (id == R.id.search) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}