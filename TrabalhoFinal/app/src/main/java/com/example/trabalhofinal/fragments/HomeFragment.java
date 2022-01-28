package com.example.trabalhofinal.fragments;

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
import com.example.trabalhofinal.models.Rent;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_rent_list);

        loadRentList();

        return view;
    }

    private void loadRentList(){
        HomeListAdapter homeListAdapter = new HomeListAdapter(getContext());

        DAORent daoRent = new DAORent();
        daoRent.listAll(homeListAdapter);

        recyclerView.setAdapter(homeListAdapter);
    }
}
