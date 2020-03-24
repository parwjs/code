<%--
  Created by IntelliJ IDEA.
  User: 王竞生
  Date: 2020/3/22
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍列表</title>
</head>
<body>
    <table border="1px">
        <tr>
            <td>书籍编号</td>
            <td>名称</td>
            <td>作者</td>
            <td>详细信息</td>
            <td>价格</td>
            <td>购买</td>
        </tr>

        <c:forEach items="${books}" var="me">
            <tr>
                <td>${me.key}</td>
                <td>${me.value.name}</td>
                <td>${me.value.author}</td>
                <td>${me.value.description}</td>
                <td>${me.value.price}</td>
                <%-- 把想买的书籍的id传给Servlet --%>
                <td><a href="BuyServlet?bookid=${me.key}">购买</a> </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
