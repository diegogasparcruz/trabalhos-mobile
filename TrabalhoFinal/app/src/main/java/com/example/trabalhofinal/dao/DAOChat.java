package com.example.trabalhofinal.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.trabalhofinal.adapters.ChatAdapter;
import com.example.trabalhofinal.adapters.ChatListAdapter;
import com.example.trabalhofinal.models.Chat;
import com.example.trabalhofinal.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAOChat {
    private String userId;
    private DatabaseReference databaseReference;

    public DAOChat() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        this.databaseReference = db.getReference(Chat.class.getSimpleName());
        this.userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void sendMessage(String textMessage, String receivedUserId) {
        String messageId = databaseReference.child(receivedUserId).push().getKey();

        Chat chat = new Chat(messageId, userId, receivedUserId, textMessage);

        databaseReference.child(messageId).setValue(chat);
    }

    public void listConversation(ChatAdapter chatAdapter, String userReceiver) {

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Chat> chats = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    Chat chat = new Chat();

                    String senderId = postSnapshot.child("senderId").getValue().toString();
                    String receivedId = postSnapshot.child("receivedId").getValue().toString();
                    String message = postSnapshot.child("message").getValue().toString();

                    chat.setSenderId(senderId);
                    chat.setReceivedId(receivedId);
                    chat.setMessage(message);

                    if(senderId.equals(userId) && receivedId.equals(userReceiver)) {
                        chats.add(chat);
                    }
                }

                chatAdapter.setMessages(chats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Erro", error.getMessage());
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }
}