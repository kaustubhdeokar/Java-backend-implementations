<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!!!!!@!" %>
</h1>
<br/>

<form action="add-servlet" method="get">
    1st number<input type="number" name="num1"><br>
    2nd number<input type="number" name="num2"><br>
    <input type="submit">
</form>

<br>
<br>

<a href="hello-servlet">Hello Servlet</a>
</body>
</html>