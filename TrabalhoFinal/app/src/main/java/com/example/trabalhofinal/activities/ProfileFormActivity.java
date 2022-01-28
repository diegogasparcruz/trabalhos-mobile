package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.databinding.ActivityProfileFormBinding;
import com.example.trabalhofinal.databinding.ActivityRentalDetailsBinding;

public class ProfileFormActivity extends AppCompatActivity {
    ActivityProfileFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelProfileForm.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}