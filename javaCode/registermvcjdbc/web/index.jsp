<%--
  Created by IntelliJ IDEA.
  User: 王竞生
  Date: 2020/3/18
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
    <h1>Welcome!</h1>
    <form action="registerUIServlet" method="post">
      <input type="submit" value="注册">
    </form>
    <form action="tologinServlet">
      <input type="submit" value="登录">
    </form>
  </body>
</html>
