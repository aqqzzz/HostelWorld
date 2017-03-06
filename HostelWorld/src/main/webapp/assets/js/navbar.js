/**
 * Created by 张文玘 on 2017/3/5.
 */

    function loginForm(){
        alert("click login submit");
        $.ajax({
            type:'POST',
            url:'/auth/login',
            data:$('#login-form').serialize(),
            success:function (result) {
                alert(result["success"]);
                if(result["success"]==false){
                    alert(result["error"]);
                }else{
                    window.location.href="/index";
                }
            },
            error:function () {
                alert("error");
            }
        })
    }

    function registerForm(){
        alert("register form submit");
        $.ajax({
            type:'POST',
            url:'/auth/register',
            data:$('#register-form').serialize(),
            success:function (result) {
                if(result["success"]==false){
                    alert(result["error"]);
                }else{
                    window.location.href="/index";
                }
            },
            error:function () {
                alert("error");
            }
        })
    }

    function startFromLogin(){
        $('#login-content').addClass("active");
        $('#login-tab').addClass("active");
        $('#register-content').removeClass("active");
        $('#register-tab').removeClass("active");
    }

    function startFromRegister() {
        $('#login-content').removeClass("active");
        $('#login-tab').removeClass("active");
        $('#register-content').addClass("active");
        $('#register-tab').addClass("active");
    }
