<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/4
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="customer" class="edu.nju.hostelWorld.entity.Customer"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/style.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.flat.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.flat.min.css"/>

    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.js"/>

    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.min.js"/>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.widgets.helper.min.js"/>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.ie8polyfill.min.js"/>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/handlebars.min.js"/>
    <script type="text/javascript" src="<%=basePath%>assets/js/auth.js"></script>

</head>

<body>

<%--<%@include file="common/navbar.jsp"%>--%>

<%--<h1>欢迎来到Hostel World!</h1>--%>
<%--<h4>--%>
    <%--<c:choose>--%>
        <%--<c:when test="${cust_id==null}">--%>
            <%--<p>请登录</p>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<p>欢迎,${cust_name}</p>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>

<%--</h4>--%>

<div class="container">
    <%--<div class="card card-container">--%>
        <ul class="nav nav-tabs" role="navlist">
            <li role="presentation" class="active"><a href="#user-content" aria-controls="user-content" role="tab" data-toggle="tab">会员</a></li>
            <li role="presentation"><a href="#hostel-content" role="tab" data-toggle="tab">客栈</a> </li>
            <li role="presentation"><a href="#hw-content" role="tab" data-toggle="tab">管理员</a> </li>
            <%--<li id="login-tab"><a href="#login-content" role="tab" data-toggle="tab">登陆</a></li>--%>
            <%--<li id="register-tab"><a href="#register-content" role="tab" data-toggle="tab">注册</a></li>--%>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="user-content">
                <div class="login-form">
                    <form class="form" id="user-login-form">
                        <div class="form-group">
                            <input class="form-control" name="phone" type="text" placeholder="请输入手机号"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" type="password" placeholder="请输入密码"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" id="user-login-btn" class="form-control btn btn-primary" onclick="userLogin()" value="登陆"/>
                        </div>
                    </form>
                    <hr>
                    <p class="vertical-center">
                        还没有账号？
                        <a type="button" class="btn-align-right" onclick="callRegister()">我要注册</a>
                    </p>
                </div>


                <div class="register-form">
                    <form class="form" id="user-register-form">

                        <div class="form-group">
                            <input name="phone" class="form-control" type="tel" placeholder="请输入您的手机号"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" type="password" placeholder="请输入您的密码"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="passwordAgain" type="password" placeholder="请再次输入您的密码"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="form-control btn btn-primary" id="user-register-btn" value="注册" onclick="userRegister()"/>
                        </div>

                    </form>
                    <hr>
                    <p class="vertical-center">
                        已有账号？
                        <a type="button" class="btn-align-right" onclick="callLogin()">我要登陆</a>
                    </p>

                </div>


            </div>
            <div class="tab-pane" id="hostel-content">
                <div class="login-form">
                    <form class="form" id="hostel-login-form" >
                        <div class="form-group">
                            <input class="form-control" name="hostelId" type="text" placeholder="请输入您的客栈id"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" type="password" placeholder="请输入密码" />
                        </div>
                        <div class="form-group">
                            <input class="form-control btn btn-primary" type="submit" id="hostel-login" value="登陆"/>
                        </div>
                    </form>
                    <hr>
                    <p class="vertical-center">
                        还没有账号？
                        <a type="button" class="btn-align-right" onclick="callRegister()">我要注册</a>
                    </p>
                </div>

                <div class="register-form">
                    <form class="form" id="hostel-register-form" >

                    </form>
                    <hr>
                    <p class="vertical-center">
                        已有账号？
                        <a type="button" class="btn-align-right" onclick="callLogin()">我要登陆</a>
                    </p>
                </div>


            </div>

            <div class="tab-pane" id="hw-content">
                <form class="form" id="hw-login-form">
                    <div class="form-group">
                        <input class="form-control" name="hwname" type="text" placeholder="请输入用户名"/>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="password" type="password" placeholder="请输入密码"/>
                    </div>
                    <div class="form-group">
                        <input class="form-control btn btn-primary" type="submit" value="登陆" />
                    </div>
                </form>
            </div>
        </div>
    <%--</div>--%>
</div>

</body>


</html>
