package com.example.jspservlets;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!!!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        //individual servlet thing.
        ServletConfig sc = getServletConfig();
        out.println("<h2>"+sc.getInitParameter("key1")+"</h2>");

        //shared between all servlets.
        ServletContext servletContext = getServletContext();
        out.println("<h2>"+servletContext.getInitParameter("key1")+"</h2>");

    }

    public void destroy() {
    }
}