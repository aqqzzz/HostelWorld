/**
 * Created by 张文玘 on 2017/3/10.
 */
function callRegister(){
    $('.register-form').css("display","block");
    $('.login-form').css("display","none");
}

function callLogin(){
    $('.login-form').css("display","block");
    $('.register-form').css("display","none");
}

function userLogin(){
    alert("click login submit");
    $.ajax({
        type:'POST',
        url:'/auth/login',
        data:$('#user-login-form').serialize(),
        success:function (result) {
            alert("ajax success");
            alert(JSON.stringify(result));
            if(result["success"]==false){
                alert(result["error"]);
            }else{
                window.location.href="customer/home";
            }
        },
        error:function (result) {
            alert("this is an error");
            alert(JSON.stringify(result));

        }
    })
}

function userRegister(){
    alert("register form submit");
    $.ajax({
        type:'POST',
        url:'/auth/register',
        data:$('#user-register-form').serialize(),
        success:function (result) {
            console.log(JSON.stringify(result));
            if(result["success"]==false){
                alert(result["error"]);
            }else{
                window.location.href="/index";
            }
        },
        error:function (result) {
            console.log(JSON.stringify(result));

            alert("this is an error");
        }
    })
}
