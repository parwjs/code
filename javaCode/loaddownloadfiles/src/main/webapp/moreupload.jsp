<%--
  Created by IntelliJ IDEA.
  User: jing
  Date: 2020/3/16
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多次上传文件</title>
</head>
<body>

<form action="morefiles" enctype="multipart/form-data" method="post">
    <table border="1px">
        <tr>
            <td>上传用户：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>添加上传文件：</td>
            <td><input type="button" value="添加上传文件" onclick="addUploadFile()"></td>
        </tr>
        <tr>
            <td>
                <div id="file"></div>
            </td>
        </tr>
    </table>
    <input type="submit" value="提交">
</form>



</body>

<script type="text/javascript">
    function addUploadFile() {
        //生成文件上传控件
        var input = document.createElement("input");
        input.type = 'file';
        input.name = 'fileName';

        //生成删除按钮
        var del = document.createElement("input");
        del.type = 'button';
        del.value = '删除';

        //生成内部的div
        var innerDiv = document.createElement("div");



        //将两个控件绑到div上
        innerDiv.appendChild(input);
        innerDiv.appendChild(del);

        //得到外部div控件，并将内部div绑定到外部div上
        var outerDiv = document.getElementById("file");
        outerDiv.appendChild(innerDiv);

        //为删除按钮绑定事件
        del.onclick = function dele() {
            //调用外界div的remove方法把内部的div干掉
            this.parentNode.parentNode.removeChild(this.parentNode);
        }

    }
</script>

<!-- js代码实现每次点击添加上传文件按钮就在div中生成上传文件的input和删除按钮 -->
</html>
