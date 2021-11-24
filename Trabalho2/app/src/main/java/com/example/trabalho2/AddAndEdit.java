package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAndEdit extends AppCompatActivity {
    private String op;
    private Button btnCancel;
    private Button btnSave;
    private EditText inputName;
    private EditText inputStarOfTeam;
    private EditText inputTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        setListeners();
        Bundle bundle = getIntent().getExtras();

        op = bundle.getString("op");

        Toast.makeText(this, op, Toast.LENGTH_SHORT).show();
    }

    private void handleAdd() {
        String name = inputName.getText().toString();
        String starOfTeam = inputStarOfTeam.getText().toString();
        String titles = inputTitles.getText().toString();

        Intent intent = new Intent();

        intent.putExtra("name", name);
        intent.putExtra("starOfTeam", starOfTeam);
        intent.putExtra("titles", titles);

        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    private void setListeners() {
        btnCancel = findViewById(R.id.btn_cancel);
        btnSave = findViewById(R.id.btn_save);
        inputName = findViewById(R.id.input_name);
        inputStarOfTeam = findViewById(R.id.input_startOfTeam);
        inputTitles = findViewById(R.id.input_titles);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(op.equals("add")) {
                   handleAdd();
               }
            }
        });
    }
}