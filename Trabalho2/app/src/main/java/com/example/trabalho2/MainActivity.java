package com.example.trabalho2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton feb;
    private RecyclerView recyclerView;
    private TeamsAdapter teamsAdapter;
    private Button btnEdit;
    private EditText inputEditTeamId;
    private Integer idTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feb = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerview);
        btnEdit = findViewById(R.id.btn_edit);
        inputEditTeamId = findViewById(R.id.inputEditTeamId);

        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        teamsAdapter = new TeamsAdapter();
        recyclerView.setAdapter(teamsAdapter);

        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAndEdit.class);

                intent.putExtra("op", "add");

                startActivityForResult(intent, 1);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = inputEditTeamId.getText().toString();

                if(inputValue.trim().isEmpty()) {
                    Toast.makeText(v.getContext(), "Insira um ID!", Toast.LENGTH_SHORT).show();
                    return;
                }

                idTeam = Integer.valueOf(inputValue);

                Team team = teamsAdapter.getTeamById(idTeam);

                if (team == null) {
                    Toast.makeText(v.getContext(), "ID inválido!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, AddAndEdit.class);
                intent.putExtra("op", "edit");
                intent.putExtra("name", team.getName());
                intent.putExtra("starOfTeam", team.getStarOfTeam());
                intent.putExtra("titles", team.getNumberTitles());
                startActivityForResult(intent, 2);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String name = data.getStringExtra("name");
                String starOfTeam = data.getStringExtra("starOfTeam");
                String titles = data.getStringExtra("titles");
                Integer id = teamsAdapter.getItemCount();

                Team team = new Team(id, name, starOfTeam, titles);
                teamsAdapter.addTeam(team);
            }

        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String name = data.getStringExtra("name");
                String starOfTeam = data.getStringExtra("starOfTeam");
                String titles = data.getStringExtra("titles");

                teamsAdapter.editTeam(idTeam, name, starOfTeam, titles);
            }
            inputEditTeamId.setText("");
        }
    } //onActivityResult
}