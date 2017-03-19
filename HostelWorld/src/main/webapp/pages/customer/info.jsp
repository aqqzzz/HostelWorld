<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/19
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>

<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的信息"/>
</jsp:include>

<style>
    @media (min-width:1200px) {
        .col-lg-10{
            margin-bottom: 15px;
        }
    }
    .control-label{
        text-align: right;
    }
</style>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-8">
            <div class="panel">
                <div class="panel-heading">
                    <div class="panel-title">
                        个人信息
                    </div>
                </div>
                <div class="panel-body">
                    <c:if test="${update!=null}">
                        <div class="update-message">
                            <c:choose>
                                <c:when test="${update=='success'}">
                                    <div class="alert alert-success">更新成功！</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-danger">更新失败！</div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                    </c:if>
                    <form:form modelAttribute="customerInfo" action="${pageContext.request.contextPath}/customer/info" class="form-horizental" role="form" id="info-form">

                        <div class="form-group">
                            <label class="col-lg-2 control-label">会员卡号</label>
                            <div class="col-lg-10">
                                <form:input path="userid" class="form-control" value="${customerInfo.userid}" readonly="true"/>
                                <form:errors path="userid" cssClass="form-error"/>
                            </div>
                        </div>

                        <form:hidden path="password" value="${customerInfo.password}"/>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">注册手机号</label>
                            <div class="col-lg-10">
                                <form:input path="phone" class="form-control" value="${customerInfo.phone}"/>
                                <form:errors path="phone" cssClass="form-error"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-lg-2 control-label">姓名</label>
                            <div class="col-lg-10">
                                <form:input path="name" class="form-control" value="${customerInfo.name}"/>
                                <form:errors path="phone" cssClass="form-error"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">性别</label>
                            <div class="col-lg-10">
                                <form:radiobutton path="gender" value="0"/>男
                                <form:radiobutton path="gender" value="1"/>女
                            </div>
                            <form:errors path="gender" cssClass="form-error"/>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">银行卡号</label>
                            <div class="col-lg-10">
                                <form:input path="bankAccountByBankCard.id" class="form-control" value="${customerInfo.bankAccountByBankCard.id}"/>
                                <form:errors path="bankAccountByBankCard.id" cssClass="form-error"/>
                                <c:if test="${customerInfo.bankAccountByBankCard.id==null}">
                                    <div class="alert alert-warning">
                                        <strong>警告！</strong>您没有绑定银行卡，不能进行网上预订操作！
                                    </div>
                                </c:if>

                            </div>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-default" value="修改"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>
