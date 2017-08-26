<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp"%>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <%@ include file="common/head.jsp"%>

    <div class="banner">
        <div class="container">
            <img style="margin-left: 200px;" src="/resources/images/banner.jpg" height="320">
        </div>
    </div>

    <div class="all-categories">
        <div class="container">
            <c:forEach items="${indexCategoryDealDTOs}" var="dto" varStatus="st">
                <div class="row each-category each-category-${st.index+1}">
                    <div class="col-lg-12 category-good-head">
                        <h2 class="pull-left">
                            <span>${st.index+1}F</span>
                            <a href="${ctx}/category/${dto.category.urlName}">${dto.category.name}</a>
                        </h2>
                        <a href="${ctx}/category/${dto.category.urlName}" class="pull-right check-all check-${st.index+1}">查看全部</a>
                    </div>
                    <div class="col-lg-3 each-category-left category-${st.index+1}">
                        <a href="${ctx}/category/${dto.category.urlName}">
                            <h3>xx品牌</h3>
                            <img src="/resources/images/pic${st.index+1}.png">
                        </a>
                    </div>
                    <c:if test="${dto.deals!=null}">
                        <div class="col-lg-9 each-category-right row">
                            <c:forEach items="${dto.deals}" var="item">
                                <div class="category-good col-lg-3">
                                    <div class="category-good-top">
                                        <a href="${ctx}/item/${item.skuId}">
                                            <img src="/resources/images/deals/${item.id}/1/good${item.id}.jpg" width="140" height="140">
                                        </a>
                                    </div>
                                    <div class="category-good-bottom">
                                        <a href="${ctx}/item/${item.skuId}">
                                            <span>${item.dealTitle}</span>
                                        </a>
                                        <p class="price">
                                            ￥<span>${item.dealPrice}</span>
                                        </p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="common/footer.jsp"%>
</body>
<script type="text/javascript">
    $(function () {
       $(".menu-box").show();
    })
</script>
</html>
