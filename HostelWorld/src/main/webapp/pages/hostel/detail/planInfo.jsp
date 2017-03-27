<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/26
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="tip" id="error-message">
    <c:if test="${error!=null}">
        <c:out value="${error}"/>
    </c:if>
</div>
<table id="plan-info-table" class="table table-hover">
    <thead>
    <tr>
        <th>计划编号</th>
        <th>房间类型</th>
        <th>计划开始时间</th>
        <th>计划结束时间</th>
        <th>价格(元/晚)</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:choose>
        <c:when test="${planList.size()==0}">
            <div class="normal-div">
                您还没有发布计划哦，赶快发布一个吧~
            </div>
        </c:when>
        <c:otherwise>
            <tbody>
            <c:set var="index" value="0"/>
            <c:forEach items="${planList}" var="item">
                <tr id="${item.id}">
                    <td class="index"><c:set var="index" value="${index+1}"/>
                    <c:out value="${index}"/> </td>
                    <td class="roomLevel">${item.roomLevelById.roomName}</td>
                    <td class="startTime"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.startTime}"/></td>
                    <td class="endTime"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.endTime}"/> </td>
                    <td class="price">${item.price}</td>
                    <td>
                        <button class="btn btn-warning edit-plan" id="editPlan-${item.id}">修改</button>
                        <button class="btn btn-danger delete-plan" id="deletePlan-${item.id}">删除</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </c:otherwise>
    </c:choose>
    <tbody>

    </tbody>
</table>

<script>
    function addPlanCell(){
        var tb = $('#plan-info-table');
        var index = <c:out value="${index+1}"/>;

        var options;
        <c:forEach items="${roomLevelList}" var="room">
        options+='<option id="option-${room.id}">${room.roomName}</option>';
        </c:forEach>

        var currentDate = new Date();
        var year = currentDate.getFullYear();
        var month = currentDate.getMonth()+1+"";
        //month补0
        if(month.length==1){
            month = "0"+month;

        }
        var day = currentDate.getDate();
        var dateString = year+"-"+month+"-"+day;

        var insetHTML = '<tr id="edit-plan-input">'+
                '<td>'+index+'</td>'+
                '<td><select class="form-control" name="roomLevelById">'+ options +
                '</select></td>'+
                '<td><input type="date" class="form-control" name="startTime" min="'+dateString+'"/></td>'+
                '<td><input type="date" class="form-control" name="endTime" min="'+dateString+'"/> </td>'+
                '<td><input type="text" class="form-control" name="price"/></td>'+
                '<td><button class="btn btn-success" onclick="savePlan()"><i class="fa fa-check"/></button>'+
                '<button class="btn btn-danger" onclick="cancelAdd()"><i class="fa fa-close" /></button> </td>'+
                '</tr>';

        tb.append(insetHTML);
    };

    function savePlan(id=null){
        var roomLevel = $("select[name='roomLevelById']").find("option:selected");
        var roomLevelId = roomLevel.attr("id").split('-')[1];

        $.ajax({
            type:'post',
            url:'/hostel/addPlanInfo',
            data: JSON.stringify({
                id:id,
                roomLevelId: roomLevelId,
                startTime: $("input[name='startTime']").val(),
                endTime: $("input[name='endTime']").val(),
                price: $("input[name='price']").val()
            }),
            contentType: 'application/json',
            dataType: 'html',
            success: function(result){
                $('#plan-info-container').html(result);
            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        })
    };

    function cancelAdd(){
        $('#edit-plan-input').remove();
    };

    $('#plan-info-table').on('click','.edit-plan',function(){
        var id = $(this).attr("id").split('-')[1];
        var index = $("#"+id+" .index").text();
        var roomName = $("#"+id+" .roomLevel").text();
        var startTime = $("#"+id+" .startTime").text();
        var endTime = $("#"+id+" .endTime").text();
        var price = $("#"+id+" .price").text();

        var options;

        <c:forEach items="${roomLevelList}" var="room">
        options+='<option id="option-${room.id}">${room.roomName}</option>';
        </c:forEach>
        var insertHTML = '<td>'+index+'</td>'+
                '<td><select class="form-control" name="roomLevelById">'+ options +
                '</select></td>'+
                '<td><input type="date" class="form-control" name="startTime" value="'+startTime+'"/></td>'+
                '<td><input type="date" class="form-control" name="endTime" value='+endTime+'/> </td>'+
                '<td><input type="text" class="form-control" name="price" value="'+price+'"/></td>'+
                '<td><button class="btn btn-success" onclick="savePlan('+id+')"><i class="fa fa-check"/></button>'+
                '<button class="btn btn-danger" onclick="getPlanInfo()"><i class="fa fa-close" /></button> </td>';

        $('tr#'+id).html(insertHTML);
        $('select[name="roomLevelById"]').val(roomName);
    });


    $('#plan-info-table').on('click', '.delete-plan', function(){
        var id = $(this).attr("id").split('-')[1];

        $.ajax({
            type:'post',
            url:'/hostel/deletePlanInfo',
            data:{
                planId: id
            },
            success:function(result){
                if(result['success']){
                    getPlanInfo();
                }else{
                    var errorMsg = $("#error-message");
                    errorMsg.addClass("alert alert-danger");
                    errorMsg.html(result['error']);
                }

            },
            error: function(result){
                alert(JSON.stringify(result));
            }
        })
    });

</script>