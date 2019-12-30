<%--
  Created by IntelliJ IDEA.
  User: Auber
  Date: 2019/12/29
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TAG</title>
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
        .sort{
            margin-bottom: 50px;
            margin-top: 30px;
        }
        .sort li{
            padding-left: 20px;
            font-size: 20px;
            padding-top: 10px;
        }
        .sort li a{
            text-decoration: none;
            color:#9d9d9d;
        }
        .sort li a:hover{color:black;}
        h2 span{
            padding: 0 5px;
        }
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
                    <h1 class="preface">你是什么标签丫</h1>
                </div>
             </div>
            </div>
       </div>
    </header><!--头图结束-->

    <div class="container-fluid">
        <div class="row">
            <!--分类-->
            <div class="col-lg-8 col-lg-offset-1">
                <div class="side-bar-tags">
                    <c:forEach var="t" items="${requestScope.tags}">
                        <div class="side-tag">
                            <a href="javascript:go_to_tag('${t.tag}')">${t.tag}</a>
                        </div>
                    </c:forEach>

                </div>

                <c:forEach var="item" items="${requestScope.tag_art}">
                    <h2 id="${item.key}"><span class="glyphicon glyphicon-tags"></span>${item.key}</h2>
                    <ul class="sort">
                        <c:forEach var="a" items="${item.value}">
                            <li><a href="article_detail?id=${a.id}">${a.title}</a></li>
                        </c:forEach>
                    </ul>
                    <hr>
                </c:forEach>
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
    <script type="text/javascript">
        var this_tag="${requestScope.this_tag}";
        if(this_tag.trim().length>0)
             document.getElementById(this_tag).scrollIntoView();

        function go_to_tag(tag){
            document.getElementById(tag).scrollIntoView();
        }
    </script>
</body>
</html>
