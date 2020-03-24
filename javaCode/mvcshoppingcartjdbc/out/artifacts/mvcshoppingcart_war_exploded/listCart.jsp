<%--
  Created by IntelliJ IDEA.
  User: 王竞生
  Date: 2020/3/22
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>购物车显示页面</title>
</head>
<body>
    <h1>购物车显示页面</h1>

    <%--empty函数是判断集合中有没有元素--%>
    <%--如果购物车是没有任何购物项的--%>
    <c:if test="${empty(cart.bookMap)}">
        <h1>您还没购买过任何书籍！</h1>
    </c:if>

    <%--如果购物车有购物项，就应该把购物项的信息显示给用户--%>
    <c:if test="${!empty(cart.bookMap)}">
        <table border="1px">
            <tr>
                <td>书籍编号</td>
                <td>名称</td>
                <td>数量</td>
                <td>小计</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${cart.bookMap}" var="me">
                <tr>
                    <td>${me.key}</td>
                    <td>${me.value.book.name}</td>
                    <td><input type="text" value="${me.value.quantity}" onblur="update(this,${me.key},${me.value.quantity})"></td>
                    <td>${me.value.price}</td>
                    <td><a href="${pageContext.request.contextPath}/DeleteCartBook?bookid=${me.key}">删除</a> </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="2"><a href="${pageContext.request.contextPath}/ClearCart" onclick="clearCart()">清空购物车</a> </td>
                <td colspan="2">合计：</td>
                <td>${cart.price}</td>
            </tr>
        </table>
    </c:if>
</body>
<script type="text/javascript">
    /*  * @input 将输入框本身填入（这样可以获取得到输入框的值）
        * @id   将书本的id传递进来，告诉服务器是修改哪一个购物项（书）
        * @oldValue 原本的值，如果用户不想修改了，就修改为原本的值（下面会询问用户是否确定修改）
        * */
    function update(input,id,oldValue) {

        //获取输入框的数据
        var quantity = input.value;

        //询问用户是否真的修改
        var  b = window.confirm("你确定修改吗?");

        //如果确定，跳转到对应的Servlet
        if (b) {
            window.location.href = "${pageContext.request.contextPath}/UpdateQuantity?bookid=" + id + "&quantity=" + quantity + "";
        }else {
            input.value = oldValue;
        }
    }
    
    
    function clearCart() {
        var b = window.confirm("确定清空购物车?");

        if (b) {
            return true;
        }else {
            return false;
        }
    }

</script>
</html>
