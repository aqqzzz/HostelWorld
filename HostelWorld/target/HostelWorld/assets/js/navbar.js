/**
 * Created by 张文玘 on 2017/3/5.
 */

    // function loginForm(){
    //     alert("click login submit");
    //     $.ajax({
    //         type:'POST',
    //         url:'/auth/login',
    //         data:$('#login-form').serialize(),
    //         success:function (result) {
    //             alert("ajax success");
    //             document.write(result);
    //             if(result["success"]==false){
    //                 alert(result["error"]);
    //             }else{
    //                 window.location.href="/index";
    //             }
    //         },
    //         error:function (result) {
    //             alert("this is an error");
    //             document.write(result);
    //         }
    //     })
    // }
    //
    // function registerForm(){
    //     alert("register form submit");
    //     $.ajax({
    //         type:'POST',
    //         url:'/auth/register',
    //         data:$('#register-form').serialize(),
    //         success:function (result) {
    //             if(result["success"]==false){
    //                 alert(result["error"]);
    //             }else{
    //                 window.location.href="/index";
    //             }
    //         },
    //         error:function () {
    //             alert("this is an error");
    //         }
    //     })
    // }

    function callRegister(){
        $('.register-form').css("display","block");
        $('.login-form').css("display","none");
    }

    function callLogin(){
        $('.login-form').css("display","block");
        $('.register-form').css("display","none");
    }
