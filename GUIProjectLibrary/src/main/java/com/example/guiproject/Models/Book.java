package com.example.guiproject.Models;

public class Book {
    private int id;
    private String name;
    private String category;
    private String publisher;
    private int status;

    public Book(int id, String name, String category, String publisher, int status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.publisher = publisher;
        this.status = status;
    }

    public Book(String name, String category, String publisher, int status) {
        this.name = name;
        this.category = category;
        this.publisher = publisher;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
