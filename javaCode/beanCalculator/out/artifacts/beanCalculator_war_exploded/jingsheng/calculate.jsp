<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/3/17
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>计算器</title>
</head>
<body>
    <!-- 获取得到JavaBean对象 -->
    <jsp:useBean id="calculator" class="bean.Calculator" scope="page" />

    <!-- 设置Bean对象的数据 -->
    <jsp:setProperty name="calculator" property="*" />

    <!-- 调用Calculator的方法计算出值 -->
    <%
        calculator.calculate();
    %>

    <%-- 得出结果 --%>
    <c:out value="计算得出的结果是："/>
    <jsp:getProperty name="calculator" property="firstNum"/>
    <jsp:getProperty name="calculator" property="operator"/>
    <jsp:getProperty name="calculator" property="secondNum"/>
    <c:out value="="/>
    <jsp:getProperty name="calculator" property="result"/>


</body>
</html>
