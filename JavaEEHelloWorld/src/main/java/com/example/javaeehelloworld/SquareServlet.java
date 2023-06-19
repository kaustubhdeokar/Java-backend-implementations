package com.example.javaeehelloworld;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "squareservlet", value = "/square-servlet")
public class SquareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int value = (int) req.getAttribute("value");
        PrintWriter writer = resp.getWriter();
        writer.println("In square servlet.");
        writer.println("square value" + value * value);
    }
}
