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
    <jsp:param name="title" value="登陆"/>
</jsp:include>

<style>
    .form-group{
        overflow: auto;
    }
</style>
<body>
<jsp:include page="common/navbar.jsp"/>
<div class="header">
    <div class="am-g">
        <h1>Hostel World</h1>
        <a href="/auth/register">加入我们</a>
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
                    <form:form modelAttribute="customerLogin" action="${path}/auth/customer/login" class="am-form am-form-horizontal" id="user-login-form">

                        <div class="am-form-group">
                            <label class="am-form-label am-u-md-3">手机号</label>
                            <div class="am-u-md-9">
                                <form:input class="form-control" id="phone" path="phone" type="text" placeholder="请输入手机号"/>
                                <form:errors path="phone" cssClass="alert alert-danger form-error"/>
                            </div>
                        </div>

                        <div class="am-form-group">
                            <label class="am-form-label am-u-md-3">密码</label>
                            <div class="am-u-md-9">
                                <form:password class="form-control" path="password" placeholder="请输入密码"/>
                                <form:errors path="password" cssClass="alert alert-danger form-error"/>
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
                    <form:form modelAttribute="hostelLogin" action="${path}/auth/hostel/login" class="form" id="hostel-login-form" >
                        
                        <form:hidden path="hostName"/>
                        <form:errors path="hostName" cssClass="alert alert-danger form-error text-center" /> <%--展示是否通过审批的页面--%>

                        <div class="form-group">
                            <div class="col-md-3 right-aligned">
                                <label class="control-label">客栈id</label>
                            </div>
                            <div class="col-md-9">
                                <form:input class="form-control" path="id" placeholder="请输入您的客栈id" value=""/>
                                <form:errors path="id" cssClass="alert alert-danger form-error"/>
                            </div>


                        </div>
                        <div class="form-group">
                            <div class="col-md-3 right-aligned">
                                <label class="control-label">密码</label>
                            </div>
                            <div class="col-md-9">
                                <form:password class="form-control" path="hostPassword" placeholder="请输入密码" />
                                <form:errors path="hostPassword" cssClass="alert alert-danger form-error"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <input class="form-control btn btn-primary" type="submit" value="登陆"/>
                        </div>
                    </form:form>
                    <hr>
                </div>

            </div>

            <div class="am-tab-panel" id="hw-content">
                <form class="form" id="hw-login-form">
                    <div class="form-group">
                        <div class="col-md-3 right-aligned">
                            <label class="control-label">用户名</label>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" name="hwname" type="text" placeholder="请输入用户名"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-md-3 right-aligned">
                            <label class="control-label">密码</label>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" name="password" type="password" placeholder="请输入密码"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <input class="form-control btn btn-primary" type="submit" onclick="managerLogin(event)" value="登陆" />
                    </div>
                </form>
            </div>
        </div>
</div>
</div>
</div>
</body>
<script>

    function managerLogin(e){
        e.preventDefault();

        $.ajax({
            type:'post',
            url:'/auth/manage/login',
            data:$('#hw-login-form').serialize(),
            success: function(result){
                if(result['success']){
                    window.location.href="/manage";
                }else{
                    alert(JSON.stringify(result));
                }
            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        });
    }
</script>
</html>
