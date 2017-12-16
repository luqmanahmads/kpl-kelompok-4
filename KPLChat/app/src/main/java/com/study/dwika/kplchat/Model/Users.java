package com.study.dwika.kplchat.model;

import java.util.Date;

/**
 * Created by A.I on 28/11/2017.
 */

public class Users {

    int id;

    String phone;
    String email;
    String password;
    String name;

    String remenber_token;
    String verfication_code;

    int is_active;
    Date created_at;
    Date updated_at;
    Date deleted_at;

    public Users(String phone, String email, String password, String name){
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemenber_token() {
        return remenber_token;
    }

    public void setRemenber_token(String remenber_token) {
        this.remenber_token = remenber_token;
    }

    public String getVerfication_code() {
        return verfication_code;
    }

    public void setVerfication_code(String verfication_code) {
        this.verfication_code = verfication_code;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
