<%--
  Created by IntelliJ IDEA.
  User: gaoyk
  Date: 2022/5/10
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--页面底部显示在第几页，以及页面的跳转--%>
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
</body>
</html>
