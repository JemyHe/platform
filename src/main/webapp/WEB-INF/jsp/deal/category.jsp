<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/tag.jsp"%>
<html>
<head>
    <title>分类页</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/category.css">
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
                    <a href="${ctx}/">${dealCategory.name}</a>
                </div>
            </div>
            <div class="bread-nav-right pull-right">
                共找到<span>${pagingDealList.total}</span>条相关产品
            </div>
        </div>
    </div>

    <c:if test="${pagingDealList.data!=null}">
        <div class="goods-list">
            <div class="container">
                <div class="row">
                    <c:forEach items="${pagingDealList.data}" var="item">
                        <div class="pro col-lg-2">
                            <div class="img">
                                <a href="${ctx}/item/${item.skuId}">
                                    <img src="/resources/images/deals/${item.id}/1/good${item.id}.jpg" width="200" height="200">
                                </a>
                            </div>
                            <div class="name">
                                <a href="${ctx}/item/${item.skuId}">
                                    ${item.dealTitle}
                                </a>
                            </div>
                            <div class="price">
                                <div class="nowprice pull-left">
                                    <span class="s1">￥</span>
                                    <span class="s2">${item.dealPrice}</span>
                                </div>
                                <div class="count pull-right">成交28件</div>
                            </div>
                            <div class="operate">
                                <a class="addcart" data-id="${item.skuId}">加入购物车</a>
                                <a href="" class="focusbtn">关注</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="page-nav">
            <div class="container">
                <div class="page-body">
                    <!--第一页，禁用“上一页”按钮-->
                    <c:if test="${pagingDealList.page <= 1 }">
                        <a href="" disabled="disabled" class="pre">
                            上一页
                        </a>
                    </c:if>
                    <c:if test="${pagingDealList.page == 2 }">
                        <a href="${ctx}/category/${dealCategory.urlName}" class="pre">
                            上一页
                        </a>
                    </c:if>
                    <c:if test="${pagingDealList.page > 2 }">
                        <a href="${ctx}/category/${dealCategory.urlName}/${pagingDealList.page-1}" class="pre">
                            上一页
                        </a>
                    </c:if>

                    <!--页码-->
                    <c:if test="${pagingDealList.totalPage==1}">
                        <a href="${ctx}/category/${dealCategory.urlName}"><span class="current">1</span></a>
                    </c:if>
                    <c:if test="${pagingDealList.totalPage>1}">
                        <c:forEach var="item" begin="1" end="${pagingDealList.totalPage}">
                            <c:if test="${pagingDealList.page == item}">
                                <a href="${ctx}/category/${dealCategory.urlName}/${item}"><span class="current">${item}</span></a>
                            </c:if>
                            <c:if test="${pagingDealList.page != item}">
                                <a href="${ctx}/category/${dealCategory.urlName}/${item}">${item}</a>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <!-- 下一页按钮 -->
                    <c:if test="${pagingDealList.page == pagingDealList.totalPage}">
                        <a href="" disabled="disabled" class="next">下一页</a>
                    </c:if>
                    <c:if test="${pagingDealList.page < pagingDealList.totalPage}">
                        <a href="${ctx}/category/${dealCategory.urlName}/${pagingDealList.page+1}" class="next">下一页</a>
                    </c:if>

                </div>
            </div>
        </div>
    </c:if>
    <%@ include file="../common/footer.jsp"%>
</body>
<script type="text/javascript">
    $(function () {
        $(".addcart").click(function () {
            var skuId = $(this).attr("data-id");
            $.post("${ctx}/cart/default/"+skuId+"/1",null,function (result) {
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
