<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
    <head>
        <title>注册页</title>
        <link rel="stylesheet" type="text/css" href="/resources/plugins/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/common.css">
        <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    </head>
    <body>
        <div class="top-part">
            <div class="container">
                <div class="top-part-left pull-left">
                    <div class="logo pull-left">
                        <a href="">
                            <img src="/resources/images/logo.jpg" width="150" height="100">
                        </a>
                    </div>
                    <h2 class="pull-left">用户注册</h2>
                </div>
                <div class="top-part-right pull-right">
                    客服热线:
                    <span>400-XXX-XXXX</span>
                </div>
            </div>
        </div>

        <div class="login-back">
            <div class="container">
                <div class="input-box">
                    <div class="title">
                        <h1 class="pull-left">
                            <strong>会员</strong>注册
                        </h1>
                        <h2 class="pull-right">
                            已经是会员
                            <a href="">立即登录</a>
                        </h2>
                    </div>
                    <form id="regist_form" action="${ctx}/user/register" method="post">
                        <div class="input">
                            <h1 class="icon-01"></h1>
                            <input id="username" type="text" name="name" placeholder="用户名">
                        </div>
                        <div class="input">
                            <h1 class="icon-02"></h1>
                            <input id="password" type="password" name="password" placeholder="密码">
                        </div>
                        <a class="login-btn" id="regist_btn">同意协议并注册</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="/resources/plugins/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){

            $("#username").blur(function () {
                var input_name = $(this).val();
                if(input_name){
                    //ajax请求
                    $.post("${ctx}/user/checkName/"+input_name,null,function (result) {
                        var success = result.success;
                        if(!success){
                            alert(result.error);
                        }
                    })
                }
            })

            //提交表单
            $("#regist_btn").click(function () {

                $(this).addClass("disabled");
                var input_name = $("#username").val();
                var input_password = $("#password").val();
                if(input_name && input_password){
                    //提交表单
                    $("#regist_form").submit();
                }

            })
        })

    </script>

</html>
