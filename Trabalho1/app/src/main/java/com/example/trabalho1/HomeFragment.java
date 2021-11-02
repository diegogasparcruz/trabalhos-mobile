package com.example.trabalho1;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeFragment extends Fragment {

    private ToggleButton toggleButton;
    private TextView textView;
    private EditText editText;
    private Button button;
    private AutoCompleteTextView autoCompleteTextView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toggleButton = view.findViewById(R.id.toggleButton);
        textView = view.findViewById(R.id.textView);
        editText = view.findViewById(R.id.edit_text);
        button = view.findViewById(R.id.clique_aqui);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView1);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText("LIGADO!");
                } else {
                    textView.setText("DESLIGADO!");
                }
            }
        });

        button.setOnClickListener(v -> {
            String text = editText.getText().toString();

            if(text.isEmpty()) {
                Toast.makeText(getContext(), "Você não digitou nada :(", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, WeekDays);

        autoCompleteTextView.setAdapter(arrayAdapter);

        return view;
    }

    private static final String[] WeekDays = new String[] {
            "Segunda-feira",
            "Terça-feira",
            "Quarta-feira",
            "Quinta-feira",
            "Sexta-feira",
            "Sábado",
            "Domingo",
    };
}