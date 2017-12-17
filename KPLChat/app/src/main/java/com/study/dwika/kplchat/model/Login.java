package com.study.dwika.kplchat.model;

/**
 * Created by Dwika on 15-Dec-17.
 */

public class Login {
    private String email;
    private String password;

    public Login(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
