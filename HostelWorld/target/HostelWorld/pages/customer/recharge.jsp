<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/19
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="充值"/>
</jsp:include>
<style>
    .col-md-9{
        margin-bottom:15px;
    }
</style>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">会员充值</div>
                </div>
                <div class="panel-body">

                    <c:if test="${customer.status==0}">
                        <div class="alert alert-warning">
                            <strong>警告：</strong>您现在还不是正式会员，一次性充值1000元及以上才可以激活会员资格哦！
                        </div>
                    </c:if>

                    <div class="error alert alert-dangers"></div>
                    <div class="normal-div form-group">
                        <div class="col-md-3 right-aligned">
                            我的余额：
                        </div>
                        <div class="col-md-9">
                            ${customer.balance}
                        </div>
                    </div>

                    <div class="normal-div form-group">
                        <div class="col-md-3 right-aligned">
                            我的银行卡号：
                        </div>
                        <div class="col-md-9">
                            ${customer.bankAccountByBankCard.id}
                        </div>
                    </div>

                    <div class="normal-div">
                        <div class="col-md-3 right-aligned">
                            充值金额：
                        </div>
                        <div class="col-md-9">
                            <input type="text" name="amount" id="amount" value="0">
                        </div>

                    </div>

                    <div class="normal-div">
                        <div class="col-md-3 right-aligned">
                            密码：
                        </div>
                        <div class="col-md-9">
                            <input type="password" name="password" id="password" value="">
                        </div>
                    </div>

                    <div class="col-md-3"></div>
                    <div class="col-md-9">
                        <button class="btn btn-primary" onclick="submit()">确认充值</button>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){

    });
    function submit(){
        $.ajax({
            type:'post',
            url:'/customer/recharge',
            data:{
                money:$('#amount').val(),
                password:$('#password').val()
            },
            success:function(result){
                if(result['success']==false){
                    $('.error').html(result['error']).hide(3000);
                }else{
                    window.location.href="/customer/dashboard";
                }
            }
        })
    }
</script>
</html>
