package com.example.tarea4;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String pass;
    public User(String email,String pass){
        this.email=email;
        this.pass=pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}

