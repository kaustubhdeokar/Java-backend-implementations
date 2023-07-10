package com.example.kdeokar.springmvc;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Please enter your name!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    public void destroy() {
    }
}