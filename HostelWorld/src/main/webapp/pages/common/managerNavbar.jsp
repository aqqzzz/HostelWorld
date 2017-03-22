<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/22
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="am-topbar am-topbar-fixed-top">
        <h1 class="am-topbar-brand">
            <a href="#">Hostel World</a>
        </h1>
        <div class="am-collapse am-topbar-collapse">
            <c:choose>
                <c:when test="${sessionScope.get('manage_id') == null}">
                    <div class="am-topbar-right">
                        <a type="button" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" id="login-btn" href="/auth/login">登陆</a>
                    </div>

                    <div class="am-topbar-right">
                        <a type="button" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" id="register-btn" href="/auth/register">注册</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="am-topbar-right">
                        <a type="button" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" id="logout-btn" href="/auth/logout">登出</a>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>

</header>
