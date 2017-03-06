<%--
  Created by IntelliJ IDEA.
  User: 张文玘
  Date: 2017/3/4
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Hostel World</a>
        </div>

        <div>
            <ul class="nav navbar-nav">
                <li data-toggle="modal" data-target="#authModal" id="login-button" onclick="startFromLogin()"><a>登陆</a></li>
                <li data-toggle="modal" data-target="#authModal" id="register-button" onclick="startFromRegister()"><a>注册</a> </li>
            </ul>
        </div>
    </div>
</nav>

<div class="modal fade" id="authModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">x</button>
                <ul class="nav nav-tabs">
                    <li id="login-tab"><a href="#login-content" role="tab" data-toggle="tab">登陆</a></li>
                    <li id="register-tab"><a href="#register-content" role="tab" data-toggle="tab">注册</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="login-content">
                        <form class="form" id="login-form">
                            <div class="form-group">
                                <input class="form-control" name="phone" type="text" placeholder="请输入手机号"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="password" type="password" placeholder="请输入密码"/>
                            </div>
                            <div class="form-group">
                                <input type="submit" id="login" class="btn btn-primary" value="登陆" onclick="loginForm()"/>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="register-content">
                        <form class="form" id="register-form">

                            <div class="form-group">
                                <input class="form-control" name="phone" type="tel" placeholder="请输入您的手机号"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="password" type="password" placeholder="请输入您的密码"/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" name="passwordAgain" type="password" placeholder="请再次输入您的密码"/>
                            </div>

                            <div class="form-group">
                                <input class="form-control" id="validateCode" type="text" placeholder="请输入您收到的验证码"/>
                                <button class="btn btn-primary">发送验证码</button>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" id="register" value="注册" onclick="registerForm()"/>
                            </div>

                        </form>
                    </div>
                </div>


            </div>
        </div>


    </div>
</div>


