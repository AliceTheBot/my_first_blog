<%--
  Created by IntelliJ IDEA.
  User: Auber
  Date: 2019/12/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.art.title}</title>
    <meta http-equiv="Content-Type" content="text/html charset=utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="editormd/css/editormd.css" rel="stylesheet">
    <link href="editormd/css/editormd.preview.css" rel="stylesheet">

    <link href="css/main.css" rel="stylesheet">
    <link href="css/articleList.css" rel="stylesheet">
    <link href="css/side.css" rel="stylesheet">
    <link href="css/articleDetail.css" rel="stylesheet">
    <style type="text/css">
        .img{
            height:50px;
            width:50px;
            vertical-align: middle;
            margin-right: 26px;
            border-radius: 50%;
            float:left;
        }
        .comment-h2,.comment-h3{
            display: block;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 23px;
        }
        .comment_box{
            padding: 66px;
        }
        .comment{
            padding-top: 30px;
            padding-bottom: 30px;
        }
        .comment-info{
            padding-top: 10px;
            margin-bottom: 22px;
            color:rgba(34,49,63,0.7);
            font-size:14px;
        }
        li{
            display: block;
            margin:1px 0;
        }
        .comment-content{
            padding-top: 17px;
            font-size: 17px;
        }
        .comment-star{
            margin-top: 28px;
        }
        form{
            padding-top: 8px;
            color:rgba(34,49,63,0.7);
            font-size: 14px;
        }
        .btn-lg{
            width: 100%;
            margin-top: 20px;
            color:rgba(34,49,63,0.5);
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
                        <div class="head-bar-tags">
                            <c:forEach var="tag" items="${requestScope.this_tags}">
                                <a href="tag?tag=${tag.tag}" class="side-tag">${tag.tag}</a>
                            </c:forEach>
                        </div>
                        <h1 class="art-title">${requestScope.this_article.title}</h1>
                        <span class="subheading art-info">
                            <span>${requestScope.this_article.author}</span>
                            <span>${requestScope.this_article.time}</span>|
                            <span>阅读&nbsp;${requestScope.this_article.visit}</span> |
                            <span><i class="glyphicon glyphicon-comment"  aria-hidden="true"></i>${requestScope.this_article.comment}</span>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </header><!--头图结束-->

    <div class="container-fluid">
        <!--文章详情-->
        <div class="row">
            <div  class="col-lg-8 col-lg-offset-1">
                <div id="editormd-view">
                    <textarea style="display:none;">${requestScope.this_article.content }</textarea>
                </div>
            </div>

            <!--引进侧边栏-->
            <%@ include file="_side.jsp"%>

        </div><!--Row END-->

        <input type="hidden" id="art_id" value="${requestScope.this_article.id}">

        <!--爱的点赞-->
        <div class="row">
            <div class="col-lg-1 col-lg-offset-5">
                <span class="like">
                    <button id="btn_like" onclick="like_art()"><i id="thumb" class="glyphicon glyphicon-thumbs-up"></i><span id="like_num">${requestScope.this_article.star}</span></button>
                </span>
            </div>
        </div>

        <div class="row"><div class="col-lg-8 col-lg-offset-1"><hr></div></div>

        <!--相邻文章-->
        <div class="row">
            <div class="col-lg-offset-1 col-lg-3">
                <div class="pre-next-art">
                    <c:choose>
                        <c:when test="${requestScope.next_article==null}">
                        </c:when>
                        <c:otherwise>
                            <a href="article_detail?id=${requestScope.next_article.id}"><span class="glyphicon glyphicon-chevron-left"></span>${requestScope.next_article.title}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-lg-offset-3 col-lg-3">
                <div class="pre-next-art">
                    <c:choose>
                        <c:when test="${requestScope.pre_article==null}">
                        </c:when>
                        <c:otherwise>
                            <a href="article_detail?id=${requestScope.pre_article.id}">${requestScope.pre_article.title}<span class="glyphicon glyphicon-chevron-right"></span></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div><!--相邻文章结束-->

        <!--评论-->
        <div class="row">
            <div class="comment_box col-lg-8 col-lg-offset-1">
                <h2 class="comment-h2">《${requestScope.this_article.title}》上有${requestScope.this_article.comment}条评论</h2>

                <!--评论开始-->
                <ul id="ul_box">
                    <c:forEach var="c" items="${requestScope.this_comment}" varStatus="i">
                        <li id="comment" class="comment">
                            <div>
                                <img class="img" src="https://api.uomg.com/api/rand.avatar?sort=动漫男&format=images" alt="头像">
                                <div><b>${c.nickname}</b></div>
                                <div class="comment-info"><time>${c.time}</time></div>
                            </div>
                            <div class="comment-content"><p>${c.content}</p></div>
                            <div class="comment-star">
                                <button type="button" class="btn btn-default" onclick="star(this,${c.id})" name="star"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>${c.star}</button>
                                <button type="button" class="btn btn-default" onclick="diss(this,${c.id})" name="diss"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>${c.diss}</button>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <hr>

                <c:choose>
                    <c:when test="${sessionScope.userName!=null}">
                        <!--发表评论-->
                        <h3 class="comment-h3">发表评论</h3>
                        <form id="form1">
                            <div class="form-group">
                                <label for="nickname">昵称</label>
                                <input type="text" class="form-control" id="nickname" name="nickname" value="${sessionScope.userName}">
                            </div>
                            <div class="form-group">
                                <label for="content">内容</label>
                                <textarea type="text" class="form-control" id="content" name="content"></textarea>
                            </div>
                            <button type="submit" onclick="return add_comment()" class="btn btn-info btn-lg">评论</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h3 class="comment-h3"><a href="login.html">登录</a>后才可以发表评论哦</h3>
                    </c:otherwise>
                </c:choose>

            </div><!--行结束-->
        </div><!--评论row结束-->

    </div><!--流体容器结束-->

    <!--页尾-->
    <div class="modal-footer">
        <address class="text-center">
            <div><i class="glyphicon glyphicon-link"></i>已有${sessionScope.clicked}人拜訪此站</div>
            <div class="footer-brand">©王氏部落格作坊</div>
        </address>
    </div><!--页尾结束-->

    <script src="editormd/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

    <script src="editormd/lib/marked.min.js"></script>
    <script src="editormd/lib/prettify.min.js"></script>

    <script src="editormd/lib/raphael.min.js"></script>
    <script src="editormd/lib/underscore.min.js"></script>
    <script src="editormd/lib/sequence-diagram.min.js"></script>
    <script src="editormd/lib/flowchart.min.js"></script>
    <script src="editormd/lib/jquery.flowchart.min.js"></script>

    <script src="editormd/editormd.js"></script>

    <script src="js/main.js"></script>
    <script type="text/javascript">
            $(".masthead").css("height","350px");
            $(".site-heading").css("padding-top","100px");


            var content=editormd.markdownToHTML("editormd-view",{
                htmlDecode      : "style,script,iframe",  // you can filter tags decode
                emoji           : true,
                taskList        : true,
                tex             : true,  // 默认不解析
                flowChart       : true,  // 默认不解析
                sequenceDiagram : true  // 默认不解析
            });

            /**
             * 加载文章时判断是否已点赞
             */
            var id=$("#art_id").val();
            var flag=false;
            if($.cookie("art"+id+"like")==="true"){
                $("#btn_like").css("background-color",color[n]).css("color","#fff");
                $("#thumb").css("color","#fff");
                flag=true;
            }

            /**
             *  点赞文章
             */
            function like_art() {
                $.get("like?type=art&id="+id+"&flag="+flag,function(data){
                    var a=data.split('\n');
                    var num=a[1];
                    if(a[0]==="success"){
                        flag=!flag;
                        if(flag) {
                            $("#btn_like").css("background-color", color[n]).css("color", "#fff");
                            $("#thumb").css("color","#fff");
                            $("#like_num").text(num);
                            $.cookie("art"+id+"like","true");
                        }else{
                            $("#btn_like").css("background-color","#fff").css("color",color[n]);
                            $("#thumb").css("color",color[n]);
                            $("#like_num").text(num);
                            $.cookie("art"+id+"like","false");
                        }
                    }
                });
            }

            /**
             * star 评论
             */
            function star(component , comm_id) {
                $.get("like?id="+comm_id+"&op=star&type=comment",function (data) {
                    data=$.parseJSON(data);
                        if (data.msg === "success") {
                            //返回 ”success“
                            component.innerHTML = "<span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span>"+data.new_num;
                        }else{
                            alert("你已经评价过这条评论了!");
                        }
                });
            }

            /**
             * diss 评论
             */
            function diss(component , comm_id) {
                $.get("like?id="+comm_id+"&op=diss&type=comment",function (data) {
                    data=$.parseJSON(data);
                    if (data.msg === "success") {
                        //返回 ”success“
                        component.innerHTML = "<span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span>"+data.new_num;
                    }else{
                        alert("你已经评价过这条评论了!");
                    }
                });
            }

            /**
             * 提交评论
             */
            function add_comment() {
                if (!check_null()){return false;}
                $.get("add_comment?aid="+${requestScope.this_article.id},$("#form1").serialize(),function(data){
                    data=$.parseJSON(data);
                    if(data.msg==="success"){
                        var li=$("<li id='comment' class='comment'><div>" +
                            "<img class='img' src='https://api.uomg.com/api/rand.avatar?sort=动漫男&format=images' alt='头像'><div><b>" +
                            data.nickname+"</b></div><div class='comment-info'><time>" +
                            data.time+"</time></div></div><div class='comment-content'><p>"+
                            data.content+"</p></div><div class='comment-star'><button type='button' class='btn btn-default' onclick='star(this,"+
                            data.id+")' name='star'><span class='glyphicon glyphicon-thumbs-up' aria-hidden='true'></span>0</button>" +
                            "<button type='button' class='btn btn-default' onclick='diss(this,"+
                            data.id+")' name='diss'><span class='glyphicon glyphicon-thumbs-down' aria-hidden='true'></span>0</button></div></li>");
                        $("#ul_box").append(li);
                        alert("评论发表成功(/≧▽≦)/");
                    }else{
                        alert("评论发表失败/(ㄒoㄒ)/~~");
                    }
                });
                $('#nickname').val("");
                $('#content').val("");
                return false;
            }

            /**
            *  判断评论表单是否为空
            */
            function check_null() {
                var n=$('#nickname').val();
                var c=$('#content').val();

                if(n.trim().length===0) {
                    $("#nickname").attr("placeholder", "请输入昵称！").focus();
                    return false;
                } else if(c.trim().length===0) {
                    $("#content").attr("placeholder", "请输入内容！").focus();
                    return false;
                }
                return true;
            }
    </script>
</body>
</html>
