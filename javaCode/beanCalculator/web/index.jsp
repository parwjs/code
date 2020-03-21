<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/3/17
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>简单计算器</title>
  </head>
  <body>
    <form action="jingsheng/calculate.jsp">
      <table border="1">
        <tr>
          <td colspan="2">简单计算器</td>
          <td></td>
        </tr>
        <tr>
          <td>第一个参数：</td>
          <td><input type="text" name="firstNum"></td>
        </tr>
        <tr>
          <td>运算符</td>
          <td>
            <select name="operator">
              <option value="+">+</option>
              <option value="-">-</option>
              <option value="*">*</option>
              <option value="/">/</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>第二个参数：</td>
          <td><input type="text" name="secondNum"></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="提交"></td>
          <td></td>
        </tr>
      </table>
    </form>
  </body>
</html>
