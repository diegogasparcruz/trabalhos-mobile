package com.example.trabalhofinal.dao;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.trabalhofinal.adapters.HomeListAdapter;
import com.example.trabalhofinal.adapters.MyRentalsAdapter;
import com.example.trabalhofinal.models.Address;
import com.example.trabalhofinal.models.Rent;
import com.example.trabalhofinal.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAORent {
    private DatabaseReference databaseReference;

    public DAORent() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference(Rent.class.getSimpleName());
    }

    public Task<Void> add(Rent rent) {
        rent.setId(databaseReference.child(Rent.class.getSimpleName()).push().getKey());
        rent.setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());

        return databaseReference.child(rent.getId()).setValue(rent);
    }

    public void listAll(HomeListAdapter homeListAdapter) {

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Rent> rents = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    if(!postSnapshot.child("userId").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        Rent rent = new Rent();

                        rent.setTitle(postSnapshot.child("title").getValue().toString());
                        rent.setDescription(postSnapshot.child("description").getValue().toString());
                        rent.setNumberBedrooms(Integer.valueOf(postSnapshot.child("numberBedrooms").getValue().toString()));
                        rent.setNumberBathrooms(Integer.valueOf(postSnapshot.child("numberBathrooms").getValue().toString()));
                        rent.setNumberResidents(Integer.valueOf(postSnapshot.child("numberResidents").getValue().toString()));
                        rent.setPrice(Double.valueOf(postSnapshot.child("price").getValue().toString()));
                        rent.setType(Integer.valueOf(postSnapshot.child("type").getValue().toString()));
                        rent.setUserId(postSnapshot.child("userId").getValue().toString());
                        rent.setId(postSnapshot.child("id").getValue().toString());

                        Address address = new Address();
                        address.setCep(postSnapshot.child("address").child("cep").getValue().toString());
                        address.setStreet(postSnapshot.child("address").child("street").getValue().toString());
                        address.setDistrict(postSnapshot.child("address").child("district").getValue().toString());
                        address.setCity(postSnapshot.child("address").child("city").getValue().toString());
                        address.setUf(postSnapshot.child("address").child("uf").getValue().toString());
                        address.setComplement(postSnapshot.child("address").child("complement").getValue().toString());

                        rent.setAddress(address);

                        rents.add(rent);
                    }
                }

                homeListAdapter.setRents(rents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Erro", error.getMessage());
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    public void getByUser(MyRentalsAdapter myRentalsAdapter) {

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Rent> rents = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    if(postSnapshot.child("userId").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        Rent rent = new Rent();

                        rent.setTitle(postSnapshot.child("title").getValue().toString());
                        rent.setDescription(postSnapshot.child("description").getValue().toString());
                        rent.setNumberBedrooms(Integer.valueOf(postSnapshot.child("numberBedrooms").getValue().toString()));
                        rent.setNumberBathrooms(Integer.valueOf(postSnapshot.child("numberBathrooms").getValue().toString()));
                        rent.setNumberResidents(Integer.valueOf(postSnapshot.child("numberResidents").getValue().toString()));
                        rent.setPrice(Double.valueOf(postSnapshot.child("price").getValue().toString()));
                        rent.setType(Integer.valueOf(postSnapshot.child("type").getValue().toString()));
                        rent.setUserId(postSnapshot.child("userId").getValue().toString());
                        rent.setId(postSnapshot.child("id").getValue().toString());

                        Address address = new Address();
                        address.setCep(postSnapshot.child("address").child("cep").getValue().toString());
                        address.setStreet(postSnapshot.child("address").child("street").getValue().toString());
                        address.setDistrict(postSnapshot.child("address").child("district").getValue().toString());
                        address.setCity(postSnapshot.child("address").child("city").getValue().toString());
                        address.setUf(postSnapshot.child("address").child("uf").getValue().toString());
                        address.setComplement(postSnapshot.child("address").child("complement").getValue().toString());

                        rent.setAddress(address);

                        rents.add(rent);
                    }
                }

                myRentalsAdapter.setRents(rents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Erro", error.getMessage());
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }
}
