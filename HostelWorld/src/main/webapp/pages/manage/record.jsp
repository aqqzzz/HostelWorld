<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/29
  Time: 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="平台统计"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">平台统计</div>
                </div>
                <div class="panel-body">
                    <div class="page-header"><h1>财务统计</h1></div>
                    <div class="normal-div" id="finantial-total">
                        自建站以来，平台共盈利<span class="number">${manager.balance}</span>
                    </div>
                    <div class="normal-div" id="finantial-item">
                        <c:choose>
                            <c:when test="${payList.size()==0}">
                                这里还没有内容哦~
                            </c:when>
                            <c:otherwise>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>类型</th>
                                        <th>金额</th>
                                        <th>时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="index" value="0"/>
                                    <c:forEach items="${payList}" var="pay">
                                        <c:set var="index" value="${index+1}"/>
                                        <tr id="${pay.id}">
                                            <td>${index}</td>
                                            <td><c:choose>
                                                <c:when test="${pay.type==0}"><span class="label label-success">收入</span></c:when>
                                                <c:otherwise><span class="label label-warning">支出</span></c:otherwise>
                                            </c:choose></td>
                                            <td>${pay.money}</td>
                                            <td><fmt:formatDate value="${pay.time}" pattern="yyyy-MM-dd"/> </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
