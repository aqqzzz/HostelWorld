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
<jsp:include page="../common/navbar.jsp"/>
<h1>欢迎，${host_id}</h1>

</body>
</html>
