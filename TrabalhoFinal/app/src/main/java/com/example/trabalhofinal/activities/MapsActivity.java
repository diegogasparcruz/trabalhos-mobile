package com.example.trabalhofinal.activities;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.databinding.ActivityMapsBinding;
import com.example.trabalhofinal.models.Rent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Rent rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        rent = (Rent) getIntent().getSerializableExtra("rent");

        binding.btnFinish.setOnClickListener(v -> {
            DAORent daoRent = new DAORent();

            daoRent.add(rent);

            Intent intent = new Intent(this, MyRentalsActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng coodinates = new LatLng(rent.getLatPoint(), rent.getLngPoint());

        MarkerOptions options = new MarkerOptions();
        options.position(coodinates);

        mMap.addMarker(options);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coodinates, 20));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));

                rent.setLatPoint(point.latitude);
                rent.setLngPoint(point.longitude);
            }
        });
    }

}
