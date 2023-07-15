<%@ page import="com.example.kdeokar.springmvc.model.Todo" %><%--
  Created by IntelliJ IDEA.
  User: kaustubh
  Date: 12/07/23
  Time: 11:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Todos Page</title>
</head>
<body>

Hi, ${username} <br>

<H1>Your Todos</H1>

<div>
    <table>
        <caption>Your Todos are</caption>

        <thead>
        <tr>
            <th>Description</th>
            <th>target date</th>
            <th>status</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${todos}" var="todo">
            <tr>
                <td>${todo.desc}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.done}</td>
                <td><a href="/delete-todo?id=${todo.id}">Delete</a></td>
                <td><a href="/update-todo?id=${todo.id}">Update</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<a class="button" href="/add-todo">Add</a>

</body>
</html>
