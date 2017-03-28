<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的消费记录"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">我的消费</div>
                </div>
                <div class="panel-body">
                    <div class="page-header"><h1>我的消费</h1></div>
                    <div class="normal-div" id="consumpTotal">
                        <p>自加入hostelWorld以来，您总共消费了<span class="number">${customer.consumpTotal}</span></p>
                    </div>

                    <div class="normal-div" id="consumpItem">
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
                            <c:choose>
                                <c:when test="${payList.size()==0}">
                                    还没有消费记录哦~
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${payList}" var="item">
                                        <c:set var="index" value="${index+1}"/>
                                        <tr id="${item.id}">
                                            <td>${index}</td>
                                            <td><c:choose>
                                                <c:when test="${item.type==0}"><span class="label label-success">收入</span></c:when>
                                                <c:otherwise><span class="label label-warning">支出</span></c:otherwise>
                                            </c:choose></td>
                                            <td>${item.money}</td>
                                            <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd"/> </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                    <div class="normal-div" style="width: 600px;height:400px;" id="reserve-charts">

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
<script>
$(document).ready(function(){
    var myChart = echarts.init(document.getElementById("reserve-charts"));
    var option = {
        title: {
            text: '预订记录'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['消费记录']
        },
        grid: {
            left: '0',
            right: '3%',
            bottom: '3%',
            top: '13%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            splitLine: { //网格线
                show: true,
                lineStyle: {
                    color: ['#b1b1b1'],
                    type: 'dashed'
                }
            },
            data: ['3.1', '3.2', '3.3', '3.4', '3.5', '3.6', '3.7', '3.8', '3.9','3.10', '3.11', '3.12']
        },
        yAxis: {
            splitLine: { //网格线
                show: true,
                lineStyle: {
                    color: ['#b1b1b1'],
                    type: 'dashed'
                }
            }
        },
        series: [{
            name: '顺丰',
            type: 'line',
            data: ['200', '300', '500', '300', '280', '290', '230', '280', '450','800', '950', '1100'],
            label: {
                normal: {
                    show: true,
                    position: 'top' //值显示
                }
            }
        },
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
})
</script>
</html>
