package com.example.kdeokar.springmvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginservlet", value = "/loginservlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {

        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Name:" + name + " password:" + password);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserValidationService service = new UserValidationService(req.getParameter("username"),
                req.getParameter("password"));
        if (service.isValidUser()) {
            req.setAttribute("name", req.getParameter("username"));
        } else {
            req.setAttribute("error", "Validation failed.");
        }
        req.getRequestDispatcher("/WEB-INF/views.jsp").forward(req, resp);
    }
}
