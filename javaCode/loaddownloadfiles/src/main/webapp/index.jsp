<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/3/16
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
  <!-- action提交地址应与tomcat的url-pattern一致-->
    <form action="loadFile" enctype="multipart/form-data" method="post">
      上传用户：<input type="text" name="username"><br/>
      上传密码：<input type="password" name="password"><br/>
      上传文件1：<input type="file" name="file1"><br/>
      上传文件2：<input type="file" name="file2"><br/>
      <input type="submit" value="提交">
    </form>
  </body>
</html>
