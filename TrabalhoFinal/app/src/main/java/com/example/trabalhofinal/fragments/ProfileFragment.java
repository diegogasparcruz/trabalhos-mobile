package com.example.trabalhofinal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.adapters.ProfileAdapter;


public class ProfileFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView
                .addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

        loadProfileAdapter();
        return view;
    }

    private void loadProfileAdapter() {
        ProfileAdapter profileAdapter = new ProfileAdapter(getContext());

        recyclerView.setAdapter(profileAdapter);
    }
}

