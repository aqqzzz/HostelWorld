<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/10
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>填写信息</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/style.css"/>
    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <form:form class="form-horizontal" modelAttribute="customerInfo" action="/customer/">
        <fieldset>
            <legend>完善个人资料</legend>
            <div class="form-group">
                <label class="col-md-4 control-label" for="userid">您的会员id</label>
                <div class="col-md-4">
                    <form:input path="userid" name="userid" type="text" value="${cust_id}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="phone">您的手机号码</label>
                <div class="col-md-4">
                    <form:input path="phone" name="phone" type="text" value="${cust_phone}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="name">姓名</label>
                <div class="col-md-4">
                    <form:input path="name" name="name" type="text" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="birthday">生日</label>
                <div class="col-md-4">
                    <form:input path="birthday" type="text"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="gender">性别</label>
                <div class="col-md-4">
                    <form:radiobutton path="gender" value="0"/>男
                    <form:radiobutton path="gender" value="1"/>女
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="bankAccountByBankCard">绑定银行卡号</label>
                <div class="col-md-4">
                    <form:input path="bankAccountByBankCard"/>
                </div>
            </div>

            <div class="form-group">
                <input type="submit" name="submit" value="提交"/>
            </div>

        </fieldset>

    </form:form>

</div>

</body>
</html>
