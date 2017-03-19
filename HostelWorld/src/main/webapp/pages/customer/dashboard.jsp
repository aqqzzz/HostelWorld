<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/18
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的首页"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9 col-md-9">
            <div class="panel panel-defualt">
                <div class="panel-heading">
                    <div class="panel-title">
                        我的首页
                    </div>
                </div>
                <div class="panel-body">
                    hhhhhhhh
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
