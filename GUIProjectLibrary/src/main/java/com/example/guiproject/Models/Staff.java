package com.example.guiproject.Models;

public abstract class Staff {

    protected int id;
    protected String username;
    protected String password;

    public Staff(int id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
