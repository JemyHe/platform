<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/plugins/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
    <div class="header">
        <div class="container">
            <div class="pull-left">xxx导航</div>
            <div class="pull-right">
                <ul class="nav navbar-nav">
                    <c:if test="${username!=null}">
                        <li>${username}欢迎你<span>|</span></li>
                    </c:if>
                    <c:if test="${username == null}">
                        <li><a href="${ctx}/user/login">请登录</a><span>|</span></li>
                        <li><a href="${ctx}/user/regist">免费注册</a><span>|</span></li>
                    </c:if>
                    <li><a href="">我的订单</a><span>|</span></li>
                    <li><a href="">会员中心</a><span>|</span></li>
                    <li><a href="">消息</a><span>|</span></li>
                    <li>免费热线:400-888-999</li>
                </ul>
            </div>
        </div>
        <div>
            <a href="${ctx}">
                <div class="header-image"></div>
            </a>
        </div>
    </div>

    <div class="search">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="logo pull-left"></div>
                    <div class="logo-text pull-left">
                        省心更省钱一站式
                        <span class="text">一家专做XXXX的网站！</span>
                    </div>
                </div>
                <div class="col-lg-5 search-input">
                    <div class="input-group">
                        <input name="s" id="search_input" type="text" class="form-control input-lg" placeholder="摄像机">
                        <span class="input-group-btn">
                            <a id="search_name" class="btn btn-info btn-lg">搜索</a>
                        </span>
                    </div>

                    <div>
                        <ul class="nav navbar-nav">
                            <li class="nav-dis"><a href="">海尔</a></li>
                            <li class="nav-dis"><a href="">海尔</a></li>
                            <li class="nav-dis"><a href="">海尔</a></li>
                            <li class="nav-dis"><a href="">海尔</a></li>
                            <li class="nav-dis"><a href="">iphon7</a></li>
                            <li class="nav-dis"><a href="">iphon7</a></li>
                            <li class="nav-dis"><a href="">iphon7</a></li>
                            <li class="nav-dis"><a href="">小米</a></li>
                            <li class="nav-dis"><a href="">小米</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 cart">
                    <div class="cart-body">
                        <a href="${ctx}/cart" class="cart-text">
                            <span class="icon-cart"></span>
                            去购物车结算
                            <span class="icon-arrow"></span>
                        </a>
                    </div>
                    <div class="cart-number">
                        <p>
                            <c:if test="${cartSize!=null}">${cartSize}</c:if>
                            <c:if test="${cartSize==null}">0</c:if>
                        </p>
                        <span class="icon-number"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="menu">
        <div class="container">
            <div class="menu-left pull-left">
                <a href="${ctx}">
                    <span class="icon-menu"></span>
                    全部商品分类
                </a>
                <div class="menu-box">
                    <c:forEach items="${categories}" var="item">
                        <div class="category">
                            <a href="${ctx}/category/${item.urlName}">
                                <span class="icon-category icon01"></span>
                                ${item.name}
                                <span class="icon-category icon-arrow-right"></span>
                            </a>
                            <c:if test="${item.children!=null}">
                                <div class="menu-content">
                                    <ul class="menu-categories">
                                        <c:forEach items="${item.children}" var="child">
                                            <li>
                                                <h3>
                                                    <a href="${ctx}/category/${child.urlName}">${child.name}</a>
                                                </h3>
                                                <c:if test="${child.children!=null}">
                                                    <ul class="nav navbar-nav">
                                                        <c:forEach items="${child.children}" var="sub">
                                                            <li><a href="${ctx}/category/${sub.urlName}">${sub.name}</a><span>|</span></li>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="menu-right pull-left">
                <ul class="nav navbar-nav">
                    <li><a href="${ctx}">设备商城</a></li>
                    <li><a href="${ctx}">设备商城</a></li>
                    <li><a href="${ctx}">设备商城</a></li>
                    <li><a href="${ctx}">设备商城</a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
<script src="/resources/plugins/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {

        $(".menu-box").hide();

        var keyword = "${s}";
        if(keyword){
            $("#search_input").attr("placeholder","${s}");
        }

        $("#search_name").click(function () {
            var search_name = $("#search_input").val();
            if(search_name){
                $(this).attr("href","${ctx}/search/1/"+search_name);
            }
        })
    })
</script>
</html>
