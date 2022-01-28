package com.example.trabalhofinal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.adapters.ChatListAdapter;
import com.example.trabalhofinal.models.User;


public class ChatsFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_chat_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView
                .addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

        loadMessages();

        return view;
    }

    private void loadMessages() {
        ChatListAdapter chatListAdapter = new ChatListAdapter(getContext());

        User user1 = new User();
        user1.setName("Lucas");

        User user2 = new User();
        user2.setName("Carlos");

        User user3 = new User();
        user3.setName("Alê");

        chatListAdapter.addItem(user1);
        chatListAdapter.addItem(user2);
        chatListAdapter.addItem(user3);

        recyclerView.setAdapter(chatListAdapter);
    }
}