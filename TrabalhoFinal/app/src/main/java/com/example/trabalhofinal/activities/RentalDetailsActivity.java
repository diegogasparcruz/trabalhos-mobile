package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAOUser;
import com.example.trabalhofinal.databinding.ActivityRentalDetailsBinding;
import com.example.trabalhofinal.models.Rent;

public class RentalDetailsActivity extends AppCompatActivity {
    ActivityRentalDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRentalDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackRentalDetails.setOnClickListener(view -> {
            onBackPressed();
        });

        Rent rent = (Rent) getIntent().getSerializableExtra("rent");

        binding.txtTitleRentalDetails.setText(rent.getTitle());
        binding.txtDescriptionRentalDetails.setText(rent.getDescription());
        binding.txtNumberBedroomsRentalDetails.setText(rent.getNumberBedrooms() + "");
        binding.txtNumberBathroomsRentalDetails.setText(rent.getNumberBathrooms() + "");
        binding.txtNumberResidentsRentalDetails.setText(rent.getNumberResidents() + "");
        binding.txtMoneyRentalDetails.setText(rent.getPrice() + "");

        binding.bookMark.setOnClickListener(v -> {
            DAOUser daoUser = new DAOUser();
            binding.bookMark.setVisibility(View.GONE);
            binding.bookMarkSelected.setVisibility(View.VISIBLE);
            daoUser.saveBookMark(rent.getId());
        });

        binding.bookMarkSelected.setOnClickListener(v -> {
            binding.bookMarkSelected.setVisibility(View.GONE);
            binding.bookMark.setVisibility(View.VISIBLE);
        });

    }
}