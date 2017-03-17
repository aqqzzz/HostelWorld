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
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-active" id="user-content">
                    <div class="register-form">
                        <form:form modelAttribute="customerNew" action="${path}/auth/register" class="am-form am-form-horizontal" id="user-register-form">
                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label vertical-center" for="phone">手机号：</label>
                                <div class="am-u-md-9">
                                    <form:input path="phone" class="am-form-field" type="tel" placeholder="请输入您的手机号"/>
                                    <form:errors path="phone" cssClass="error"/>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label" for="password">密码</label>
                                <div class="am-u-md-9">
                                    <form:password class="am-form-field" path="password" id="password" placeholder="请输入您的密码"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label" for="passwordAgain">再次输入密码</label>
                                <div class="am-u-md-9">
                                    <input class="am-form-field" id="passwordAgain" type="password" placeholder="请再次输入您的密码"/>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label" for="name">姓名</label>
                                <div class="am-u-md-9">
                                    <form:input class="am-form-field" path="name" id="name" type="text" placeholder="请输入您的姓名"/>
                                    <form:errors path="name" cssClass="error"/>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label">性别</label>
                                <div class="am-u-md-9">
                                    <div class="am-radio-inline">
                                        <form:radiobutton path="gender" value="0" />男
                                    </div>
                                    <div class="am-radio-inline">
                                        <form:radiobutton path="gender" value="1"/>女
                                    </div>
                                    <form:errors path="gender" cssClass="error"/>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label">银行卡号</label>
                                <div class="am-u-md-9">
                                    <form:input path="bankAccountByBankCard.id" placeholder="绑定银行卡号"/>
                                    <form:errors path="bankAccountByBankCard.id" cssClass="error"/>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <input type="submit" class="am-btn am-btn-primary am-btn-block" value="注册"/>
                            </div>

                        </form:form>
                        <hr>
                    </div>


                </div>
                <div class="am-tab-panel" id="hostel-content">

                    <div class="register-form">
                        <form class="form" id="hostel-register-form" >

                        </form>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
