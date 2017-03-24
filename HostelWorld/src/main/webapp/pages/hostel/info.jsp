<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/24
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="客栈信息"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">客栈信息</div>
                </div>
                <div class="panel-body">

                    <div class="normal-div room-info">
                        <div class="page-header">
                            <h1>房间信息</h1>
                        </div>

                        <button class="btn btn-success add-room" onclick="addRoomLevelCell()"><i class="fa fa-plus-circle">添加房间信息</i></button>

                        <div class="normal-div tips"></div>

                        <div class="normal-div" id="room-level-container">
                        </div>
                    </div>

                    <div class="normal-div hostel-info">
                        <div class="page-header">
                            <h1>客栈信息</h1>
                        </div>
                        <div class="normal-div" id="hostel-info-container">

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>

</body>

<script>

    $(document).ready(function(){
        getRoomLevelInfo();
        getHostelInfo();
    });

    function getRoomLevelInfo(){
        $.ajax({
            type:'get',
            url:'/hostel/getRoomLevelInfo',
            success: function(result){
                $('#room-level-container').html(result);
            },
            error:function (result) {
                alert(JSON.stringify(result));
            }
        })
    }

    function getHostelInfo(){
        $.ajax({
            type:'get',
            url:'/hostel/getInfo',
            success: function(result){
                $('#hostel-info-container').html(result);
            },
            error:function(result){
                alert(JSON.stringify(result));
            }

        })
    }

    function addRoomLevelCell(){
        var tb = $('#room-level-table');

        var insertHTML = "<tr id='edit-input'><td><input type='text' class='form-control' name='roomType'></td>" +
                "<td><input type='text' class='form-control' name='roomName'></td>" +
                "<td><input type='text' class='form-control' name='roomCount'></td>" +
                "<td><input type='text' class='form-control' name='maxPeople'></td>" +
                "<td><input type='text' name='startRoomNum'> 至 <input type='text' name='endRoomNum'></td>" +
                "<td><input type='text' class='form-control' name='description'></td>" +
                "<td><button class='btn btn-success' onclick='addRoomLevel()'><i class='fa fa-check'/></button>" +
                "<button class='btn btn-danger' onclick='deleteCell()'><i class='fa fa-close'/></button></td></tr>";
        tb.append(insertHTML);

    }

    function addRoomLevel() {
        $.ajax({
            type:'POST',
            url:'/hostel/addRoomLevel',
            data: JSON.stringify({
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
            },
            error:function(result){
                alert(JSON.stringify(result));
            }
        })
    }

    function deleteCell(){
        $("#edit-input").remove();
    }



</script>
</html>
