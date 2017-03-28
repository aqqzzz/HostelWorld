<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="alert" id="error-msg"></div>
<table class="table table-hover">
    <thead>
    <tr>
        <td>订单编号</td>
        <td>预订时间</td>
        <td>预订房型</td>
        <td>入住时间</td>
        <td>离店时间</td>
        <td>到店时间</td>
        <td>支付类型</td>
        <td>实际支付</td>
        <td>操作</td>
    </tr>
    </thead>

    <tbody>
    <c:choose>
        <c:when test="${reserveList.size()==0}">
            <div class="normal-div">这里还没有内容哦</div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${reserveList}" var="item">
                <tr id="${item.id}">
                    <td>${item.id}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/> </td>
                    <td>${item.roomInfoById.roomLevelId.roomName}</td>
                    <td><fmt:formatDate value="${item.checkinTime}" pattern="yyyy-MM-dd"/> </td>
                    <td><fmt:formatDate value="${item.leaveTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.actualCheckinTime}" pattern="yyyy-MM-dd"/></td>
                    <td><c:choose>
                        <c:when test="${item.payType==0}">会员卡支付</c:when>
                        <c:otherwise>现金支付</c:otherwise>
                    </c:choose></td>
                    <td>${item.actual}</td>
                    <td><button class="btn btn-primary checkout" id="${item.id}">登记离店</button></td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    </tbody>
</table>

<script>
    $(".checkout").on("click", function(){
        var id = $(this).attr("id");
        $.ajax({
            type:'post',
            url:'/hostel/checkOut',
            data:{
                reserveId: id
            },
            dataType: 'html',
            success:function(result){
                if(result['success']){
                    getCheckInList();
                }else{
                    var error = $("#error-msg");
                    error.addClass("alert-danger");
                    error.html(result['error']);
                }
            }
        })
    })
</script>