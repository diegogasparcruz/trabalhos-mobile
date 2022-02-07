package com.example.trabalhofinal.models;

public class Chat {
    private String id;
    private String senderId;
    private String receivedId;
    private String message;

    public Chat() {
    }

    public Chat(String id, String senderId, String receivedId, String message) {
        this.id = id;
        this.senderId = senderId;
        this.receivedId = receivedId;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(String receivedId) {
        this.receivedId = receivedId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
