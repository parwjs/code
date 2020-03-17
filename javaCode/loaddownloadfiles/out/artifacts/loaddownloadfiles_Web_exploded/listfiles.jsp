<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/3/16
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件列表</title>
</head>
<body>

    <c:forEach items="${map}" var="me">
        <c:url var="url" value="/DownFileServlet">
            <c:param name="fileName" value="${me.key}"/>
        </c:url>
        ${me.value}<a href="${url}">下载！</a><br/>
    </c:forEach>

</body>
</html>
