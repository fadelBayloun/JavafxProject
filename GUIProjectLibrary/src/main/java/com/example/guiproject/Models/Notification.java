package com.example.guiproject.Models;

public class Notification {
    private int id;
    private String title;
    private String body;

    public Notification(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Notification(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
