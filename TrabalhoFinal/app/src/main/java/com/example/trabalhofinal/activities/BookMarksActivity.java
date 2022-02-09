package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.adapters.HomeListAdapter;
import com.example.trabalhofinal.dao.DAOBookmark;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.databinding.ActivityBookMarksBinding;

public class BookMarksActivity extends AppCompatActivity {
    private ActivityBookMarksBinding binding;
    private DAOBookmark daoBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookMarksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daoBookmark = new DAOBookmark();
        loadRentList();

        binding.btnBackBookmark.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void loadRentList() {
        HomeListAdapter homeListAdapter = new HomeListAdapter(this);

        daoBookmark.listBookmarks(homeListAdapter);
        binding.recyclerviewRentBookmark.setAdapter(homeListAdapter);
    }
}