package com.example.trabalhofinal.dao;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.trabalhofinal.adapters.HomeListAdapter;
import com.example.trabalhofinal.models.Rent;
import com.example.trabalhofinal.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAOBookmark {
    private DatabaseReference databaseReference;

    public DAOBookmark() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference();
    }

    public void listBookmarks(HomeListAdapter homeListAdapter) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            return;
        }

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Rent> rents = new ArrayList<>();
                ArrayList<String> bookmarksIds = new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.child(User.class.getSimpleName()).getChildren()) {
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(postSnapshot.child("id").getValue().toString())) {
                        for(DataSnapshot bookMarks : postSnapshot.child("bookMarksIds").getChildren()) {
                            String bookMarkId = bookMarks.getValue().toString();

                            bookmarksIds.add(bookMarkId);
                        }
                    }
                }

                for(String id : bookmarksIds) {
                    Rent rent = new Rent();

                    rent.setTitle(snapshot.child(Rent.class.getSimpleName()).child(id).child("title").getValue().toString());
                    rent.setDescription(snapshot.child(Rent.class.getSimpleName()).child(id).child("description").getValue().toString());
                    rent.setNumberBedrooms(Integer.valueOf(snapshot.child(Rent.class.getSimpleName()).child(id).child("numberBedrooms").getValue().toString()));
                    rent.setNumberBathrooms(Integer.valueOf(snapshot.child(Rent.class.getSimpleName()).child(id).child("numberBathrooms").getValue().toString()));
                    rent.setNumberResidents(Integer.valueOf(snapshot.child(Rent.class.getSimpleName()).child(id).child("numberResidents").getValue().toString()));
                    rent.setPrice(Double.valueOf(snapshot.child(Rent.class.getSimpleName()).child(id).child("price").getValue().toString()));
                    rent.setType(Integer.valueOf(snapshot.child(Rent.class.getSimpleName()).child(id).child("type").getValue().toString()));
                    rent.setUserId(snapshot.child(Rent.class.getSimpleName()).child(id).child("userId").getValue().toString());
                    rent.setId(snapshot.child(Rent.class.getSimpleName()).child(id).child("id").getValue().toString());

                    rents.add(rent);
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
}
