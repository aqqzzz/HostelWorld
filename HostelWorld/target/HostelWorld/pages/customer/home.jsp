<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/10
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../common/includeHeader.jsp">
    <jsp:param name="title" value="欢迎"/>
</jsp:include>
<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="home-curtain">
    <div class="am-g">
        <div class="am-u-lg-12">
            <h1 class="home-curtain-title">去旅行，去生活</h1>
        </div>
    </div>
</div>
<div class="home-detail">
    <div class="am-g am-container">
        <div class="am-u-lg-12">
            <h1 class="home-detail-h1">发现世界</h1>
            <p class="home-detail-p">有 些 好 玩， 住 了 才 知 道</p>
        </div>

        <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-gallery-overlay"
            data-am-gallery="{ pureview:true }">

            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_1.jpg"/>
                <jsp:param name="title" value="土耳其阿戈斯特色酒店"/>
            </jsp:include>
            
            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_2.jpg"/>
                <jsp:param name="title" value="芬兰卡克斯劳坦恩酒店"/>
            </jsp:include>

            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_3.jpg"/>
                <jsp:param name="title" value="圣卢西亚拉德拉酒店"/>
            </jsp:include>

            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_4.jpg"/>
                <jsp:param name="title" value="坦桑尼亚曼塔海上漂浮酒店"/>
            </jsp:include>

            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_5.jpg"/>
                <jsp:param name="title" value="意大利溶洞酒店"/>
            </jsp:include>
            
            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_6.jpg"/>
                <jsp:param name="title" value="希腊Katikies酒店"/>
            </jsp:include>
            
            <jsp:include page="detail/hostelThumbnail.jsp">
                <jsp:param name="url" value="/assets/img/hostel/hostel_7.jpg"/>
                <jsp:param name="title" value="意大利波西塔诺酒店"/>
            </jsp:include>
        </ul>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
