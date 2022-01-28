package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.adapters.HomeListAdapter;
import com.example.trabalhofinal.adapters.MyRentalsAdapter;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.models.Rent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyRentalsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView btnBackMyRentals;
    private FloatingActionButton btnAddMyRentals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rentals);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        btnBackMyRentals = findViewById(R.id.btn_back_my_rentals);
        btnAddMyRentals = findViewById(R.id.btn_add_my_rentals);
        recyclerView = findViewById(R.id.recyclerview_my_rentals_list);

        recyclerView.setLayoutManager(manager);

        loadMyRentals();

        btnBackMyRentals.setOnClickListener(view -> {
            onBackPressed();
        });

        btnAddMyRentals.setOnClickListener(view -> {
            Intent intent = new Intent(MyRentalsActivity.this, AddNewRentalActivity.class);
            startActivity(intent);
        });
    }

    private void loadMyRentals() {
        MyRentalsAdapter myRentalsAdapter = new MyRentalsAdapter(this);

        DAORent daoRent = new DAORent();
        daoRent.getByUser(myRentalsAdapter);

        recyclerView.setAdapter(myRentalsAdapter);
    }
}