<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/24
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${success==true}">

            <c:if test="${hint.equals('rejected')}">
                <div class="normal-div tips alert-danger">
                    您的修改店铺信息申请未通过!
                </div>
            </c:if>
            <c:if test="${hint.equals('approved')}">
                <div class="normal-div tips alert-success">
                    您的修改店铺信息申请已通过！
                </div>
            </c:if>

    </c:when>
    <c:otherwise>
        <div class="normal-div tips alert-warning">
            您的修改店铺信息申请正在审批中，请稍等~
        </div>
    </c:otherwise>
</c:choose>
<form class="form form-horizontal" id="hostel-info-edit">
    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈id</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="id" value="${hostel.id}" readonly/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈名称</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="hostName" value="${hostel.hostName}"/>
        </div>
    </div>

    <input type="hidden" name="hostPassword" value="${hostel.hostPassword}"/>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈绑定银行卡</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="hostBankAccountByBankCard.id" value="${hostel.hostBankAccountByBankCard.id}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈账户余额</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="balance" value="${hostel.balance}" readonly/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈电话</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="hostTel" value="${hostel.hostTel}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈地址</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="location" value="${hostel.location}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-2">
            <label class="control-label right-aligned">您的客栈描述</label>
        </div>
        <div class="col-md-10">
            <input class="form-control" type="text" name="discription" value="${hostel.discription}"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-md-10 col-md-offset-2">
            <input class="btn btn-default" type="submit" onclick="edit(event)" id="submit-btn" value="修改"/>
        </div>
    </div>
</form>

<script>
    $(document).ready(function(){
        if(${success==false}){
            $('#submit-btn').attr("disabled","true");
        }else{
            $('#submit-btn').removeAttr("disabled");
        }
    });

    function edit(e){
        e.preventDefault();

        $.ajax({
            type:'post',
            url:'/hostel/editInfo',
            data:JSON.stringify(objectifyForm($('#hostel-info-edit').serializeArray())),
            contentType: 'application/json',
            dataType:'html',
            success: function(result){
                $("#hostel-info-container").html(result);
            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        })
    }
</script>