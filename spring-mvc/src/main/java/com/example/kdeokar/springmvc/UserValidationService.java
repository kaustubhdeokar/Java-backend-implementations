package com.example.kdeokar.springmvc;

import jakarta.servlet.http.HttpServlet;

public class UserValidationService extends HttpServlet {

    String username;
    String password;

    public UserValidationService(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean isValidUser() {
        return username.length() > 4 && password.length() > 4;
    }


}
