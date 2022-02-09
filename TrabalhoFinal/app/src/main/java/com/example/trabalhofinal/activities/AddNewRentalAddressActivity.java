package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.databinding.ActivityAddNewRentalAddressBinding;
import com.example.trabalhofinal.models.Address;
import com.example.trabalhofinal.models.Rent;

public class AddNewRentalAddressActivity extends AppCompatActivity {
    private ActivityAddNewRentalAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewRentalAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPreviousAddNewRental.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnSaveAddNewRental.setOnClickListener(v -> {
            handleSubmit();
        });
    }

    private void handleSubmit() {
        String cep = binding.edtCepAddNewRental.getEditText().getText().toString();
        String street = binding.edtStreetAddNewRental.getEditText().getText().toString();
        String distric = binding.edtDistrictAddNewRental.getEditText().getText().toString();
        String city = binding.edtCityAddNewRental.getEditText().getText().toString();
        String uf = binding.edtUfAddNewRental.getEditText().getText().toString();
        String complement = binding.edtComplementAddNewRental.getEditText().getText().toString();


        Address address = new Address();
        address.setCep(cep);
        address.setStreet(street);
        address.setDistrict(distric);
        address.setCity(city);
        address.setUf(uf);
        address.setComplement(complement);

        Rent rent = (Rent) getIntent().getSerializableExtra("rent");

        rent.setAddress(address);

        DAORent daoRent = new DAORent();

        daoRent.add(rent);

        Intent intent = new Intent(this, MyRentalsActivity.class);
        startActivity(intent);
        finish();
    }
}