package com.example.jspservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "squareservlet", value = "/square-servlet")
public class SquareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        int value = (Integer) httpSession.getAttribute("value");
        //httpSession.removeAttribute("value");//will remove attribute.
        PrintWriter writer = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            if ("value".equals(c.getName())) {
                writer.println("value set from cookie");
                value = Integer.parseInt(c.getValue());
            }
        }

        writer.println("In square servlet.");
        writer.println("square value" + value * value);
    }
}
