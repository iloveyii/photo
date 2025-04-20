package com.learn.photo.model;

public class UserMessage {

    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String says() {
        return String.format("%s says: %s", getName(), getMessage());
    }
}
