package com.example.trabalhofinal.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.R;
import com.example.trabalhofinal.activities.ChatActivity;
import com.example.trabalhofinal.models.User;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListVH> {
    private Context context;
    ArrayList<User> users;

    public ChatListAdapter(Context ctx) {
        this.context = ctx;
        this.users = new ArrayList<>();
    }

    public void addItem(User user) {
        this.users.add(user);
    }

    @NonNull
    @Override
    public ChatListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list_chat_row, parent, false);
        return new ChatListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListVH holder, int position) {
        User user = users.get(position);

        holder.txtNameUser.setText(user.getName());
        holder.txtLastMessage.setText("Última mensagem");

        holder.parentLayoutListChats.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, ChatActivity.class);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ChatListVH extends RecyclerView.ViewHolder {
        TextView txtNameUser, txtLastMessage;
        RelativeLayout parentLayoutListChats;

        public ChatListVH(@NonNull View itemView) {
            super(itemView);
            txtNameUser = itemView.findViewById(R.id.txt_name_user);
            txtLastMessage = itemView.findViewById(R.id.txt_last_message);
            parentLayoutListChats = itemView.findViewById(R.id.parent_layout_list_chats);
        }
    }
}
