<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
<head>
    <title>生成订单</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/order.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/cart.css">
</head>
<body>
    <%@ include file="../common/head.jsp"%>

    <div class="settlement">
        <div class="container">
            <div class="address-title">
                <h1 class="f1">填写核对订单信息</h1>
            </div>
            <div class="address-info">

                <c:if test="${addresses.size()!=0}">

                    <div class="address-info-title">
                        <div class="infor-title">
                            <em class="icon-01"></em>
                            收货人信息
                        </div>
                        <c:forEach items="${addresses}" var="address" varStatus="vs">
                            <ul id="oneAddress-${address.id}" class="adders-list <c:if test="${vs.index==0}">cur</c:if>">
                                <li class="adders-li01 pull-left">
                                    <input type="radio"  <c:if test="${vs.index==0}">checked</c:if>><span>${address.receiver}</span><span>${address.phone}</span>
                                </li>
                                <li class="adders-li02 pull-left">${address.area}${address.detail}</li>
                                <li class="adders-li03 pull-left">
                                    <span>
                                        <c:if test="${address.type==1}">[默认地址]</c:if>
                                        <c:if test="${address.type==0}"><a onclick="settle.address.defaultAddress(${address.id})">[设置为默认地址]</a></c:if>
                                    </span>
                                </li>
                                <li class="adders-li04 pull-left">
                                    <a title="修改" onclick="settle.address.updateAddress()">修改</a>
                                    <a title="删除" onclick="settle.address.deleteAddress(${address.id})">删除</a>
                                </li>
                            </ul>
                        </c:forEach>
                        <div class="infor-bd adders-add" style="display:none;">
                            <a class="add-btn" href="" title="使用新地址">使用新地址</a>
                        </div>
                    </div>
                </c:if>

                <c:if test="${addresses.size()==0}">

                    <div class="adders-box infor-bd">
                        <%--div class="adders-box-title">
                            <em class="pop-close pull-right"></em>
                            <h2>使用新地址</h2>
                        </div>--%>
                        <form action="${ctx}/user/address/save" method="post" id="saveAdd">
                            <ul>
                                <li>
                                    <span class="pull-left"><strong>*</strong>收货人：</span>
                                    <input class="pull-left w203" type="text" placeholder="" name="receiver">
                                </li>
                                <li>
                                    <span class="pull-left"><strong>*</strong>手机：</span>
                                    <input class="pull-left w203" type="text" placeholder="" name="phone">
                                </li>
                                <li>
                                    <span class="pull-left"><strong>*</strong>省市：</span>
                                    <select id="provinceId" class="pull-left" onchange="settle.init.initCities()"><option value="">-请选择-</option></select>
                                    <select id="cityId" class="pull-left" onchange="settle.init.initFormAddresses()"><option value="">-请选择-</option></select>
                                    <input type="hidden" name="area" />
                                </li>
                                <li>
                                    <span class="pull-left"><strong>*</strong>详细地址：</span>
                                    <input type="text" class="pull-left" name="detail">
                                </li>
                                <li>
                                    <span class="pull-left"></span>
                                    <label class="pull-left">
                                        <input type="checkbox" id="default">设为默认收货地址
                                        <input type="hidden" name="type">
                                    </label>
                                </li>
                                <li class="btn-box-01">
                                    <span class="pull-left"></span>
                                    <a class="pull-left btnn" id="saveAddBtn" onclick="settle.address.submitAddress()">保存收货地址</a>
                                </li>
                                <li class="btn-box-02">
                                    <span class="pull-left"></span>
                                    <a class="pull-left btnn btn-01">保存</a>
                                    <a class="pull-left btnn btn-02">取消</a>
                                </li>
                                <div style="clear: both;"></div>
                            </ul>
                        </form>
                    </div>
                </c:if>

            </div>

            <div class="cart-title" style="margin-top: 20px">
                <h1 class="pull-left">
                    确认订单明细
                </h1>
            </div>

            <div class="cart-head">
                <table width="0" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="top-none">
                        <th class="td-01"></th>
                        <th class="td-02">全部</th>
                        <th class="td-03">商品信息</th>
                        <th class="td-04">数量</th>
                        <th class="td-05">单价（元）</th>
                        <th class="td-06">小计</th>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="cart-list">
                <table width="0" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <c:forEach items="${carts}" var="cart">
                    <tr class="top-none">
                        <td class="td-01"></td>
                        <td class="td-02">
                            <a>
                                <img src="/resources/images/deals/${cart.deal.id}/1/good${cart.deal.id}.jpg" alt="${cart.deal.dealTitle}">
                            </a>
                        </td>
                        <td class="td-03">
                            <p>
                                <a>${cart.deal.dealTitle}</a>
                            </p>
                        </td>
                        <td class="td-04">
                            <div class="munber-box">
                                ${cart.cart.count}
                            </div>
                        </td>
                        <td class="td-05">
                            <p>￥<span>${cart.deal.dealPrice}</span></p>
                        </td>
                        <td class="td-06">
                            <strong>￥<em>${cart.subtotal}</em></strong><p></p>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="mab2">
                <div class="mab2">
                    <div class="infor-title">
                        <em class="icon-02"></em>配送方式
                    </div>
                    <dl class="infor-bd carts-distribution">
                        <dt class="pull-left distribution-box">
                            <a title="汽运" class="cur pull-left">汽运</a>
                            <a title="空运" class="pull-left">空运</a>
                        </dt>
                    </dl>
                </div>
            </div>

            <div class="mab2">
                <div class="mab2">
                    <div class="infor-title">
                        <em class="icon-03"></em>开票信息
                    </div>
                    <dl class="infor-bd carts-distribution">
                        <dt class="pull-left distribution-box">
                            <a title="不开票" class="cur pull-left">不开票</a>
                            <a title="开票" class="pull-left">开票</a>
                        </dt>
                    </dl>
                </div>
            </div>

            <div class="mab2">
                <div class="mab2">
                    <div class="infor-title">
                        <em class="icon-04"></em>支付方式
                    </div>
                    <dl class="infor-bd carts-distribution">
                        <dt class="pull-left distribution-box pay-type">
                            <a title="支付宝" class="cur pull-left" type="0">支付宝</a>
                            <a title="微信" class="pull-left" type="1">微信</a>
                        </dt>
                    </dl>
                </div>
            </div>


            <div class="cart-title" style="margin-top: 20px">
                <h1 class="pull-left">
                    结算信息
                </h1>
            </div>

            <form method="post" id="orderSubmit">
                <div class="carts-settlement mab2">
                    <div class="carts-settlement-b">
                        <ul class="s-b-r pull-right">
                            <li class="s-b-r-01 isDingJing">
                                <p>应付总额：
                                    <span>
                                        ￥<strong id="factPrice">${totalPrice}</strong>
                                        <input type="hidden" value="${totalPrice}" name="totalPrice">
                                        <input type="hidden" name="carts">
                                        <input type="hidden" name="addressId">
                                        <input id="pay-type" type="hidden" name="payType">
                                    </span>
                                </p>
                            </li>
                            <li class="s-b-r-02">可获得积分：<span id="sp_IntegralValue">${totalPrice}</span></li>
                            <li class="s-b-r-03">
                                <a title="确认提交" class="pull-right btn-01"  onclick="settle.order.initOrder()">确认提交</a>
                                <a title="返回进货单" class="pull-right btn-02" onclick="location.href='${ctx}/cart'">返回购物车</a>
                                <div class="clear"></div>
                            </li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                </div>
            </form>

            <div class="mab2">
                <dl class="car-reminder infor-bd">
                    <dt>温馨提示：</dt>
                    <dd>
                        <p>1.请您认真填写送达时间，订单提交后，如需变更送达时间，请至少提前5个工作日（以未变更前的送达时间为准）告知我们，如有特殊需求，请联系在线客服！</p>
                        <p>2.为了确保商品能够按时送达，请您务必在24小时内完成订单付款！</p>
                        <p>3.赠送数量以订单实际支付金额为准，活动商品不予以河币赠送。<a href="//www.Homedo.com/HelpCenter/HebiRule.shtml" title="了解详情" target="_blank" style="display: inline-block;">了解详情&gt;&gt;</a></p>
                        <p>4.大件商品配送至偏远地区，平台将收取超区服务费。商品发货前客服将跟您取得联系，谢谢！</p>
                    </dd>
                </dl>
            </div>



        </div>
    </div>


    <%@ include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">

    var settle = {

        constants:{
            addresses:"${addresses}",
            totalPrice:"${totalPrice}",
            carts:${cartsJson}
        },

        url:{
            queryAreas:"${ctx}/area/query/root",
            updateAdd:"${ctx}/user/address/update",
            deleteAdd:"${ctx}/user/address/delete",
            defaultAdd:"${ctx}/user/address/default",
            initAliPay:"${ctx}/alipay",
            initWeiPay:"${ctx}/weipay"
        },

        init:{
            /**
             * 获取省
             */
            initAddress:function(){
                if(settle.constants.addresses=="[]"){
                    $.get(settle.url.queryAreas,{"parentId":0},function (result) {
                        if(result.success){
                            var str = "";
                            for(var i=0;i<result.data.length;i++){
                                str += "<option value="+result.data[i].id+">"+result.data[i].name+"</option>";
                            }
                            $("#provinceId").empty().html(str);
                            settle.init.initFormAddresses();
                        }

                    })
                }
            },

            /**
             * 显示市
             */
            initCities:function () {
                var parentId = $("#provinceId option:selected").val();
                if(parentId){
                    $.get(settle.url.queryAreas,{"parentId":parentId},function (result) {
                        if(result.success){
                            var str = "";
                            for(var i=0;i<result.data.length;i++){
                                str += "<option value="+result.data[i].id+">"+result.data[i].name+"</option>";
                            }
                            $("#cityId").empty().html(str);
                            settle.init.initFormAddresses();
                        }

                    })
                }

            },

            /**
             * 生成提交表单用的地区数据
             */
            initFormAddresses:function () {
                var province = $("#provinceId option:selected").text();
                var city = $("#cityId option:selected").text();
                $("input[name='area']").val(province+city);
            }

        },

        address:{

            updateAddress:function () {
                //TODO 先弹出修改框，点击保存后执行ajax方法

                $.post(settle.url.updateAdd,"",function (result) {
                    if(result.success){
                        window.location.reload();
                    }
                })
            },

            deleteAddress:function (addId) {
                //TODO 删除前应该让用户确认是否删除
                $.post(settle.url.deleteAdd,{"addId":addId},function (result) {
                    if(result.success){
                        $("#oneAddress-"+addId).remove();
                        var ul = $(".adders-list");
                        if(ul.length==0){
                            window.location.reload();
                        }
                    }
                })
            },

            defaultAddress:function (addId) {
                $.post(settle.url.defaultAdd,{"addId":addId},function (result) {
                    if(result.success){
                        window.location.reload();
                    }
                })
            },

            submitAddress:function () {
                $("#saveAddBtn").click(function () {
                    var receiver = $("input[name='receiver']").val();
                    var phone = $("input[name='phone']").val();
                    var area = $("input[name='area']").val();
                    var detail = $("input[name='detail']").val();
                    var type = $("#default").prop("checked");
                    if(type){
                        $("input[name='type']").val(1);
                    }else{
                        $("input[name='type']").val(0);
                    }
                    if(receiver&&phone&&area&&detail){
                        //TODO 判断逻辑需要优化；如手机号要做验证，地址要做省市验证
                        $("#saveAdd").submit();
                    }else{
                        alert("请填写正确的收货地址");
                    }

                })
            }
        },


        order:{

            initOrder:function () {

                //组装cartsIds
                var carts = settle.constants.carts;
                if(!carts)return;
                $("input[name='carts']").val(JSON.stringify(carts));

                //组装总价
                var totalPrice = settle.constants.totalPrice;
                if(totalPrice != $("input[name='totalPrice']").val()){
                    alert("总价被篡改");
                }

                //支付方式 0：支付宝 1：微信;组装支付方式
                var payType = $(".pay-type .cur").attr("type");
                if(!payType)return;
                $("input[name='payType']").val(payType);

                //组装收获地址信息
                var addressId = ($(".adders-list.cur").attr("id").split("-"))[1];
                if(!addressId)return;
                $("input[name='addressId']").val(addressId);

                //cartIds,addressId,payType,totalPrice
                //确定支付方式url
                var url = (payType==0)?settle.url.initAliPay:settle.url.initWeiPay;
                $("#orderSubmit").attr("action",url).submit();

            }
        }

    }

    $(function(){

        //为选择地址绑定样式
        $(".adders-list").click(function () {
            $(".adders-list").removeClass("cur");
            $(".adders-list input[type='radio']").prop("checked",false);
            $(this).addClass("cur");
            $(this).find(" input[type='radio']").prop("checked",true);
        })

        //支付方式
        $(".pay-type a").click(function () {
            $(".pay-type a").removeClass("cur");
            $(this).addClass("cur");
            var type = $(this).attr("type");
            $("#pay-type").val(type);
        })

        //填充省
        settle.init.initAddress();
    })

</script>
</html>
