package com.alex.bibliotheque_web.model;

public class User{

    private int id;
    private String name;
    private String email;
    private String pswd;


    public User(int id, String name, String email, String pswd) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pswd = pswd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}