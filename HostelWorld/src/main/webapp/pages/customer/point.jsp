<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/19
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="积分兑换"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="detail/sidebar.jsp"/>
        <div class="col-lg-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">积分兑换</div>
                </div>
                <div class="panel-body">
                    <div class="normal-div" id="error">

                    </div>
                    <div class="normal-div">
                        <div class="col-md-3 right-aligned">
                            我的积分：
                        </div>
                        <div class="col-md-9" id="my-point">
                            ${customer.point}
                        </div>
                    </div>

                    <div class="normal-div">
                        <div class="col-md-3 right-aligned">
                            兑换积分：
                        </div>
                        <div class="col-md-9" id="exchange-point">
                            <input type="text" id="input-point" placeholder="兑换积分数">
                            <button class="btn btn-primary" onclick="submit()">兑换</button>
                        </div>

                        <div class="col-md-9 col-md-offset-3" id="point-error"></div>

                    </div>


                    <hr>

                    <div class="normal-div tips">
                        <div class="alert alert-info">
                            <div class="col-md-2 right-aligned">
                                兑换规则：
                            </div>
                            <div class="col-md-10">
                                <p>每在hostelworld平台消费1元钱可以获得1积分</p>
                            </div>

                            <div class="col-md-10 col-md-offset-2">
                                <p>每100积分可以兑换1元钱</p>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<script>
    function submit(){
        var maxPoint = $("#my-point").innerText;
        var inputPoint = $("#input-point").val();
        if(inputPoint%100!=0){
            $('#point-error').addClass("alert");
            $('#point-error').addClass("alert-dangers");

            $('#point-error').html("兑换积分数必须是100的倍数！");
            $('#input-point').focus();
            return false;
        }
        if(inputPoint > maxPoint){
            alert(maxPoint);
            alert(inputPoint);
            $('#point-error').addClass("alert");
            $('#point-error').addClass("alert-dangers");

            $('#point-error').html("兑换积分数不能大于总积分数！");
            $('#input-point').focus();
            return false;
        }
        $.ajax({
            type:'post',
            url:'/customer/point',
            data:{
                point: $('#input-point').val()
            },
            success: function (result) {
                alert(result['point']);
                alert(result['balance']);
                if(result['success']){
                    window.location.href="/customer/dashboard";
                }else{
                    $('#error').html(result['error']);
                }
            }
        })
    }
</script>
</html>
