<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
<head>
    <title>详情页</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/category.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/detail.css">
</head>
<body>
    <%@ include file="../common/head.jsp"%>
    <div class="bread-nav">
        <div class="container bread-nav-body">
            <div class="bread-nav-left pull-left">
                <div class="">
                    <a href="${ctx}">首页</a>
                    <span>></span>
                    <a href="${ctx}/category">设备商城</a>
                    <span>></span>
                    <a href="${ctx}/item/${deal.skuId}">${deal.dealTitle}</a>
                </div>
            </div>
        </div>
    </div>
    <div class="detail">
        <div class="container">
            <div class="detail-left pull-left">
                <img src="/resources/images/deals/${deal.id}/2/good${deal.id}.jpg" width="400" height="400">
            </div>
            <div class="detail-right pull-left">
                <h1 class="pro_title">${deal.dealTitle}</h1>
                <div class="pro_panel">
                    <div class="pro_price pull-left">
                        <span>会员价：￥</span><strong>${deal.dealPrice}</strong>
                    </div>
                    <p class="max-number pull-right">最大购买量：<span>${deal.maxPurchaseCount}件</span></p>
                </div>
                <div class="buy-number">
                    <dl>
                        <dt>采购量：</dt>
                        <dd>
                            <div class="amountnum">
                                <a href="javascript:;" class="countbtn unclick" id="btnSubCount">-</a>
                                <input type="text" class="coutinput" value="1" id="count_input" readonly="readonly">
                                <a href="javascript:;" class="countbtn" id="btnAddCount">+</a>
                            </div>
                            <c:if test="${deal.inventoryAmount>0||deal.vendibilityAmount>0}">
                                <span class="instock">有货</span>
                            </c:if>
                            <c:if test="${deal.inventoryAmount==0||deal.vendibilityAmount==0}">
                                <span class="instock">无货</span>
                            </c:if>

                        </dd>
                    </dl>
                </div>

            </div>
            <div class="actionbtn pull-left">
                <c:if test="${deal.inventoryAmount>0||deal.vendibilityAmount>0}">
                    <a class="btnrhpd" href="${ctx}/settlement/${deal.skuId}">立即购买</a>
                    <a class="btnhporder">加入购物车</a>
                </c:if>
                <c:if test="${deal.inventoryAmount==0||deal.vendibilityAmount==0}">
                    <a class="btnrhpd" disabled="disabled">立即购买</a>
                    <a class="btnhporder" disabled="disabled">加入购物车</a>
                </c:if>
            </div>
        </div>
    </div>

    <div>
        <div class="container">
            <div class="detailcont">
                <div class="detailcont-left"></div>
                <div class="detailcont-right">
                    <div class="goods-info">
                        <h3>产品介绍</h3>
                        <p>${deal.dealDetail.dealDetail}</p>
                    </div>
                    <div class="goods-info-bottom">
                        <img src="/resources/images/deals/${deal.id}/3/detail1.jpg" width="750" height="500">
                        <img src="/resources/images/deals/${deal.id}/3/detail2.jpg" width="750" height="500">
                        <img src="/resources/images/deals/${deal.id}/3/detail3.jpg" width="750" height="500">
                        <img src="/resources/images/deals/${deal.id}/3/detail4.jpg" width="750" height="500">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../common/footer.jsp"%>
</body>
<script src="/resources/plugins/js/jquery.min.js"></script>
<script type="text/javascript">

    $(function () {
        var max = "${deal.maxPurchaseCount}";

        $("#btnAddCount").click(function () {
            $(".amountnum a").removeClass("unclick");
            var input = $("#count_input").val();
            if(input && (Number(input)+1)>=Number(max)){
                $(this).addClass("unclick");
                $("#count_input").val(Number(max));
            }else{
                $("#count_input").val(Number(input)+1);
            }
        })

        $("#btnSubCount").click(function () {
            $(".amountnum a").removeClass("unclick");
            var input = $("#count_input").val();
            if(input && (Number(input)-1)<=0){
                $(this).addClass("unclick");
                $("#count_input").val(1);
            }else{
                $("#count_input").val(Number(input)-1);
            }
        })

        var skuId = "${deal.skuId}";
        $(".btnhporder").click(function () {
            var count = $("#count_input").val();
            $.post("${ctx}/cart/default/"+skuId+"/"+count,null,function (result) {
                if(result.success){
                    alert("添加成功");
                }else{
                    alert(result.error);
                }
            })
        })
    })
</script>
</html>
