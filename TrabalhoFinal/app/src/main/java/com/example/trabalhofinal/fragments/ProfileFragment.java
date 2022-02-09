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
import com.example.trabalhofinal.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.recyclerviewProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerviewProfile
                .addItemDecoration(new DividerItemDecoration(binding.recyclerviewProfile.getContext(),
                        DividerItemDecoration.VERTICAL));

        loadProfileAdapter();
        return binding.getRoot();
    }

    private void loadProfileAdapter() {
        ProfileAdapter profileAdapter = new ProfileAdapter(getContext());

        binding.recyclerviewProfile.setAdapter(profileAdapter);
    }
}

