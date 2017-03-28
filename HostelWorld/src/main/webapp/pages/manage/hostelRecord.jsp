<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/29
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="客栈统计"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">客栈统计</div>
                </div>
                <div class="panel-body">
                    <div class="normal-div">
                        <div class="page-header"><h1>入驻客栈</h1></div>
                        <div class="normal-div" id="hostel-total">
                            自HostelWorld建站以来，已有<span class="number">${hostelList.size()}</span>间客栈入驻
                        </div>
                        <div class="normal-div" id="hostel-item">
                            <c:choose>
                                <c:when test="${hostelList.size()==0}">这里还没有内容哦~</c:when>
                                <c:otherwise>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>客栈id</th>
                                            <th>客栈名称</th>
                                            <th>客栈入驻时间</th>
                                            <th>客栈地址</th>
                                            <th>客栈电话</th>
                                            <th>客栈描述</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${hostelList}" var="item">
                                            <tr id="${item.id}">
                                                <td>${item.id}</td>
                                                <td>${item.hostName}</td>
                                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/> </td>
                                                <td>${item.location}</td>
                                                <td>${item.hostTel}</td>
                                                <td>${item.discription}</td>
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


</div>
</body>
</html>
