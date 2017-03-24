<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/20
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="欢迎"/>
</jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="../common/managerNavbar.jsp"/>
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
    </div>
</div>

</body>
</html>
