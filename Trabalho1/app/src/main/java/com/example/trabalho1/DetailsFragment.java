package com.example.trabalho1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailsFragment extends Fragment {
    private Spinner spinner;
    private Button button;
    private ImageButton imageButton;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        spinner = view.findViewById(R.id.spinner);
        button = view.findViewById(R.id.clique_longo);
        imageButton = view.findViewById(R.id.imageButton);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, Pokemons);

        spinner.setAdapter(arrayAdapter);

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.champions);
                mediaPlayer.start();

                Toast.makeText(getContext(), "MÚSICA", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SecondActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private static final String[] Pokemons = new String[] {
            "Bulbasaur",
            "Charmander",
            "Squirtle",
    };
}