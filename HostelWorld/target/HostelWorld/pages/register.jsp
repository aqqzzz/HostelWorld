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
<jsp:include page="common/includeHeader.jsp">
    <jsp:param name="title" value="注册"/>
</jsp:include>
<body>
<jsp:include page="common/navbar.jsp"/>
<style>
    .form-group{
        overflow: auto;
    }
</style>
<div class="header">
    <div class="am-g">
        <h1>Hostel World</h1>
        <a href="/auth/login">我要登陆</a>
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
                        <form:form modelAttribute="customerNew" action="${path}/auth/customer/register" class="am-form am-form-horizontal" id="user-register-form">
                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label vertical-center" for="phone">手机号：</label>
                                <div class="am-u-md-9">
                                    <form:input path="phone" class="am-form-field" type="tel" placeholder="请输入您的手机号"/>
                                    <form:errors path="phone" cssClass="form-error"/>
                                </div>
                            </div>
                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label" for="password">密码</label>
                                <div class="am-u-md-9">
                                    <form:password class="am-form-field" path="password" id="password" placeholder="请输入您的密码"/>
                                    <form:errors path="password" cssClass="form-error"/>
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
                                    <form:errors path="name" cssClass="form-error"/>
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
                                    <form:errors path="gender" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label class="am-u-md-3 am-form-label">银行卡号</label>
                                <div class="am-u-md-9">
                                    <form:input path="bankAccountByBankCard.id" placeholder="绑定银行卡号"/>
                                    <form:errors path="bankAccountByBankCard.id" cssClass="form-error"/>
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
                        <form:form modelAttribute="hostelNew" action="${path}/auth/hostel/register" class="form form-horizental" id="hostel-register-form" >
                            <h2>客栈信息</h2>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">客栈名称</div>
                                <div class="col-md-9">
                                    <form:input path="hostName" class="form-control" placeholder="客栈名称"/>
                                    <form:errors path="hostName" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">登录密码</div>
                                <div class="col-md-9">
                                    <form:password path="hostPassword" class="form-control" id="hostPassword" placeholder="请输入您的注册密码"/>
                                    <form:errors path="hostPassword" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">再次输入密码</div>
                                <div class="col-md-9">
                                    <input type="password" name="passwordAgain" class="form-control" id="hostPasswordAgain" placeholder="请再次输入您的密码"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">绑定银行卡号</div>
                                <div class="col-md-9">
                                    <form:input path="hostBankAccountByBankCard.id" class="form-control" placeholder="请输入您的工作银行卡号"/>
                                    <form:errors path="hostBankAccountByBankCard.id" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">客栈电话</div>
                                <div class="col-md-9">
                                    <form:input path="hostTel" class="form-control" placeholder="请输入客栈的联系电话"/>
                                    <form:errors path="hostTel" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">客栈地址</div>
                                <div class="col-md-9">
                                    <form:input path="location" class="form-control" placeholder="请输入客栈地址"/>
                                    <form:errors path="location" cssClass="form-error"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-3 control-label right-aligned">描述</div>
                                <div class="col-md-9">
                                    <form:textarea path="discription" class="form-control" placeholder="请简要介绍一下您的客栈（200字以内）"/>
                                    <form:errors path="discription" cssClass="form-error"/>
                                </div>
                            </div>

                            <hr>
                            <input type="submit" class="btn btn-primary btn-block" value="注册"/>
                        </form:form>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
