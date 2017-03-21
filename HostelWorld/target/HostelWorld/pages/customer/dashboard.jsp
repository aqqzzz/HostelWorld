<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/18
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="我的首页"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9 col-md-9">
            <div class="panel panel-defualt">
                <div class="panel-heading">
                    <div class="panel-title">
                        我的首页
                    </div>
                </div>
                <div class="panel-body">
                    <div class="normal-div">
                        <div class="right-aligned col-md-3">
                            我的会员卡号：
                        </div>
                        <div class="col-md-9">
                            <span class="number">${cust_id}</span>
                            <c:choose>
                                <c:when test="${customer.status==0}">
                                    <span class="label label-danger">未激活</span>
                                    <a type="button" class="btn btn-primary" href="/customer/validate">立刻激活</a>
                                </c:when>
                                <c:when test="${customer.status==1}">
                                    <span class="label label-success">已激活</span>
                                    <button class="btn btn-primary" onclick="stop()">停止会员</button>
                                </c:when>
                                <c:when test="${customer.status==2}">
                                    <span class="label label-warning">已暂停</span>
                                    <a type="button" class="btn btn-primary" href="/customer/recharge">恢复会员</a>
                                    <button class="btn btn-primary" onclick="stop()">停止会员</button>
                                </c:when>
                                <c:when test="${customer.status==3}">
                                    <span class="label label-danger">已停止</span>
                                </c:when>
                            </c:choose>
                        </div>


                    </div>

                    <c:if test="${customer.status==1}">
                        <div class="normal-div">
                            <div class="right-aligned col-md-3">
                                我的会员级别
                            </div>
                            <div class="col-md-9">
                                <span class="number">${custLevel.level}</span>
                            </div>
                        </div>

                        <div class="normal-div">
                            <div class="right-aligned col-md-3">
                                我的优惠折扣
                            </div>
                            <div class="col-md-9">
                                <span class="number">${custLevel.discount}</span>
                            </div>
                        </div>
                    </c:if>

                    <hr>

                    <div class="normal-div">
                        <div class="right-aligned col-md-3">
                            我的账户余额：
                        </div>
                        <div class="col-md-9">
                            <span class="number">${customer.balance}</span>
                            <a type="button" class="btn btn-primary btn-alive" href="/customer/recharge">立刻充值</a>
                        </div>

                    </div>

                    <div class="normal-div">
                        <div class="right-aligned col-md-3">
                            我的积分：
                        </div>
                        <div class="col-md-9">
                            <span class="number">${customer.point}</span>
                            <a type="button" class="btn btn-primary btn-alive" href="/customer/point">立刻兑换</a>
                        </div>

                    </div>

                    <div class="normal-div">
                        <div class="right-aligned col-md-3">
                            我的消费总额：
                        </div>
                        <div class="col-md-9">
                            <span class="number">${customer.consumpTotal}</span>
                            <a type="button" class="btn btn-primary" href="#">详细信息</a>
                        </div>
                    </div>

                    <div class="tips">
                        <c:choose>
                            <c:when test="${customer.status==0}">
                                <div class="normal-div alert alert-dangers">
                                    <strong>注意：</strong>您目前属于<span class="label label-danger">未激活</span>状态，请尽快激活您的账户
                                </div>
                            </c:when>
                            <c:when test="${customer.status==1}">
                                <div class="normal-div alert alert-warning">
                                    <strong>注意：</strong>您目前属于<span class="label label-success">已激活</span>状态，
                                    如果到<span class="number"><fmt:formatDate pattern="yyyy-MM-dd" value="${customer.custStatusByUserid.pauseTime}"/></span>
                                    您的账户余额不足1000的话，您的账户将会变成<span class="label label-warning">暂停</span>状态
                                </div>
                            </c:when>
                            <c:when test="${customer.status==2}">
                                <div class="normal-div alert alert-danger">
                                    <strong>警告：</strong>您目前属于<span class="label label-warning">暂停</span>状态，
                                    如果到<span class="number"><fmt:formatDate pattern="yyyy-MM-dd" value="${customer.custStatusByUserid.stopTime}"/></span>
                                    您没有支付操作的话，您的账户将会变成<span class="label label-danger">停止</span>状态
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="normal-div alert alert-danger">
                                    <strong>警告：</strong>您目前属于<span class="label label-danger">停止</span>状态，
                                    此会员卡已作废
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    $(document).ready(function(){
       if(${customer.status==3}){
           $('.btn-alive').attr('disabled','true');
       }
    });
    function stop(){
        var flag = confirm("您是否真的要停止账户？");
        if(flag){
            $.ajax({
                type:'post',
                url:'/customer/stop',
                success:function(result){
                    if(result['success']){
                        location.reload();
                    }
                }
            })
        }
    }
</script>
</html>
