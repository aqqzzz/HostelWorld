<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/12
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>登陆</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>

    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.flat.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/amaze/amazeui.flat.min.css"/>

    <link rel="stylesheet" href="<%=basePath%>assets/css/style.css"/>

    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.widgets.helper.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/amazeui.ie8polyfill.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/amaze/handlebars.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>assets/js/auth.js"></script>


</head>
<body>
<jsp:include page="common/navbar.jsp"/>
<div class="header">
    <div class="am-g">
        <h1>Hostel World</h1>
        <p>加入我们</p>
    </div>
    <hr/>
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">


    <div data-am-widget="tabs"
         class="am-tabs am-tabs-d2">
        <ul class="am-tabs-nav am-cf">
            <li class="am-active"><a href="#user-content">会员</a></li>
            <li class=""><a href="#hostel-content">客栈</a></li>
            <li class=""><a href="#hw-content">管理员</a></li>
        </ul>

        <div class="am-tabs-bd">
            <div class="am-tab-panel am-active" id="user-content">
                <div class="login-form">
                    <div class="error">

                    </div>
                    <form:form modelAttribute="customerLogin" action="${path}/auth/login" class="am-form am-form-horizontal" id="user-login-form">

                        <div class="am-form-group">
                            <label class="am-form-label am-u-md-3">手机号</label>
                            <div class="am-u-md-9">
                                <form:input class="form-control" id="phone" path="phone" type="text" placeholder="请输入手机号"/>
                                <form:errors path="phone" cssClass="error"/>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-form-label am-u-md-3">密码</label>
                            <div class="am-u-md-9">
                                <form:password class="form-control" path="password" placeholder="请输入密码"/>
                                <form:errors path="password" cssClass="error"/>
                            </div>
                        </div>
                        <div class="am-form-group">
                            <input type="submit" class="am-btn am-btn-primary am-btn-block" value="登陆"/>
                        </div>
                    </form:form>
                <hr>
            </div>

            </div>
            <div class="am-tab-panel" id="hostel-content">
                <div class="login-form">
                    <form class="form" id="hostel-login-form" >
                        <div class="form-group">
                            <input class="form-control" name="hostelId" type="text" placeholder="请输入您的客栈id"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" type="password" placeholder="请输入密码" />
                        </div>
                        <div class="form-group">
                            <input class="form-control btn btn-primary" type="submit" value="登陆"/>
                        </div>
                    </form>
                    <hr>
                </div>

            </div>

            <div class="am-tab-panel" id="hw-content">
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
</div>
</div>
</div>
</body>
</html>