package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.models.Rent;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNewRentalActivity extends AppCompatActivity {
    private String[] typesProperty;
    private AutoCompleteTextView autoCompleteTypeProperty;
    private ArrayAdapter arrayAdapter;
    private Button btnNext;
    private TextInputEditText edtTitle, edtDescription, edtQtdBedrooms, edtQtdBathrooms, edtQtdResidents, edtPrice;
    private Integer typeProperty;
    private ImageView btnBack;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rental);

        setListeners();
        setItemsInputTypesProperty();

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        btnNext.setOnClickListener(v -> {
            handleSubmit();
        });
    }

    private void setListeners() {
        btnNext = findViewById(R.id.btn_next_add_new_rental);
        edtTitle = findViewById(R.id.edt_title_add_new_rental);
        edtDescription = findViewById(R.id.edt_description_add_new_rental);
        edtQtdBedrooms = findViewById(R.id.edt_qtd_bedrooms_add_new_rental);
        edtQtdBathrooms = findViewById(R.id.edt_qtd_bathrooms_add_new_rental);
        edtQtdResidents = findViewById(R.id.edt_qtd_residents_add_new_rental);
        edtPrice = findViewById(R.id.edt_price_add_new_rental);
        btnBack = findViewById(R.id.btn_back_add_new_rental);
    }

    private void setItemsInputTypesProperty() {
        autoCompleteTypeProperty = findViewById(R.id.edt_type_property_add_new_rental);

        typesProperty = getResources().getStringArray(R.array.types_property);
        arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item_add_new_rental, typesProperty);

        autoCompleteTypeProperty.setAdapter(arrayAdapter);
        autoCompleteTypeProperty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeProperty = (int)(adapterView.getItemIdAtPosition(i));
            }
        });
    }

    private void handleSubmit() {
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        String qtdBedrooms = edtQtdBedrooms.getText().toString();
        String qtdBathrooms = edtQtdBathrooms.getText().toString();
        String qtdResidents = edtQtdResidents.getText().toString();
        String price = edtPrice.getText().toString();

        if(title.trim().isEmpty() ||
                description.trim().isEmpty() ||
                qtdBedrooms.trim().isEmpty() ||
                qtdBathrooms.trim().isEmpty() ||
                qtdResidents.trim().isEmpty() ||
                price.trim().isEmpty() ||
                typeProperty == null) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            Rent rent = new Rent();
            rent.setTitle(title);
            rent.setDescription(description);
            rent.setNumberBedrooms(Integer.valueOf(qtdBedrooms));
            rent.setNumberBathrooms(Integer.valueOf(qtdBathrooms));
            rent.setNumberResidents(Integer.valueOf(qtdResidents));
            rent.setPrice(Double.valueOf(price));
            rent.setType(typeProperty);

            Intent intent = new Intent(this, AddNewRentalAddressActivity.class);
            intent.putExtra("rent", rent);
            startActivity(intent);
        }
    }
}