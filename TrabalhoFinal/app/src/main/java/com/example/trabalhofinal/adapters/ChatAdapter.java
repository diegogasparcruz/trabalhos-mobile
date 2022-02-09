package com.example.trabalhofinal.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhofinal.databinding.LayoutItemChatReceivedRowBinding;
import com.example.trabalhofinal.databinding.LayoutItemChatSendRowBinding;
import com.example.trabalhofinal.models.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Chat> chatChats;
    private String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public ChatAdapter(String senderId) {
        this.chatChats = new ArrayList<>();
        this.senderId = senderId;
    }

    public void setMessages(List<Chat> chatMessages) {
        this.chatChats = chatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(
                    LayoutItemChatSendRowBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        } else {
            return new ReceivedMessageViewHolder(
                    LayoutItemChatReceivedRowBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).setData(chatChats.get(position));
        } else {
            ((ReceivedMessageViewHolder) holder).setData(chatChats.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chatChats.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatChats.get(position).getSenderId().equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final LayoutItemChatSendRowBinding binding;

        SentMessageViewHolder(LayoutItemChatSendRowBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(Chat chatChat) {
            binding.txtMessageSend.setText(chatChat.getMessage());
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private final LayoutItemChatReceivedRowBinding binding;

        ReceivedMessageViewHolder(LayoutItemChatReceivedRowBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(Chat chatChat) {
            binding.txtMessageReceived.setText(chatChat.getMessage());
        }
    }
}
