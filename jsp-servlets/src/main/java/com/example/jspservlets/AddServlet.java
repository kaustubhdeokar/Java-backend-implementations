package com.example.jspservlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int value = add(req, resp.getWriter());
        HttpSession session = req.getSession();
        session.setAttribute("value", value);

        Cookie cookie = new Cookie("value", value+"");
        resp.addCookie(cookie);

        resp.sendRedirect("square-servlet");
    }
}