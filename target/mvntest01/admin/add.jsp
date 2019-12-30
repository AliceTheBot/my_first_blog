<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新的文章 | MyBlog</title>


<!-- Bootstrap core CSS -->
	<link	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<link href="../css2/login.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="../css2/add.css" />

<link rel="stylesheet" href="../editormd/css/editormd.css" />
<link href="../editormd/css/editormd.preview.css" rel="stylesheet">

</head>
<body>
	<div class="head_line"></div>
	<div class="container" id="main">		
				<div id="title"><h2><a href="../index.jsp">MyBlog</a></h2>
					</div>	
							
		   <form action="../NewArticleServlet" method="post">
		   		
		   		<div class="info" >
					<input type="hidden" class="form-control" name="id" value="0" >
		   		<!-- title -->
		   		<span class="help">标题</span>
		   		<input type="text" class="form-control" name="title" >
		   		<!-- time -->
		   		<span class="help">时间</span>
		   		<input type="text"  class="form-control" name="time" value="${requestScope.time}" >
		   		<!-- author-->
		   		<span class="help">作者</span>
		   		<input type="text" class="form-control" name="author" >	
		   		<!-- sort --> 				
		   		<span class="help">分类</span><br/>
 				<c:forEach var="s"  items="${requestScope.sort_count}">
 				<input class="btn btn-default" type="button" value="${s.key}" onclick="sort_click(this)"> &nbsp;					
 				</c:forEach> 	 			
 				<input type="text" class="form-control"  id="sort" name="sort">		
 				
				<!-- tag -->		
		   		<span class="help">标签</span><br/>
		   		<c:forEach var="tag" items="${requestScope.all_tag}">
		   		<input class="btn btn-default" type="button" value="${tag.tag}" onclick="tags_click(this)">&nbsp;
		   		</c:forEach>
		   		<input type="text" class="form-control" id="tags"  name="tags">	
		   		</div>   		
		   		
		   		
		   		<div class="foot_line"></div>
		   		<!-- content -->   
                <div class="editormd" id="mdView">                
                    <textarea name="mdView-markdown-doc" style="display:none;"></textarea>
                </div>
                <br/>
                <input  class="btn btn-default"  type="submit"   value="提交" />
            </form>
		
		<div class="foot_line"></div>
			<!-- container -->
		</div><!-- container -->

	<script src="../editormd/jquery.min.js" type="text/javascript"></script>

	<script src="../editormd/editormd.js" type="text/javascript"></script>
	<script src="../editormd/lib/marked.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/prettify.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/raphael.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/underscore.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/sequence-diagram.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/flowchart.min.js" type="text/javascript"></script>
	<script src="../editormd/lib/jquery.flowchart.min.js" type="text/javascript"></script>
	<script src="../js2/add.js" type="text/javascript" type="text/javascript"></script>

	<script type="text/javascript">
		var testEditor;
		$(function() {
					testEditor = editormd("mdView", {
						width  : "90%",
						height : 720,
						path   : '../editormd/lib/',
                        codeFold : true,
                        searchReplace : true,
                        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea                   
                        htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
                        emoji : true,
                        taskList : true,
                        tocm: true,      
                        tex : true,  
                        flowChart : true,  
                        sequenceDiagram : true,   
                        imageUpload : true,
				        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],				        
				        imageUploadURL : "../UploadPic",
				        //后台只需要返回一个 JSON 数据				      
						onload : function() {
							//console.log("onload =>", this, this.id, this.settings);
						}
					});				
					editor.setToolbarAutoFixed(false);//工具栏自动固定定位的开启与禁用               
            });
        </script>	
	
</body>
</html>