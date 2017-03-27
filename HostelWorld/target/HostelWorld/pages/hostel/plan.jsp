<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/26
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的计划"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">我的计划</div>
                </div>
                <div class="panel-body">
                    <div class="normal-div plan-info">
                        <div class="page-header">
                            <h1>我的计划</h1>
                        </div>
                        <button class="btn btn-success" id="add-plan-btn" onclick="addPlanCell()"><i class="fa fa-plus-circle">添加计划</i></button>
                        <div class="normal-div tips"></div>

                        <div class="normal-div" id="plan-info-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    $(document).ready(function(){
        getPlanInfo();
    });
    function getPlanInfo(){
        $.ajax({
            type:'get',
            url:'/hostel/getPlanInfo',
            dataType:'html',
            success:function(result){
                $('#plan-info-container').html(result);
            }
        })
    }


</script>
</html>
