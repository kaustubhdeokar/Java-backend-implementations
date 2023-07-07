<%--
Directive tag.
--%>
<%@ page import="java.util.Scanner" errorPage="error.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: kaustubh
  Date: 01/07/23
  Time: 12:13 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!--
    declaration tag. outside the service method. kind of like global thing.
-->
<%!
    int num = 10;
%>

<!--
 scriptlet tag.
 whatever mentioned here will be a part of service method in the converted file to jsp.
-->

<%
    Scanner scanner = new Scanner(System.in);
    System.out.println((num * 2)/0);

%>

<%= num %>

</body>
</html>
