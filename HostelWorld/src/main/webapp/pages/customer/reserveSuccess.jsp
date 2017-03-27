<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/27
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="预订成功"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container vertical-center-container">
        <h1>预订成功，您的订单号为：
            <c:forEach items="${reserveIdList}" var="item">
                ${item},
            </c:forEach>
        </h1>
        <a href="/hostel/getAllHostel">继续预订</a>

</div>

</body>
</html>
