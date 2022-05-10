<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<a href="pages/user/login.jsp">登录</a> |
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>

	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="" method="get">
					价格：<input id="min" type="text" name="min" value=""> 元 - 
						<input id="max" type="text" name="max" value=""> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button>加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<div id="page_nav">
			<a href="${requestScope.page.url}&pageNo=1">首页</a>
			<c:if test="${requestScope.page.pageNo>1}">
				<%--如果当前已经是第一页，那么不再显示“上一页--%>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>

			<c:choose>
				<%--当页面总数小于等于5--%>
				<c:when test="${requestScope.page.pageTotal <=5 }" >
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<c:if test="${i==requestScope.page.pageNo}">
							【${i}】
						</c:if>
						<c:if test="${i!=requestScope.page.pageNo}">
							<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>

					</c:forEach>
				</c:when>

				<%--当页面总数大于5--%>
				<c:when test="${requestScope.page.pageTotal >5}">
					<c:choose>
						<%--当前页码为前3个--%>
						<c:when test="${requestScope.page.pageNo <=3}">
							<c:forEach begin="1" end="5" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--情况2：当前页码为最后3个，8,9,10，页码范围是：总页码减4 ~ 总页码--%>
						<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
							<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:when>

						<%--情况3：页码在4~7范围内, 页码范围是：当前页码-2 ~ 当前页码+2 --%>
						<c:otherwise>
							<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${i}】
								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>


			<%--如果当前已经是最后一页，那么不再显示“下一页--%>
			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
			</c:if>
			<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			共${requestScope.page.pageTotal}页，${requestScope.pageTotalCount}条记录
			<%--			实现跳转，首先给“按钮”一个ID ,并给它绑定一个单一事件--%>
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageNo" type="button" value="确定">

			<script type="text/javascript">
				$(function (){
					$("#searchPageNo").click(function(){
						//	知道要跳转到哪个页面
						var pageNo=$("#pn_input").val();
						var pageTotal=${requestScope.page.pageTotal};
						alert(pageNo>0 && pageNo<=pageTotal);

						//	javaScript语言中提供了一个location地址栏对象，它有一个属性href,它可以获取浏览器地址栏中的地址
						//	location.href可读、可写、可赋值
						//	从 head.jsp中设置pageContext
						location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;

					});
				});
			</script>
		</div>
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>