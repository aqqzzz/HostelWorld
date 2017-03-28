<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/20
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="欢迎"/>
</jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="../common/managerNavbar.jsp"/>
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9">


            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">
                        客栈统计
                    </div>
                </div>
                <div class="panel-body">
                    <div class="normal-div">
                        <div class="normal-div" id="pay">
                            <div class="page-header">
                                <h1>财务状况</h1>
                            </div>
                            <div class="normal-div" id="payInfo">
                                <div class="normal-div">
                                    自加入HostelWorld以来，您总共在hostelWorld赚了<span class="number">${balance}</span>元
                                </div>
                                <div class="normal-div" id="payItem">
                                    <c:choose>
                                        <c:when test="${payList.size()==0}">
                                            这里还没有内容哦~
                                        </c:when>
                                        <c:otherwise>
                                            <table class="table table-hover">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>类型</th>
                                                    <th>金额</th>
                                                    <th>时间</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:set var="index" value="0"/>
                                                <c:forEach items="${payList}" var="pay">
                                                    <c:set var="index" value="${index+1}"/>
                                                    <tr id="${pay.id}">
                                                        <td>${index}</td>
                                                        <td><c:choose>
                                                            <c:when test="${pay.type==0}"><span class="label label-success">收入</span></c:when>
                                                            <c:otherwise><span class="label label-warning">支出</span></c:otherwise>
                                                        </c:choose></td>
                                                        <td>${pay.money}</td>
                                                        <td><fmt:formatDate value="${pay.time}" pattern="yyyy-MM-dd"/> </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                        </div>


                        <div class="normal-div" id="reserveInfo">
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
                                            <td>预订客户</td>
                                            <td>预订房型</td>
                                            <td>入住时间</td>
                                            <td>离店时间</td>
                                            <td>订单总额</td>
                                            <td>实际支付</td>
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
                                                        <td>${item.customerByCustId.userid}</td>
                                                        <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.checkinTime}"/></td>
                                                        <td>${item.leaveTime}</td>
                                                        <td>${item.original}</td>
                                                        <td>${item.actual}</td>

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
                                            <td>预订客户</td>
                                            <td>预订房型</td>
                                            <td>入住时间</td>
                                            <td>离店时间</td>
                                            <td>订单总额</td>
                                            <td>取消时间</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${cancelList.size()!=0}">
                                            <c:forEach items="${cancelList}" var="item" varStatus="statu">
                                                <tr id="${item.id}">
                                                    <td>${item.id}</td>
                                                    <td>${item.createTime}</td>
                                                    <td>${item.customerByCustId.userid}</td>
                                                    <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                                    <td>${item.checkinTime}</td>
                                                    <td>${item.leaveTime}</td>
                                                    <td>${item.original}</td>
                                                    <td>${item.cancelTime}</td>
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
                                            <td>预订客户</td>
                                            <td>预订房型</td>
                                            <td>入住时间</td>
                                            <td>离店时间</td>
                                            <td>订单总额</td>
                                            <td>实际支付</td>
                                            <td>实际到店时间</td>
                                            <td>处理进度</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:if test="${checkInList.size()!=0}">
                                            <c:forEach items="${checkInList}" var="item" varStatus="statu">
                                                <tr id="${item.id}">
                                                    <td>${item.id}</td>
                                                    <td>${item.createTime}</td>
                                                    <td>${item.customerByCustId.userid}</td>
                                                    <td>${item.roomInfoById.roomLevelId.roomName}</td>
                                                    <td>${item.checkinTime}</td>
                                                    <td>${item.leaveTime}</td>
                                                    <td>${item.original}</td>
                                                    <td>${item.actual}</td>
                                                    <td>${item.actualCheckinTime}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${settlementList[statu.index].status==0}">
                                                                <span class="label label-warning">待结算</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="label label-success">已结算</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
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
                                            <td>预订客户</td>
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
                                                    <td>${item.customerByCustId.userid}</td>
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

                    <div class="normal-div" style="width: 600px; height: 400px;" id="reserve-charts">
                    </div>
                </div>
            </div>


        </div>



</div>

</body>

<script>
    $(document).ready(function(){
        var giftImageUrl = "data:image/png;base64,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";


        var myChart = echarts.init(document.getElementById("reserve-charts"));

        var reserveCount = <c:out value="${reserveList.size()+1-1}" />;
        var cancelCount = <c:out value="${cancelList.size()+1-1}"/>;
        var checkInCount = <c:out value="${checkInList.size()+1-1}" />;
        var checkOutCount =<c:out value="${checkOutList.size()+1-1}"/>;

        var total = reserveCount+cancelCount+checkInCount+checkOutCount;

        var res = Math.floor((reserveCount/total)*100);
        var cancel = Math.floor((cancelCount/total)*100);
        var checkIn = Math.floor((checkInCount/total)*100);
        var checkOut = Math.floor((checkOutCount/total)*100);

        myChart.setOption({
            graphic: {
                elements: [{
                    type: 'image',
                    style: {
                        image: giftImageUrl,
                        width: 30,
                        height: 30
                    },
                    left: 'center',
                    top: 'center'
                }]
            },
            series: [{
                type: 'pie',
                radius: [30, '55%'],
                center: ['50%', '50%'],
                roseType: 'radius',
                color: ['#f2c955', '#00a69d', '#46d185', '#ec5845'],
                data: [{
                    value: res,
                    name: '已预订'
                }, {
                    value: cancel,
                    name: '取消'
                }, {
                    value: checkIn,
                    name: '已入住'
                }, {
                    value: checkOut,
                    name: '已离店'
                }],
                label: {
                    normal: {
                        textStyle: {
                            fontSize: 14
                        },
                        formatter: function(param) {
                            return param.name + ':\n' + Math.round(param.percent) + '%';
                        }
                    }
                },
                labelLine: {
                    normal: {
                        smooth: true,
                        lineStyle: {
                            width: 2
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        shadowBlur: 30,
                        shadowColor: 'rgba(0, 0, 0, 0.4)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function(idx) {
                    return Math.random() * 200;
                }
            }]
        });
    })
</script>
</html>
