<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/26
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Calendar date = Calendar.getInstance();
    Date today = date.getTime();
    date.add(Calendar.DATE, 1);
    Date tomorrow = date.getTime();
    date.add(Calendar.DATE, 1);
    Date after = date.getTime();
%>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="酒店详情"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <img src="/assets/img/hostel/hostel_7.jpg"/>
        </div>
        <div class="col-md-6 col-md-offset-2">
            <div class="page-header">
                <h1>${hostel.hostName}</h1>
                <h1><small>${hostel.location}</small></h1>
                <h1><small>Tel:${hostel.hostTel}</small></h1>
            </div>
            <div class="normal-div">
                入驻时间：${hostel.createTime}
            </div>
            <div class="normal-div">
                ${hostel.discription}
            </div>
        </div>
    </div>
    <div class="normal-div">
        <div class="col-md-2">
            <input class="form-control" name="startTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="<%=tomorrow%>"/>"
                 min="<fmt:formatDate pattern="yyyy-MM-dd" value="<%=today%>"/>"  type="date">
        </div>
        至
        <div class="col-md-2">
            <input class="form-control" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="<%=after%>"/>"
                   min = "<fmt:formatDate pattern="yyyy-MM-dd" value="<%=tomorrow%>"/>" type="date">
        </div>

        <button class="gtn btn-warning" onclick="searchPlan()">搜索</button>
    </div>
    <div class="normal-div" id="plan-container">


    </div>
</div>
</body>
<script>
    $(document).ready(function(){
        searchPlan();
    });
    function searchPlan(){
        var inTime = $("input[name='startTime']").val();
        var outTime = $("input[name='endTime']").val();
        var hostelId = ${hostel.id};

        $.ajax({
            type:'get',
            url:'/customer/planBetween',
            data:{
                inTime: inTime,
                outTime: outTime,
                hostelId: hostelId
            },
            dataType: 'html',
            success: function(result){
                $("#plan-container").html(result);
            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        })
    }
</script>
</html>
