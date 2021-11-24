package com.example.trabalho2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Adapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton feb;
    private RecyclerView recyclerView;
    private TeamsAdapter teamsAdapter;
    private ArrayList<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feb = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerview);

        feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAndEdit.class);

                intent.putExtra("op", "add");

                startActivityForResult(intent, 1);
            }
        });
        teams = new ArrayList<Team>();
        teamsAdapter = new TeamsAdapter(teams);
        recyclerView.setAdapter(teamsAdapter);
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
                teams.add(team);
                teamsAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    } //onActivityResult
}