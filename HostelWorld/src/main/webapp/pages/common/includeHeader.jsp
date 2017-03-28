<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/18
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
    <title>${param.title}</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="<%=basePath%>assets/vendor/bootStrap/css/bootstrap.min.css"/>

    <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.flat.css"/>


    <link rel="stylesheet" href="<%=basePath%>assets/vendor/font-awesome/css/font-awesome.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/style.css?v=<%=new Date()%>"/>


    <script type="text/javascript" src="<%=basePath%>assets/vendor/jquery/jquery-3.1.1.min.js"></script>

    <script type="text/javascript" src="<%=basePath%>assets/js/amazeui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.widgets.helper.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.ie8polyfill.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/handlebars.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/vendor/echarts.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/auth.js"></script>
</head>
