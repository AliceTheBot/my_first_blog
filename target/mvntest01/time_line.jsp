<%--
  Created by IntelliJ IDEA.
  User: Auber
  Date: 2019/12/29
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>时间线</title>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="css/main.css" rel="stylesheet">
    <link href="css/side.css" rel="stylesheet">
    <style type="text/css">
        .site-heading{
            height:50px;
        }
        .site-heading .preface{
            font-size:50px !important;
        }
        .year{
            margin-bottom: 30px;
            margin-top: 30px;
        }
        .year li{
            padding-left: 20px;
            font-size: 20px;
            padding-top: 10px;
        }
        .year li a{
            text-decoration: none;
            color:#9d9d9d;
        }
        .year li a:hover{color:black;}
    </style>
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
                        <h1 class="preface">命运女神手中的丝线</h1>
                    </div>
                </div>
            </div>
        </div>
    </header><!--头图结束-->

    <div class="container-fluid">
        <!--时间线-->
        <div class="row">
            <div  class="col-lg-8 col-lg-offset-1">
                <c:forEach var="a" items="${requestScope.axis_arts}" varStatus="i">
                    <c:choose>
                        <c:when test="${a.id==0}">
                            <c:if test="${i.index!=0}"></ul></c:if>
                            <h2><span class="glyphicon glyphicon-map-marker"></span>${a.year}</h2>
                            <ul class="year">
                        </c:when>
                        <c:otherwise>
                            <li>
                                <span>${a.month}.${a.day}</span>
                                <a href="article_detail?id=${a.id}">${a.title}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                            </ul>
            </div>

            <!--引进侧边栏-->
            <%@ include file="_side.jsp"%>

        </div><!--Row END-->
    </div><!--容器结束-->

    <!--页尾-->
    <div class="modal-footer">
        <address class="text-center">
            <div><i class="glyphicon glyphicon-link"></i>已有${sessionScope.clicked}人拜訪此站</div>
            <div class="footer-brand">©王氏部落格作坊</div>
        </address>
    </div><!--页尾结束-->

    <script src="editormd/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
