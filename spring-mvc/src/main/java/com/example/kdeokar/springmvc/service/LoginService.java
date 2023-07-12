package com.example.kdeokar.springmvc.service;

import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends HttpServlet {

    public LoginService() {
    }
    public boolean isValidUser(String username, String password) {
        return username.length() > 4 && password.length() > 4;
    }


}
