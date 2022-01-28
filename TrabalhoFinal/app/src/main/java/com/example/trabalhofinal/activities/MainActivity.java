package com.example.trabalhofinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.fragments.ChatsFragment;
import com.example.trabalhofinal.fragments.HomeFragment;
import com.example.trabalhofinal.fragments.MapFragment;
import com.example.trabalhofinal.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private final HomeFragment homeFragment = new HomeFragment();
    private final MapFragment mapFragment = new MapFragment();
    private final ChatsFragment chatsFragment = new ChatsFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private FrameLayout homeContainer;
    private BottomNavigationView bottomNavigationView;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment currentFragment = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        homeContainer = findViewById(R.id.home_container);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        setTransactions();
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_item) {
            fragmentManager.beginTransaction()
                    .hide(currentFragment)
                    .show(homeFragment)
                    .commit();

            currentFragment = homeFragment;
        } else if (id == R.id.map_item) {
            fragmentManager.beginTransaction()
                    .hide(currentFragment)
                    .show(mapFragment)
                    .commit();

            currentFragment = mapFragment;
        } else if (id == R.id.chat_item) {
            fragmentManager.beginTransaction()
                    .hide(currentFragment)
                    .show(chatsFragment)
                    .commit();

            currentFragment = chatsFragment;
        } else if (id == R.id.profile_item) {
            fragmentManager.beginTransaction()
                    .hide(currentFragment)
                    .show(profileFragment)
                    .commit();

            currentFragment = profileFragment;
        }

        return true;
    }

    private void setTransactions(){
        fragmentManager.beginTransaction()
                .add(homeContainer.getId(), profileFragment, "Profile Fragment")
                .hide(profileFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(homeContainer.getId(), chatsFragment, "Chats Fragment")
                .hide(chatsFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(homeContainer.getId(), mapFragment, "Map Fragment")
                .hide(mapFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(homeContainer.getId(), homeFragment, "Home Fragment")
                .hide(homeFragment)
                .commit();
    }
}