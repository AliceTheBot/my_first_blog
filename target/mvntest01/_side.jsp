<%--
  Created by IntelliJ IDEA.
  User: Auber
  Date: 2019/12/25
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--侧边栏--%>
<div class="col-lg-2">
    <%--标签--%>
    <div class="side-bar-card side-bar-recommend clearfix">
        <div class="side-bar-title">标签</div>
        <div class="side-bar-body">
            <div class="side-bar-tags">
                <c:forEach var="tag" items="${sessionScope.tag_list}">
                    <a href="tag?tag=${tag.tag}" class="side-tag">${tag.tag}</a>
                </c:forEach>
            </div>
        </div>
    </div><!--标签结束-->

    <!--热门文章-->
    <div class="side-bar-card side-bar-recommend clearfix">
        <div class="side-bar-title">推荐阅读</div>
        <div class="side-bar-body">
            <div class="side-bar-list">
                <c:forEach var="art" items="${sessionScope.visit_rank}">
                    <div class="side-bar-item">
                        <a href="article_detail?id=${art.id}" class="side-item-title">${art.title}</a>
                        <div class="side-item-info">${art.visit}阅读 | ${art.time}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div><!--Rank结束-->

    <%--目录--%>
    <div class="side-bar-card side-bar-recommend clearfix">
        <div class="side-bar-title">目录</div>
        <div class="side-bar-body">
            <div class="side-bar-list">
                <c:forEach var="sort" items="${sessionScope.sort_count_map}">
                    <div class="side-bar-item">
                        <a href="sort?sort=${sort.key}" class="side-item-title">${sort.key}(${sort.value})</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div><!--目录结束-->

</div><!--Col END-->

<!--登录相关-->
<input type="hidden" id="username" value="${sessionScope.userName}">
<input type="hidden" id="power" value="${sessionScope.power}">
<script type="text/javascript">
    //用户信息
    var username=$("#username").val();
    var power=$("#power").val();
    if(username!=null && username.trim().length>0){
        if(power==='1')
            $("#head-login").empty().append("<a href='AdminServlet' title='后台'>"+username+"</a>");
        else
            $("#head-login").empty().append("<a href='login.html' title='退出登录'>"+username+"</a>");
    }
</script>
</body>
</html>
