<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
    <head>
        <title>登录页</title>
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
                    <h2 class="pull-left">用户登录</h2>
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
                                <strong>会员</strong>登录
                            </h1>
                            <h2 class="pull-right">
                                还不是会员？
                                <a href="">快速注册</a>
                            </h2>
                        </div>
                        <form id="login_form" action="${ctx}/user/doLogin" method="post">
                            <div class="input">
                                <h1 class="icon-01"></h1>
                                <input id="username" type="text" name="name" placeholder="用户名">
                            </div>
                            <div class="input">
                                <h1 class="icon-02"></h1>
                                <input id="password" type="password" name="password" placeholder="密码">
                            </div>
                            <div class="remember-box">
                                <label class="pull-left remember">
                                    <input type="checkbox" style="color: rgb(204, 204, 204);">
                                    <span>记住密码</span>
                                </label>
                                <a href="" class="pull-right forget">忘记密码?</a>
                            </div>
                            <a class="login-btn" id="login_btn">立即登录</a>
                        </form>
                    </div>
            </div>
        </div>
    </body>
    <script src="/resources/plugins/js/jquery.min.js"></script>
    <script type="text/javascript">
        //页面加载时候运行
        $(function(){

            $("#login_btn").click(function(){
                //禁用按钮
                $(this).addClass("disabled");
                //取值：取出username和password，验证一下参数
                var input_name = $("#username").val();
                var input_password = $("#password").val();
                if(input_name && input_password){
                    //提交表单
                    $("#login_form").submit();
                }
            })

        })
    </script>
</html>
