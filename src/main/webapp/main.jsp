<%--
  Created by IntelliJ IDEA.
  User: Auber
  Date: 2019/12/23
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MyBlog</title>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="css/main.css" rel="stylesheet">
    <link href="css/articleList.css" rel="stylesheet">
    <link href="css/side.css" rel="stylesheet">
</head>
<body>
        <!--引入头部-->
        <%@include file="head.html"%>

        <!--头图-->
        <header class="masthead">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-10 mx-auto">
                        <div class="site-heading">
                            <h1>一個部落格</h1>
                            <span class="subheading" id="gushici"></span>
                        </div>
                    </div>
                </div>
            </div>
        </header><!--头图结束-->

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-1"></div>
                <%--文章--%>
                <div class="col-lg-8">
                    <div class="blog-list">
                        <c:forEach var="art" items="${requestScope.article_pageList}">
                            <div class="blog-list-item clearfix">
                                <a href="article_detail?id=${art.id}" class="title">${art.title}</a>
<%--                                <div class="text">${art.content}</div><br/>--%>
                                <div class="info">
                                    <span class="avatar"></span>
                                    <span>${art.author}</span> |
                                    <span>${art.time}</span> |
                                    <span>阅读&nbsp;${art.visit}</span> |
                                    <span><i class="glyphicon glyphicon-star" aria-hidden="true"></i>
                                            ${art.star}
                                    </span> |
                                    <span><i class="glyphicon glyphicon-comment"  aria-hidden="true"></i>
                                            ${art.comment}
                                    </span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="row justify-content-center">
                        <div class="container-fluid">
                            <nav>
                                <div class="col-lg-5"></div>
                                <ul class="pagination pagination-lg col-lg-7">
                                    <c:set var="page" value="${requestScope.page}"/>
                                    <c:set var="totalPages" value="${sessionScope.totalPages}"/>
                                    <!--上一页-->
                                    <c:choose>
                                        <c:when test="${page==1}">
                                            <li class="disabled"><span>上一页</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="<c:url value="main_page?page=${page-1}"/>">上一页</a></li>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:if test="${page!=1}">
                                        <li>
                                            <a href="<c:url value="main_page?page=1"/>">1</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${page>2}">
                                        <li class="disabled">
                                            <span>...</span>
                                        </li>
                                    </c:if>

                                    <!--本页-->
                                    <li class="active">
                                        <a href="<c:url value="main_page?page=${page}"/>">${page}</a>
                                    </li>

                                    <c:if test="${totalPages-page>1}">
                                        <li class="disabled">
                                            <span>...</span>
                                        </li>
                                    </c:if>
                                    <c:if test="${page!=totalPages}">
                                        <li>
                                            <a href="<c:url value="main_page?page=${totalPages}"/>">${totalPages}</a>
                                        </li>
                                    </c:if>
                                    <!--下一页-->
                                    <c:choose>
                                        <c:when test="${page==totalPages}">
                                            <li class="disabled"><span>下一页</span></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a href="<c:url value="main_page?page=${page+1}"/>">下一页</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                        </div>
                    </div><!--分页 END-->

                </div><!--文章列表结束-->

                <%@ include file="_side.jsp"%>

            </div><!--Row END-->

        </div><!--流体容器结束-->

        <!--页尾-->
        <div class="modal-footer">
            <address class="text-center">
                <div><i class="glyphicon glyphicon-link"></i>已有${sessionScope.clicked}人拜訪此站</div>
                <div class="footer-brand">©王氏部落格作坊</div>
            </address>
        </div><!--页尾结束-->

        <script src="https://cdn.staticfile.org/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
