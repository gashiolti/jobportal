package com.example.contact;

public class Contact {

    private int id;
    private String fullName, email, messageSubject, message;

    public Contact(int id, String fullName, String email, String messageSubject, String message) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.messageSubject = messageSubject;
        this.message = message;
    }

    public Contact(String fullName, String email, String messageSubject, String message) {
        this.fullName = fullName;
        this.email = email;
        this.messageSubject = messageSubject;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public String getMessage() {
        return message;
    }
}
