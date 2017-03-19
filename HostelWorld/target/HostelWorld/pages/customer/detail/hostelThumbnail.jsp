<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/18
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<li>
    <div class="am-gallery-item">
        <a href="${param.url}" class="">
            <img src="${param.url}" alt="${param.title}">
            <h3 class="am-gallery-title">${param.title}</h3>
        </a>
    </div>
</li>
