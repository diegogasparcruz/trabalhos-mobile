package com.example.trabalhofinal.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.activities.MainActivity;
import com.example.trabalhofinal.activities.RegisterActivity;
import com.example.trabalhofinal.adapters.HomeListAdapter;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.databinding.FragmentHomeBinding;
import com.example.trabalhofinal.models.Rent;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        loadRentList();

        setListeners();

        return binding.getRoot();
    }

    private void setListeners() {
        binding.btnCategoryAll.setOnClickListener(v -> {
            binding.btnCategoryAll.setBackgroundColor(Color.parseColor("#303030"));
            binding.btnCategoryAll.setTextColor(Color.parseColor("#FFFFFF"));

            binding.btnCategoryHouse.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryHouse.setTextColor(Color.parseColor("#FFBB86FC"));

            binding.btnCategoryApartment.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryApartment.setTextColor(Color.parseColor("#FFBB86FC"));
            loadRentList();
        });

        binding.btnCategoryHouse.setOnClickListener(v -> {
            binding.btnCategoryHouse.setBackgroundColor(Color.parseColor("#303030"));
            binding.btnCategoryHouse.setTextColor(Color.parseColor("#FFFFFF"));

            binding.btnCategoryAll.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryAll.setTextColor(Color.parseColor("#FFBB86FC"));

            binding.btnCategoryApartment.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryApartment.setTextColor(Color.parseColor("#FFBB86FC"));
            loadHouses();
        });

        binding.btnCategoryApartment.setOnClickListener(v -> {
            binding.btnCategoryApartment.setBackgroundColor(Color.parseColor("#303030"));
            binding.btnCategoryApartment.setTextColor(Color.parseColor("#FFFFFF"));

            binding.btnCategoryAll.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryAll.setTextColor(Color.parseColor("#FFBB86FC"));

            binding.btnCategoryHouse.setBackgroundColor(Color.parseColor("#FFFFFF"));
            binding.btnCategoryHouse.setTextColor(Color.parseColor("#FFBB86FC"));
            loadApartment();
        });
    }

    private void loadRentList() {
        HomeListAdapter homeListAdapter = new HomeListAdapter(getContext());
        DAORent daoRent = new DAORent();
        daoRent.listAll(homeListAdapter);
        binding.recyclerviewRentList.setAdapter(homeListAdapter);
    }

    private void loadHouses() {
        HomeListAdapter homeListAdapter = new HomeListAdapter(getContext());
        DAORent daoRent = new DAORent();
        daoRent.filterByType(homeListAdapter, 0);
        binding.recyclerviewRentList.setAdapter(homeListAdapter);
    }

    private void loadApartment() {
        HomeListAdapter homeListAdapter = new HomeListAdapter(getContext());
        DAORent daoRent = new DAORent();
        daoRent.filterByType(homeListAdapter, 1);
        binding.recyclerviewRentList.setAdapter(homeListAdapter);
    }

}
