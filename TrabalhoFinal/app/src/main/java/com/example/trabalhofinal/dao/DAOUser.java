package com.example.trabalhofinal.dao;

import com.example.trabalhofinal.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
