<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/27
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="预定"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="normal-div col-md-10 col-md-offset-1">
        <div class="page-header">
            <h1>预定</h1>
        </div>

        <div class="normal-div" id="error-msg">
            <c:if test="${plan==null}">
                <div class="alert alert-danger">没有符合条件的计划！</div>
            </c:if>
            <c:if test="${roomInfos.size()==0}">
                <div class="alert alert-danger">没有符合条件的房间！请修改入住和离店时间重新搜索</div>
            </c:if>
        </div>
        <form class="form form-horizontal" action="/customer/reserve" method="post" id="reserve-form">
            <div class="normal-div border-div">
                <h1>预定信息</h1>
                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">入住时间：</label>
                    </div>
                    <div class="col-md-8">
                        <input type="date" class="form-control" name="inTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${inTime}"/>"
                               min = "<fmt:formatDate value="${plan.startTime}" pattern="yyyy-MM-dd"/>" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">离店时间：</label>
                    </div>
                    <div class="col-md-8">
                        <input type="date" class="form-control" name="outTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${outTime}"/>"
                               max = "<fmt:formatDate value="${plan.endTime}" pattern="yyyy-MM-dd"/>" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">共计：</label>
                    </div>
                    <div class="col-md-8">
                        <label>${betweenDays}天</label>
                        <label>${betweenDays*plan.price}元/间</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">可预订房间数：</label>
                    </div>
                    <div class="col-md-8">
                        <label>${roomInfos.size()}</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">预订房间数：</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" name="roomCount" class="form-control" placeholder="请输入大于0小于${roomInfos.size()}的整数"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-2">
                        <label class="right-aligned">付款方式：</label>
                    </div>
                    <div class="col-md-8">
                        <input type="radio" name="payType" value="0">会员卡
                        <input type="radio" name="payType" value="1">现金
                    </div>
                </div>

                <input type="hidden" name="planId" value="${plan.id}"/>

                <input type="submit" class="btn btn-primary" value="预订"/>
            </div>
        </form>

    </div>
</div>

</body>
<script>
    $(document).ready(function(){
        alert(${plan.id});
    })
    <%--function submit() {--%>
        <%--alert(${plan.id});--%>
        <%--var maxRoomCount = ${roomInfos.size()};--%>
        <%--var roomCount = $("input[name='roomCount']").val();--%>
        <%--if(roomCount > maxRoomCount){--%>
            <%--alert("预定房间数不能大于"+maxRoomCount+"!");--%>
            <%--return false;--%>
        <%--}--%>
        <%--return true;--%>
    <%--}--%>
</script>
</html>
