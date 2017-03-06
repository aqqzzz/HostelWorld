<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/4
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="customer" class="edu.nju.hostelWorld.entity.Customer"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>

</head>

<body>

<%@include file="common/navbar.jsp"%>

<h1>欢迎来到Hostel World!</h1>
<h4>
    <c:choose>
        <c:when test="${cust_id}==null">
            <p>请登录</p>
        </c:when>
        <c:otherwise>
            <p>欢迎,${cust_name}</p>
        </c:otherwise>
    </c:choose>
</h4>
<img src="/assets/img/1.jpg"/>

</body>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/assets/js/navbar.js"></script>

</html>
