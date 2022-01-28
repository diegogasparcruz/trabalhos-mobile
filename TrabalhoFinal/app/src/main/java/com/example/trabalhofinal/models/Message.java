package com.example.trabalhofinal.models;

public class Message {
    private String id;
    private String senderId;
    private String message;

    public Message() {
    }

    public Message(String id, String senderId, String message) {
        this.id = id;
        this.senderId = senderId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
