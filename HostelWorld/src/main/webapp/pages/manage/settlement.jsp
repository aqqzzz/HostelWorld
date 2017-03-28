<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="结算清单"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">结算清单</div>
                </div>
                <div class="panel-body">
                    <div id="table-container"></div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
<script>
$(document).ready(function(){
    $.ajax({
        type:'get',
        url: '/manage/getSettleList',
        dataType:'html',
        success:function(result){
            $("#table-container").html(result);
        }
    })
})
</script>
</html>
