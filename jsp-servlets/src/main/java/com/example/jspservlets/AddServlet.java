package com.example.jspservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addservlet", value = "/add-servlet")
public class AddServlet extends HttpServlet {

    private static int add(HttpServletRequest request, PrintWriter out) {
        String num1 = request.getParameter("num1");
        int intNum1 = Integer.valueOf(num1);

        String num2 = request.getParameter("num2");
        int intNum2 = Integer.valueOf(num2);

        out.println("<html><body>");
        out.println("<h1>" + (intNum1 + intNum2) + "</h1>");
        out.println("</body></html>");

        return intNum2 + intNum1;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int value = add(req, resp.getWriter());

        req.setAttribute("value", value);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("square-servlet");
        requestDispatcher.forward(req, resp);

    }
}