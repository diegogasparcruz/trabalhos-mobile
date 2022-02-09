package com.example.trabalhofinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Double latitude, longitude;
    private LocationRequest locationRequest;
    private Rent editRent;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rental);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(500);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        setStartLocationAction();
        editRent = (Rent) getIntent().getSerializableExtra("rent");
        setListeners();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkSettingsAndStartLocationUpdates();
            } else {
                Toast.makeText(this, "Permissão de localização recusada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setStartLocationAction() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (this),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1000
            );
        } else {
            checkSettingsAndStartLocationUpdates();
        }
    }

    private void getLastLocation() {
        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("location", location.toString());
                    Log.d("latitude", "" + location.getLatitude());
                    Log.d("longitude", "" + location.getLongitude());

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    Log.e("location error", "location was null...");
                }
            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("location error", e.getLocalizedMessage());
            }
        });
    }

    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();

        SettingsClient client = LocationServices.getSettingsClient((this));

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getLastLocation();
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(AddNewRentalActivity.this, 1001);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
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

        setItemsInputTypesProperty();

        if(editRent != null) {
            edtTitle.setText(editRent.getTitle());
            edtDescription.setText(editRent.getDescription());
            edtQtdBedrooms.setText(editRent.getNumberBedrooms()+"");
            edtQtdBathrooms.setText(editRent.getNumberBathrooms()+"");
            edtQtdResidents.setText(editRent.getNumberResidents()+"");
            edtPrice.setText(editRent.getPrice() + "");
            autoCompleteTypeProperty.setText(autoCompleteTypeProperty.getAdapter().getItem(editRent.getType()).toString(), false);
        }

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        btnNext.setOnClickListener(v -> {
            handleSubmit();
        });
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
            rent.setLatPoint(latitude);
            rent.setLngPoint(longitude);

            if(editRent != null) {
                rent.setId(editRent.getId());
                rent.setLatPoint(editRent.getLatPoint());
                rent.setLngPoint(editRent.getLngPoint());
            }

            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("rent", rent);
            startActivity(intent);
        }
    }
}