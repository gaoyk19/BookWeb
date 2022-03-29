<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: gaoyk
  Date: 2022/3/25
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border: 2px black solid;
            width: 600px;
            border-collapse: collapse;
        }
        td,th{
            border: 2px black solid;
        }
    </style>
</head>
<body>
<%
    List<User> studentList = (List<User>) request.getAttribute("userList");
%>
<table>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>电话</td>
        <td>操作</td>
    </tr>
    <% for (User student : studentList) { %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getUsername()%></td>
        <td><%=student.getPassword()%></td>
        <td><%=student.getEmail()%></td>
        <td>删除、修改</td>
    </tr>
    <% } %>
</table>
</body>
</html>
