<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/26
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Calendar date = Calendar.getInstance();
    Date today = date.getTime();
    date.add(Calendar.DATE, 1);
    Date tomorrow = date.getTime();
    date.add(Calendar.DATE, 1);
    Date after = date.getTime();
%>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="酒店列表"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <c:forEach items="${hostelList}" var="item">
            <div class="col-md-6">
                <div class="thumbnail" style="height: 600px;">
                    <a href="/hostel/getHostel/${item.id}">
                        <img src="/assets/img/hostel/hostel_6.jpg">
                        <h3>${item.hostName}</h3>
                    </a>
                    <div class="caption">
                        <p>${item.discription}</p>
                        <p><button class="btn btn-info" onclick="getHostel(${item.id})">查看详情</button> </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>

<script>
</script>
</html>
