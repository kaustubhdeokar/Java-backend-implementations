<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: kaustubh
  Date: 08/07/23
  Time: 12:02 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login.</title>
</head>
<body>

<%
    PrintWriter writer = response.getWriter();
    String error = "validation failed.";
    String name = "default";
    if (request.getAttribute("error") != null) {
        writer.println("Some null values.");
    } else {
        name = (String) request.getAttribute("name");
        writer.println("all good.");
    }

%>

<% if (request.getAttribute("error") != null) {
    writer.println("Error occured.");
} else {
    writer.println("<h1> Hello," + name + "</h1>");
}
%>

</body>
</html>
