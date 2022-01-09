package com.example.trabalho3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalho3.R;

import java.util.HashMap;

public class Form extends AppCompatActivity {
    Button btn_cancel, btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final EditText input_team_name = findViewById(R.id.input_team_name);
        final EditText input_team_star = findViewById(R.id.input_team_star);
        final EditText input_team_titles = findViewById(R.id.input_team_titles);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        DAOTeam dao = new DAOTeam();
        Team team_edit = (Team) getIntent().getSerializableExtra("EDIT");

        if (team_edit != null) {
            input_team_name.setText(team_edit.getName());
            input_team_star.setText(team_edit.getStar());
            input_team_titles.setText(String.valueOf(team_edit.getTitles()));
        }

        btn_save.setOnClickListener(v -> {
            Team team = new Team(input_team_name.getText().toString(), input_team_star.getText().toString(), Integer.parseInt(input_team_titles.getText().toString()));

            if (team_edit == null) {
                dao.add(team).addOnSuccessListener(suc -> {
                    input_team_name.setText("");
                    input_team_star.setText("");
                    input_team_titles.setText("");

                    Toast.makeText(this, "Time adicionado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }).addOnFailureListener(err -> {
                    Toast.makeText(this, "" + err.getMessage(), Toast.LENGTH_SHORT).show();
                });
            } else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", input_team_name.getText().toString());
                hashMap.put("star", input_team_star.getText().toString());
                hashMap.put("titles", Integer.parseInt(input_team_titles.getText().toString()));

                dao.update(team_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Time atualizado!", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(err -> {
                    Toast.makeText(this, "" + err.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });

        btn_cancel.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        });
    }
}