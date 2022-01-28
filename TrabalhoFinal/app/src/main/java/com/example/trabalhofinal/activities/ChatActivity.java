package com.example.trabalhofinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.adapters.ChatAdapter;
import com.example.trabalhofinal.databinding.ActivityChatBinding;
import com.example.trabalhofinal.models.Message;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());

        loadChatMessages();

        setContentView(binding.getRoot());
    }

    private void loadChatMessages() {
        Message msg1 = new Message();
        msg1.setSenderId("1");
        msg1.setMessage("Jooj");

        Message msg2 = new Message();
        msg2.setSenderId("1");
        msg2.setMessage("Jooj <<<<< bha");

        Message msg3 = new Message();
        msg3.setSenderId("2");
        msg3.setMessage("DBZ !== Naruto");

        ArrayList<Message> messages = new ArrayList<>();

        messages.add(msg1);
        messages.add(msg2);
        messages.add(msg3);

        chatAdapter = new ChatAdapter(messages, "1");

        binding.recyclerGchat.setAdapter(chatAdapter);
    }
}