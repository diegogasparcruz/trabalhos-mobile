package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAOUser;
import com.example.trabalhofinal.databinding.ActivityProfileFormBinding;
import com.example.trabalhofinal.databinding.ActivityRentalDetailsBinding;
import com.example.trabalhofinal.models.User;

public class ProfileFormActivity extends AppCompatActivity {
    ActivityProfileFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadUser();

        binding.btnCancelProfileForm.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    private void loadUser() {
        DAOUser daoUser = new DAOUser();
        daoUser.me(binding.edtNameProfileForm, binding.edtEmailProfileForm);
    }
}