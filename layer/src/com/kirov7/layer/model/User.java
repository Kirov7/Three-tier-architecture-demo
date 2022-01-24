package com.kirov7.layer.model;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:38
 */

public class User {

    private String username;

    private String password;

    private String salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
