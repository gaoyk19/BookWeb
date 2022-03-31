<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
<%-- manager/bookServlet代表请求资源的地址，action=list表示调用服务器功能（BookServlet类）中的哪个方法   --%>
<%--    定义了点击“图书管理”按钮之后，就会跳转；href为超链接！--%>
    <a href="manager/bookServlet?action=list">图书管理</a>
    <a href="order_manager.jsp">订单管理</a>
    <a href="../../index.jsp">返回商城</a>
</div>