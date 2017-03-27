<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/27
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header">
    <h1>酒店计划</h1>
</div>
<c:choose>
    <c:when test="${planList.size()==0}">
        <div class="alert alert-danger">没有符合条件的计划！</div>
    </c:when>
    <c:otherwise>
        <c:forEach items="${planList}" var="item">
            <div class="col-md-10 col-md-offset-1 card">
                <div class="col-md-4">
                    <img class="img-responsive" src="/assets/img/hostel/hostel_3.jpg"/>
                </div>
                <div class="col-md-8">
                    <div class="normal-div">
                        <div>${item.roomLevelById.roomName}</div>
                        <div>最大可容纳人数：${item.roomLevelById.maxPeople}</div>
                        <div>价格：${item.price} 元/晚</div>
                        <div>描述：${item.roomLevelById.discription}</div>

                        <a type="button" class="btn btn-primary" href="/customer/reserve/${item.id}/${inTime}/${outTime}">预订</a>
                    </div>
                </div>

            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<script>
    function reserve(id){

    }
</script>