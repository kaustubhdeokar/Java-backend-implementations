<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    errorPage="error.jsp"
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Index jsp." %>
</h1>
<br/>

<form action="add.jsp" method="get">
    1st number<input type="text" name="fname"><br>
    2nd number<input type="text" name="lame"><br>
    <input type="submit">
</form>

<a href="home.jsp">Home</a>

<br>
<br>

<a href="hello-servlet">Hello Servlet</a>
</body>
</html>