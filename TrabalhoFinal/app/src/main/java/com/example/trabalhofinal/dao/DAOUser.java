package com.example.trabalhofinal.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trabalhofinal.adapters.ChatListAdapter;
import com.example.trabalhofinal.adapters.HomeListAdapter;
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

public class DAOUser {
    private DatabaseReference databaseReference;

    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user) {
        return databaseReference.child(user.getId()).setValue(user);
    }

    public Task<Void> saveBookMark(String rentId) {
        return databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bookMarksIds").child(rentId).setValue(rentId);
    }

    public void listAll(ChatListAdapter chatListAdapter) {

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<User> users = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                   if(!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(postSnapshot.child("id").getValue().toString())) {
                       User user = new User();

                       user.setId(postSnapshot.child("id").getValue().toString());
                       user.setName(postSnapshot.child("name").getValue().toString());

                       users.add(user);
                   }
                }

                chatListAdapter.addAll(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Erro", error.getMessage());
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }
}
