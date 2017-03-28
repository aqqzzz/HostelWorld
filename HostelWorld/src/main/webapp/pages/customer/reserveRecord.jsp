<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的订单"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>

<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">我的订单</div>
                </div>
                <div class="panel-body">
                    <div class="alert alert-info">
                        <strong>注意：</strong>请记好您的订单号，到店内出示订单号即可办理入住
                    </div>
                    <div id="hint"></div>
                    <div class="normal-div">
                        <div class="page-header">
                            <h1>已预订</h1>
                        </div>
                        <div class="normal-div" id="reserve-order-container">
                            <table class="table table-hover" id="reserve-order-table">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>预订时间</td>
                                    <td>预订酒店</td>
                                    <td>预订房型</td>
                                    <td>入住时间</td>
                                    <td>离店时间</td>
                                    <td>订单总额</td>
                                    <td>实际支付</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${reserveList.size()==0}">
                                        <div class="null">
                                            这里还没有预定信息哦，赶紧去预订吧~
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${reserveList}" var="item">
                                            <tr id="${item.id}">
                                                <td>${item.id}</td>
                                                <td>${item.createTime}</td>
                                                <td>${item.roomInfoById.hostelByHostelId.hostName}</td>
                                                <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.checkinTime}"/></td>
                                                <td>${item.leaveTime}</td>
                                                <td>${item.original}</td>
                                                <td>${item.actual}</td>
                                                <td><button class="btn btn-success" id="cancel-${item.id}" onclick="cancel(${item.id})">取消预订</button> </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="normal-div">
                        <div class="page-header"><h1>已取消</h1></div>
                        <div class="normal-div" id="cancel-order-container">
                            <table class="table table-hover" id="cancel-order-table">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>预订时间</td>
                                    <td>预订酒店</td>
                                    <td>预订房型</td>
                                    <td>入住时间</td>
                                    <td>离店时间</td>
                                    <td>订单总额</td>
                                    <td>取消时间</td>
                                    <td>处理进程</td>
                                    <td>退还金额</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${cancelList.size()!=0}">
                                        <c:forEach items="${cancelList}" var="item" varStatus="statu">
                                            <tr id="${item.id}">
                                                <td>${item.id}</td>
                                                <td>${item.createTime}</td>
                                                <td>${item.roomInfoById.hostelByHostelId.hostName}</td>
                                                <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                                <td>${item.checkinTime}</td>
                                                <td>${item.leaveTime}</td>
                                                <td>${item.original}</td>
                                                <td>${item.cancelTime}</td>

                                                <c:choose>
                                                    <c:when test="${settlementList[statu.index].status==0}">
                                                        <td><span class="label label-warning">待结算</span></td>
                                                    </c:when>
                                                    <c:when test="${settlementList[statu.index].status==1}">
                                                        <td><span class="label label-success">已结算</span></td>
                                                        <td>${settlementList[statu.index].amount*settlementList[statu.index].actualRate}元</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><c:out value="${settlementList[statu.index].status}"/></td>
                                                        <td><c:out value="${statu.index}"/></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>


                    <div class="normal-div">
                        <div class="page-header"><h1>已入住</h1></div>
                        <div class="normal-div" id="checkin-order-container">
                            <table class="table table-hover" id="checkin-order-table">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>预订时间</td>
                                    <td>预订酒店</td>
                                    <td>预订房型</td>
                                    <td>入住时间</td>
                                    <td>离店时间</td>
                                    <td>订单总额</td>
                                    <td>实际支付</td>
                                    <td>实际到店时间</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${checkInList.size()!=0}">
                                    <c:forEach items="${checkInList}" var="item">
                                        <tr id="${item.id}">
                                            <td>${item.id}</td>
                                            <td>${item.createTime}</td>
                                            <td>${item.roomInfoById.hostelByHostelId.hostName}</td>
                                            <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                            <td>${item.checkinTime}</td>
                                            <td>${item.leaveTime}</td>
                                            <td>${item.original}</td>
                                            <td>${item.actual}</td>
                                            <td>${item.actualCheckinTime}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>


                    <div class="normal-div">
                        <div class="page-header"><h1>已完成</h1></div>
                        <div class="normal-div" id="checkout-order-container">
                            <table class="table table-hover" id="checkout-order-table">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>预订时间</td>
                                    <td>预订酒店</td>
                                    <td>预订房型</td>
                                    <td>入住时间</td>
                                    <td>离店时间</td>
                                    <td>订单总额</td>
                                    <td>实际支付</td>
                                    <td>实际入住时间</td>
                                    <td>实际离店时间</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${checkOutList.size()!=0}">
                                    <c:forEach items="${checkOutList}" var="item">
                                        <tr id="${item.id}">
                                            <td>${item.id}</td>
                                            <td>${item.createTime}</td>
                                            <td>${item.roomInfoById.hostelByHostelId.hostName}</td>
                                            <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                            <td>${item.checkinTime}</td>
                                            <td>${item.leaveTime}</td>
                                            <td>${item.original}</td>
                                            <td>${item.actual}</td>
                                            <td>${item.actualCheckinTime}</td>
                                            <td>${item.actualLeaveTime}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function cancel(id){
        $.ajax({
            type:'post',
            url:'/customer/cancelReserve',
            data:{
                reserveId: id
            },
            dataType:'json',
            success:function(result){
                window.location.href="/customer/reserveRecord";
                var hint = $('#hint');
                hint.addClass("alert");
                if(result['success']){
                    hint.addClass("alert-success");
                    hint.html("成功取消预定！请耐心等待管理员回退钱款");
                }else{
                    hint.addClass("alert-danger");
                    hint.html(result['error']);
                }
            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        })
    }
</script>
</html>
