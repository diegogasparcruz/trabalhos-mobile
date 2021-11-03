package com.example.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private Button button_trocar;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.list_view);
        gridView = findViewById(R.id.grid_view);

        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Heros);

        listView.setAdapter(adapterList);

        ArrayAdapter<String> adapterGrid = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Heros);

        gridView.setAdapter(adapterGrid);

        button_trocar = findViewById(R.id.button_trocar);

        button_trocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(SecondActivity.this, button_trocar);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if(id == R.id.item_list_popup) {
                            listView.setVisibility(View.VISIBLE);
                            gridView.setVisibility(View.INVISIBLE);
                        } else {
                            listView.setVisibility(View.INVISIBLE);
                            gridView.setVisibility(View.VISIBLE);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.item1) {
            onBackPressed();
        }
        return true;
    }

    private static final String[] Heros = new String[] {
            "All Might",
            "Endeavor",
            "Super Homem",
            "Batman",
            "Flash",
            "Hulk",
            "Aquaman",
            "Capitão Ámerica",
            "Homem de Ferro",
            "Homem aranha",
            "Gavião arqueiro",
            "Mulher maravilha",
            "Homem Formiga",
            "Vespa",
            "Thor",
            "Viúva negra",
    };
}