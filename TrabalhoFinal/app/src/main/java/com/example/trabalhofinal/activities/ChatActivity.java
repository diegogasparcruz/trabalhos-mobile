package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalhofinal.adapters.ChatAdapter;
import com.example.trabalhofinal.dao.DAOChat;
import com.example.trabalhofinal.databinding.ActivityChatBinding;
import com.example.trabalhofinal.models.Chat;
import com.example.trabalhofinal.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;
    private User userReceived;
    private DAOChat daoChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userReceived = (User) getIntent().getSerializableExtra("user");
        daoChat = new DAOChat();
        setListeners();
    }

    private void setListeners() {
        loadChatMessages();
        binding.buttonGchatSend.setOnClickListener(v -> {
            sendMessage();
        });
    }

    private void loadChatMessages() {
        chatAdapter = new ChatAdapter(FirebaseAuth.getInstance().getCurrentUser().getUid());

        daoChat.listConversation(chatAdapter, userReceived.getId());

        binding.recyclerGchat.setAdapter(chatAdapter);
    }

    private void sendMessage() {
        String textMessage = binding.editGchatMessage.getText().toString();

        if(!textMessage.trim().isEmpty()) {
            daoChat.sendMessage(textMessage, userReceived.getId());
        }

        binding.editGchatMessage.setText(null);
    }
}