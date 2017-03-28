<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/28
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="登记入住"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">登记入住</div>
                </div>
                <div class="panel-body">
                    <div class="normal-div">
                        <div class="page-header"><h1>会员入住登记</h1></div>
                        <div class="alert" id="error-msg"></div>
                        <div class="normal-div">
                            <div class="col-md-3 right-aligned">
                                <strong>订单号</strong>
                            </div>
                            <div class="col-md-3">
                                <input class="form-control" type="text" name="id" placeholder="订单号"/>
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-success" onclick="searchReserve()">确定</button>
                            </div>
                        </div>



                        <div class="normal-div" id="reserve-order-container">
                            <table class="table table-hover" id="reserve-order-table">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>预订时间</td>
                                    <td>预订房型</td>
                                    <td>可容纳人数</td>
                                    <td>入住时间</td>
                                    <td>离店时间</td>
                                    <td>支付类型</td>
                                    <td>实际支付</td>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>

                        <c:set var="index" value="0"/>

                        <button class="btn btn-success" id="addCust" onclick="addCust()"><i class="fa fa-plus-circle fa-3x"></i></button>

                        <div class="normal-div" id="custContainer"></div>

                        <button class="btn btn-success" id="saveCust" onclick="saveCust()">提交</button>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
<script>

    $(document).ready(function(){
        var c1 = 0;
        $("#addCust").attr("counter",c1);
    });
    function searchReserve(){
        var id = $("input[name='id']").val();
        $.ajax({
            type:'get',
            url:'/hostel/getReserve/'+id,
            dataType: 'json',
            success:function(result){

                if(result.roomInfoById.hostelByHostelId.id != <%=session.getAttribute("host_id")%>){
                    alert("该订单不在这个酒店！");
                }else if(result.status!=1){
                    alert("该订单当前不是预定状态！");
                }else{
                    var id = result.id;

                    var createTime = transformToString(result.createTime);

                    var room = result.roomInfoById.roomLevelId.roomName;
                    var maxPeople = result.roomInfoById.roomLevelId.maxPeople;

                    var checkin = transformToString(result.checkinTime);

                    var checkout = transformToString(result.leaveTime);
                    var payType = result.payType;
                    if(payType==0){
                        payType = "会员卡支付";
                    }else{
                        payType = "现金支付";
                    }
                    var amount = result.actual;

                    var insertHTML = "<tr><td>" + id + "</td>" +
                            "<td>" + createTime + "</td>" +
                            "<td>" + room + "</td>" +
                            "<td id='maxPeople'>" + maxPeople + "</td>" +
                            "<td>" + checkin + "</td>" +
                            "<td>" + checkout + "</td>" +
                            "<td>" + payType + "</td>" +
                            "<td>" + amount + "</td>";
                    $('tbody').html(insertHTML);

                    $('#custContainer').css("display","block");
                }


            }
        })
    }

    function addCust(){
        var count = $("#addCust").attr("counter");
        var actual = $("#custContainer").children(".custInfo");
        var maxPeople = $("#maxPeople").text();

        if(actual.length > maxPeople-1){
            alert("人数已达上限！");
        }else{
            $.ajax({
                type:'get',
                url:'/hostel/getAddCheckInCust',
                data:{
                    index:count
                },
                success:function(result){
                    $("#custContainer").append(result);
                }
            });
            $("#addCust").attr("counter",(count+1)/1);
        }

    }

    function saveCust(){
        var cust = $("#custContainer").children(".custInfo");
        var maxPeople = $("#maxPeople").text();

        if(cust.length>maxPeople){
            alert("人数超过房间可容纳最大人数！");
        }else if(cust.length==0){
            alert("未登记入住客户！");
        }else{
            var list = {};
            var reserveId = $("input[name='id']").val();

            $.each(cust,function(n,value){
                var name = $(value).find("#custNameConstainer").text();
                var phone = $(value).find("#custPhoneContainer").text();
                list[name]=phone;
            });
            list["reserveId"]=reserveId;

            $.ajax({
                type:'post',
                url:'/hostel/checkIn',
                data: JSON.stringify(list),
                contentType:"application/json",
                dataType: 'json',
                success:function(result){
                    if(result['success']){
                        location.reload();
                        $("#error-msg").html("入住成功！");
                    }else{
                        $("#error-msg").html(result['error']);
                        $("#error-msg").addClass("alert-danger");
                    }
                }
            })
        }
    }

    function transformToString(date){
        var date = new Date(date);
        var month = date.getMonth()+1;
        if((month+"").length==1){
            month = "0"+month;
        }
        return date.getFullYear()+"-"+month+"-"+date.getDate();
    }


</script>
</html>
