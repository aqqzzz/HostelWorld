<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="离店登记"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">离店登记</div>
                </div>
                <div class="panel-body">
                    <div class="page-header"><h1>会员离店登记</h1></div>

                    <div class="normal-div" id="check-in-container">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    $(document).ready(function(){
        getCheckInList();
    })

    function getCheckInList(){
        $.ajax({
            type:'get',
            url:'/hostel/getCheckInList',
            dataType:'html',
            success:function (result) {
                $("#check-in-container").html(result);

            }
        })
    }
</script>
</html>
