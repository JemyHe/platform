<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/cart.css">
</head>
<body>
    <%@include file="../common/head.jsp"%>
    <div class="cart">
        <div class="container">
            <div class="cart-title">
                <h1 class="pull-left">
                    全部商品<span class="total-count">${cartSize}</span>件
                </h1>
            </div>
            <div class="cart-head">
                <table width="0" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="top-none">
                        <th class="td-01"><input type="checkbox" class="All-checked"></th>
                        <th class="td-02">全部</th>
                        <th class="td-03">商品信息</th>
                        <th class="td-04">数量</th>
                        <th class="td-05">单价（元）</th>
                        <th class="td-06">小计</th>
                        <th class="td-07">操作</th>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="cart-list">
                <table width="0" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <c:if test="${carts!=null}">
                        <c:forEach items="${carts}" var="cart">
                            <tr class="top-none">
                                <td class="td-01">
                                    <input type="checkbox" id="check-${cart.deal.skuId}">
                                </td>
                                <td class="td-02">
                                    <a href="${ctx}/item/${cart.deal.skuId}" target="_blank">
                                        <img src="/resources/images/deals/${cart.deal.id}/1/good${cart.deal.id}.jpg" alt="${cart.deal.dealTitle}">
                                    </a>
                                </td>
                                <td class="td-03">
                                    <p>
                                        <a href="${ctx}/item/${cart.deal.skuId}" target="_blank">${cart.deal.dealTitle}</a>
                                    </p>
                                </td>
                                <td class="td-04">
                                    <div class="munber-box">
                                        <em class="cut-btn" data-id="${cart.deal.skuId}"></em>
                                        <input id="count-${cart.deal.skuId}" type="text" value="${cart.cart.count}" readonly="readonly" max-count="${cart.deal.maxPurchaseCount}" cart-id="${cart.cart.id}" deal-price="${cart.deal.dealPrice}">
                                        <em class="add-btn" data-id="${cart.deal.skuId}"></em><span>件</span>
                                    </div>
                                </td>
                                <td class="td-05">
                                    <p>￥<span>${cart.deal.dealPrice}</span></p>
                                </td>
                                <td class="td-06">
                                    <strong>￥<em id="subtotal-${cart.deal.skuId}">${cart.subtotal}</em></strong><p></p>
                                </td>
                                <td class="td-07">
                                    <a href="" title="删除" class="det-icon"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${carts==null}">
                        <tr class="top-none">
                        <th colspan="8" style="text-align: center;font-size: 24px;font-weight: 500;padding:20px 0;">购物车为空</th>
                    </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="cart-all-box">
                <table width="0" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="top-none">
                        <th class="td-01"><input type="checkbox" class="All-checked"></th>
                        <th class="td-02">全选</th>
                        <th colspan="5">
                            <a href="JavaScript:;" title="删除选中商品" class="pull-left det-check">删除选中商品</a>
                            <div class="fr money-box">
                                <p>
                                    <span class="all-money"><small>￥</small><strong>0</strong></span> 总价
                                </p>
                                <form action="${ctx}/settlement" method="post" id="settlement_form">
                                    <input type="hidden" id="totalPrice" name="totalPrice" value="0">
                                    <input type="hidden" id="cartIds" name="cartIds"/>
                                    <a href="JavaScript:;" title="去结算" class="go-check" id="settlement"></a>
                                </form>


                            </div>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
    $(function () {

        //商品发生变化时，更新总价和购物车id
        function check() {
            var doms = $(".cart-list input[type='checkbox']:checked");
            var total = Number(0);
            var cartIds = "";
            for (var i = 0; i < doms.length; i++) {
                var dealId = doms[i].id.split("-")[1];
                total += Number($("#subtotal-" + dealId).text());
                var cartId = $("#count-" + dealId).attr("cart-id");
                cartIds += cartId + ",";
            }
            $("#cartIds").val(cartIds);
            $(".all-money strong").text(total);
        }

        //添加按钮
        $(".add-btn").click(function () {
            var skuId = $(this).attr("data-id");
            var inputDom = $("#count-"+skuId);
            var count = inputDom.val();
            var max = inputDom.attr("max-count");
            var cartId = inputDom.attr("cart-id");
            var dealPrice = inputDom.attr("deal-price");

            if(count && count>=max){
                alert("超出最大购买量");
                inputDom.val(max);
            }else{
                $.post("${ctx}/cart/"+cartId+"/1",null,function (result) {
                    if(result.success){
                        inputDom.val(Number(count)+1);
                        //更新小计金额和总金额
                        $("#subtotal-"+skuId).text(Number(Number(count)+1)*dealPrice);
                        //更新总价
                        check();
                        alert("添加成功");
                    }else{
                        alert(result.error);
                    }
                })
            }
        })

        //减少按钮
        $(".cut-btn").click(function () {
            var skuId = $(this).attr("data-id");
            var inputDom = $("#count-"+skuId);
            var count = inputDom.val();
            var max = inputDom.attr("max-count");
            var cartId = inputDom.attr("cart-id");
            var dealPrice = inputDom.attr("deal-price");
            if(count && count<=1){
                alert("购买数量不能等于0");
                inputDom.val(1);
            }else{
                $.post("${ctx}/cart/"+cartId+"/0",null,function (result) {
                    if(result.success){
                        inputDom.val(Number(count)-1);
                        //更新小计金额和总金额
                        $("#subtotal-"+skuId).text( Number(Number(count)-1)*dealPrice);
                        check();
                        alert("减少成功");
                    }else{
                        alert(result.error);
                    }
                })
            }
        })

        //全选操作
        $(".All-checked").click(function () {
            //如果你要选中
            if(this.checked){
                $(".cart-list input[type='checkbox']").prop("checked",true);
                check();
            }else{
                $(".cart-list input[type='checkbox']").prop("checked",false);
                check();
            }
        })

        //选中某一个
        $("input[type='checkbox']").click(function(){
            check();
        })

        //结算
        $("#settlement").click(function(){
            //需要总价、cartIds
            var totalPrice = $(".all-money strong").text();
            var cartIds = $("#cartIds").val();
            if(totalPrice&&totalPrice>0&&cartIds){
                $("#totalPrice").val(totalPrice);
                $("#settlement_form").submit();
            }
        })

    })
</script>
</html>
