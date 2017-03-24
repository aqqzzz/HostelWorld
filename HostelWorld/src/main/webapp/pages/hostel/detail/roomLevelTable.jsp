<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/24
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-hover" id="room-level-table">
    <thead>
    <tr>
        <th class="col-md-1">类型编号</th>
        <th class="col-md-1">名称</th>
        <th class="col-md-1">房间数目</th>
        <th class="col-md-1">最大容纳量</th>
        <th class="col-md-4">房间号</th>
        <th class="col-md-2">房间描述</th>
        <th class="col-md-2">操作</th>
    </tr>
    </thead>

    <c:choose>
        <c:when test="${roomLevels.size()==0}">
            <div class="null">
                您还没有初始化房间信息哦，赶快初始化吧~
            </div>
        </c:when>
        <c:otherwise>
            <tbody>
            <c:forEach items="${roomLevels}" var="item">
                <tr id="${item.id}">
                    <td class="room-type">${item.roomType}</td>
                    <td class="room-name">${item.roomName}</td>
                    <td class="room-count">${item.roomCount}</td>
                    <td class="max-people">${item.maxPeople}</td>
                    <td><span class="start-room">${item.startRoomNum}</span> 至 <span class="end-room">${item.endRoomNum}</span></td>
                    <td class="description">${item.discription}</td>
                    <td>
                        <button class="btn btn-warning edit-room" id="edit-${item.id}">修改</button>
                        <button class="btn btn-danger delete-room" id="delete-${item.id}">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </c:otherwise>
    </c:choose>
</table>

<script>
    $('#room-level-table').on('click','.delete-room',function(){
        var id=$(this).attr('id').split('-')[1];
        $.ajax({
            type:'post',
            url:'/hostel/delRoomLevel',
            data:{
                roomLevelId: id
            },
            success: function () {
                getRoomLevelInfo();
                var tips=$(".tips");
                tips.addClass("alert-success");
                tips.html("删除成功！");

            },
            error: function(result){
            }
        })
    });

    $('#room-level-table').on('click','.edit-room',function(){
        var id = $(this).attr('id').split('-')[1];
        var roomType = $('#'+id+' .room-type').text();
        var roomName = $('#'+id+' .room-name').text();
        var roomCount = $('#'+id+' .room-count').text();
        var maxPeople = $('#'+id+' .max-people').text();
        var startRoomNum = $('#'+id+' .start-room').text();
        var endRoomNum = $('#'+id+' .end-room').text();
        var description = $('#'+id+' .description').text();
        var insertHTML= "<td><input type='text' class='form-control' name='roomType' value="+roomType+"></td>" +
                "<td><input type='text' class='form-control' name='roomName' value="+roomName+"></td>" +
                "<td><input type='text' class='form-control' name='roomCount'value="+roomCount+"></td>" +
                "<td><input type='text' class='form-control' name='maxPeople'value="+maxPeople+"></td>" +
                "<td><input type='text' name='startRoomNum' value="+startRoomNum+"> 至 <input type='text' name='endRoomNum' value="+endRoomNum+"></td>" +
                "<td><input type='text' class='form-control' name='description'value="+description+"></td>" +
                "<td><button class='btn btn-success' onclick='saveRoomLevel("+id+")'><i class='fa fa-check'/></button>" +
                "<button class='btn btn-danger' onclick='changeCancel()'><i class='fa fa-close'/></button></td>";

        $("#"+id).html(insertHTML);
    });


    function changeCancel(){
        getRoomLevelInfo();
    }

    function saveRoomLevel(id){
        $.ajax({
            type:'POST',
            url:'/hostel/saveRoomLevel',
            data: JSON.stringify({
                id: id,
                roomType: $("input[name='roomType']").val(),
                roomName: $("input[name='roomName']").val(),
                roomCount: $("input[name='roomCount']").val(),

                maxPeople: $("input[name='maxPeople']").val(),
                startRoomNum: $("input[name='startRoomNum']").val(),
                endRoomNum: $("input[name='endRoomNum']").val(),
                discription: $("input[name='description']").val(),

            }),
            contentType:'application/json',
            dataType: 'html',
            success:function(result){
                $("#room-level-container").html(result);
                var msg = $(".tips");
                msg.addClass("alert-success");
                msg.html("更新成功！");
            },
            error:function(result){
                alert(JSON.stringify(result));
            }
        })
    }

</script>
