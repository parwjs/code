<%--
  Created by IntelliJ IDEA.
  User: 王竞生
  Date: 2020/3/21
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录信息</title>
</head>
<body>
    <%
        String message = (String) request.getAttribute("message");
    %>
    <h1><%=message%></h1>

</body>
</html>
