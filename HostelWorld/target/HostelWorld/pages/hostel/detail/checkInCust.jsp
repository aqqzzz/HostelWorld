<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="normal-div col-md-6 card custInfo" id="${index}">
    <div class="normal-div">
        <div class="col-md-3">
            <label>姓名</label>
        </div>
        <div class="col-md-8">
            <input class="form-control" type="text" name="custName" id="custName"/>
            <p id="custNameConstainer" style="display: none;"></p>
        </div>
    </div>

    <div class="normal-div">
        <div class="form-group">
            <div class="col-md-3">
                <label>手机号</label>
            </div>
            <div class="col-md-8">
                <input class="form-control" type="text" name="custPhone" id="custPhone"/>
                <p  id="custPhoneContainer" style="display: none;"></p>
            </div>
        </div>
    </div>

    <div id="btn-container">
        <button class="btn btn-primary btn-persist" id="btn-persist-${index}">确定</button>
        <button class="btn btn-warning btn-change" id="btn-change-${index}" style="display: none;">修改</button>
        <button class="btn btn-danger btn-delete" id="btn-delete-${index}">删除</button>
    </div>

</div>

<script>

    $(".btn-persist").on("click",function(){
        var id = $(this).attr("id").split("-")[2];
        var custName = $("#"+id+" #custName");
        var custPhone = $("#"+id+" #custPhone");
        var name = custName.val();
        var phone = custPhone.val();

        var  custNameContainer = $("#"+id+" #custNameConstainer");
        var custPhoneContainer = $("#"+id+" #custPhoneContainer");
        custNameContainer.html(name);
        custPhoneContainer.html(phone);
        custNameContainer.css("display","block");
        custPhoneContainer.css("display","block");
        custName.css("display","none");
        custPhone.css("display","none");

        $("#"+id+" #btn-persist-"+id).css("display","none");
        $("#"+id+" #btn-change-"+id).css("display","block");
    });


    $(".btn-change").on("click",function(){
        var id = $(this).attr("id").split("-")[2];
        var custName = $("#"+id+" #custNameConstainer");
        var custPhone = $("#"+id+" #custPhoneContainer");
        var name = custName.text();
        var phone = custPhone.text();

        custName.css("display","none");
        custPhone.css("display","none");

        var inputCustName = $("#"+id+" #custName");
        var inputCustPhone = $("#"+id+" #custPhone");
        inputCustName.css("display", "block");
        inputCustPhone.css("display","block");
        inputCustName.attr("value", name);
        inputCustPhone.attr("value", phone);

        $("#"+id+" #btn-persist-"+id).css("display","block");
        $("#"+id+" #btn-change-"+id).css("display","none");
    });

    $(".btn-delete").on("click",function(){
        var id = $(this).attr("id").split("-")[2];
        $(".custInfo#"+id).remove();
    })
</script>
