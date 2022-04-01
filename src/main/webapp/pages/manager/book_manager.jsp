
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--下面该语句需要在pom文件中引入依赖jar包jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%-- 静态包含 manager管理模块的菜单  --%>
		<%@include file="/pages/common/manager_menu.jsp"%>

<%--		点击删除图书之后，需要弹出提示--%>
		<script type="text/javascript">
			$(function (){
				//给删除的a标签绑定单击事件，用于删除的确认提示操作
				$("a.deleteClass").click(function (){
					//确认提示框函数，有两个按钮：一个确认（true），一个取消（false)
					return confirm("确认删除 "+$(this).parent().parent().find("td:first").text()+" ?");

				});
			});

		</script>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
<%--			遍历前面BookServlet程序保存在request域中的books数据--%>
			<c:forEach items ="${requestScope.books}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="book_edit.jsp">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=deleteBook&id=${book.id}" >删除</a></td>
				</tr>
			</c:forEach>


			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a hrefpages/manager/book_edit.j="sp">添加图书</a></td>
			</tr>	
		</table>
	</div>


	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>