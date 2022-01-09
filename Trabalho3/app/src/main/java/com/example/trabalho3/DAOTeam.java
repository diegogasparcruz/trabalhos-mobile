package com.example.trabalho3;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOTeam {
    private DatabaseReference databaseReference;

    public DAOTeam() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference(Team.class.getSimpleName());
    }

    public Task<Void> add(Team team) {
        return databaseReference.push().setValue(team);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key) {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key) {
        if(key == null) {
            return databaseReference.orderByKey().limitToFirst(8);
        }

        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get() {
        return databaseReference;
    }
}
