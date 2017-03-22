<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/22
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="欢迎"/>
</jsp:include>
<body>
<jsp:include page="../common/managerNavbar.jsp" />
<div class="container-fluid">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title" id="title">申请列表</div>
                </div>
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${applyList.size()==0}">
                            <div class="null">这里还没有内容哦</div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-hover">
                                <caption>待审批列表</caption>
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>客栈id</th>
                                    <th>申请类型</th>
                                    <th>申请时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>


                                <c:forEach items="${applyList}" var="item">
                                    <tr id="${item.id}">
                                        <td>${item.id}</td>
                                        <td id="hostel-${item.id}">${item.hostelByHostelId.id}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status==0}">
                                                    开店申请
                                                </c:when>
                                                <c:otherwise>
                                                    修改店铺信息
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.createTime}"/></td>
                                        <td id="lastd-${item.id}">
                                            <c:choose>
                                                <c:when test="${item.status==0}">
                                                    <button class="btn btn-primary info" id="info-${item.id}" data-toggle="modal" data-target="#myModal">查看</button>
                                                    <button class="btn btn-primary accepted" id="accepted-${item.id}">通过</button>
                                                    <button class="btn btn-primary rejected" id="rejected-${item.id}">拒绝</button>
                                                </c:when>
                                                <c:when test="${item.status==1}">
                                                    <span class="label label-success">已通过</span>
                                                </c:when>
                                                <c:when test="${item.status==2}">
                                                    <span class="label label-danger">已拒绝</span>
                                                </c:when>
                                            </c:choose>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLable">客栈信息</h4>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
    </div>

</div>

</body>

<script>
    $('.info').on('click',function(e){
        e.preventDefault();
        var id = $(this).attr("id").split('-')[1];
        var hostelId = $('#hostel-'+id).text();
        $('#myModal').modal('show');
        $.ajax({
            type:'get',
            data:{
                hostelId:hostelId
            },
            url:'/manage/apply/info',
            success:function (result) {
                if(result['success']){
                    var insertHTML = "<div class='normal-div'>" +
                            "<label>客栈id：</label>" + "<span class='number'>" + result['id'] +"</span>" +
                            "</div>" +
                            "<div class='normal-div'>" +
                            "<label>客栈名称：</label>" + "<span class='number'>" + result['name'] +"</span>" +
                            "</div>" +
                            "<div class='normal-div'>" +
                            "<label>客栈绑定银行卡：</label>" + "<span class='number'>" + result['bank_card'] +"</span>" +
                            "</div>" +
                            "<div class='normal-div'>" +
                            "<label>客栈电话：</label>" + "<span class='number'>" + result['tel'] +"</span>" +
                            "</div>" +
                            "<div class='normal-div'>" +
                            "<label>客栈地址：</label>" + "<span class='number'>" + result['location'] +"</span>" +
                            "</div>" +
                            "<div class='normal-div'>" +
                            "<label>客栈描述：</label>" + "<span class='number'>" + result['description'] +"</span>" +
                            "</div>";
                    $("#myModal .modal-body").html(insertHTML);

                }
            }
        })

    });

    $('.accepted').on('click',function(e){
        e.preventDefault();
        var id = $(this).attr("id").split('-')[1];
        var hostelId = $('#hostel-'+id).text();

        $.ajax({
            type:'post',
            data:{
                id:id
            },
            url: '/manage/apply/accept',
            success:function(result){
                if(result['success']){
                    $("#"+id).remove();
                }
            }
        })
    })

    $('.rejected').on('click',function(e){
        e.preventDefault();
        var id = $(this).attr("id").split('-')[1];

        $.ajax({
            type:'post',
            data:{
                id:id
            },
            url: '/manage/apply/reject',
            success:function(result){
                $("#"+id).remove();
            }
        })
    })
</script>
</html>
