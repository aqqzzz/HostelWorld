<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/24
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的计划"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="row">
    <jsp:include page="detail/sidebar.jsp"/>
    <div class="col-md-10">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">我的计划</div>
            </div>
            <div class="panel-body">
                <div class="normal-div my-room">

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
