<%@ page import="com.example.kdeokar.springmvc.model.Todo" %><%--
  Created by IntelliJ IDEA.
  User: kaustubh
  Date: 12/07/23
  Time: 11:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <title>Todos Page</title>
</head>
<body>

<h2>Update a todo</h2>
<form action="/update-todo?id=${id}" method="POST">
    Descr:<input path="desc" type="text" name="desc"/>
    <input type="submit">
</form>

</body>
</html>
