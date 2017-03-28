<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="page-header">
        <h1>待结算列表</h1>
    </div>
    <div class="normal-div">
        <table class="table table-hover" id="settlemen-table">
            <thead>
            <tr>
                <th>订单号</th>
                <td>客户注册手机号</td>
                <td>客栈id</td>
                <td>酒店名称</td>
                <td>金额</td>
                <td>申请原因</td>
                <td>钱款去向</td>
                <td>申请时间</td>
                <td>结算比例</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${settlementList}" var="item">
                <tr id="${item.id}">
                    <td>${item.reserveByReserveId.id}</td>
                    <td>${item.reserveByReserveId.customerByCustId.phone}</td>
                    <td>${item.reserveByReserveId.roomInfoById.hostelByHostelId.id}</td>
                    <td>${item.reserveByReserveId.roomInfoById.hostelByHostelId.hostName}</td>
                    <td>${item.amount}</td>
                    <c:choose>
                        <c:when test="${item.reason==0}">
                            <td><span class="label label-warning">取消订单</span></td>
                            <td>回退给客户</td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="label label-success">完成订单</span></td>
                            <td>客栈</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.createTime}</td>
                    <td><input type="text" name="rate" placeholder="比率"/> </td>
                    <td><button class="btn btn-primary" onclick="settle(${item.id})">结算</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="page-header">
        <h1>已结算列表</h1>
    </div>
    <div class="normal-div">
        <table class="table table-hover" id="solved-table">
            <thead>
            <tr>
                <th>订单号</th>
                <td>客户注册手机号</td>
                <td>客栈id</td>
                <td>酒店名称</td>
                <td>金额</td>
                <td>申请原因</td>
                <td>钱款去向</td>
                <td>申请时间</td>
                <td>结算比例</td>
                <td>结算时间</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${solvedList}" var="item">
                <tr id="${item.id}">
                    <td>${item.reserveByReserveId.id}</td>
                    <td>${item.reserveByReserveId.customerByCustId.phone}</td>
                    <td>${item.reserveByReserveId.roomInfoById.hostelByHostelId.id}</td>
                    <td>${item.reserveByReserveId.roomInfoById.hostelByHostelId.hostName}</td>
                    <td>${item.amount}</td>
                    <c:choose>
                        <c:when test="${item.reason==0}">
                            <td><span class="label label-warning">取消订单</span></td>
                            <td>回退给客户</td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="label label-success">完成订单</span></td>
                            <td>客栈</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.createTime}</td>
                    <td>${item.actualRate*100}%</td>
                    <td>${item.settleTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>





<script>
    function settle(id){
        var rate = $("input[name='rate']").val();
        $.ajax({
            type:'post',
            url:'/manage/settle',
            data:{
                settlementId: id,
                rate:rate
            },
            dataType:'html',
            success:function(result){
                $("#table-container").html(result);
            },
            error:function(result){
                $("#table-container").html(result);
            }
        })
    }
</script>
