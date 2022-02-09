package com.example.trabalhofinal.fragments;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.dao.DAORent;
import com.example.trabalhofinal.databinding.FragmentMapBinding;
import com.example.trabalhofinal.models.Rent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FragmentMapBinding binding;
    private ArrayList<Rent> rents;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rents = new ArrayList<>();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        return binding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d("location", location.toString());
                    Log.d("latitude", "" + location.getLatitude());
                    Log.d("longitude", "" + location.getLongitude());

                    LatLng myLoaction = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions options = new MarkerOptions();
                    options.position(myLoaction);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoaction, 20));
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

        DAORent daoRent = new DAORent();
        daoRent.setMapPoints(mMap, getContext());
    }
}