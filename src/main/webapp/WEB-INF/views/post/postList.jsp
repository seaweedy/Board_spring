<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		postListHTML(1);
		
		$('#writeBtn').on('click',function(){
			document.location = "/post/insertPostView?board_name=${board_name }&userid=${S_MEMBER.userid}";
		});

		$(document).on('click','#postList #post',function(){
			var post_seq = $(this).data('post_seq');
			console.log(post_seq);
			document.location="/post/selectView?post_seq="+post_seq+"&userid=${S_MEMBER.userid}";
		})
	});

	function postListHTML(p){
		$.ajax(
				{url:"/post/list",
				data : {page : p, pageSize : 10, board_name : "${board_name}", pages :"${pageVo}" },
				method:"get",
				success : function(data){
					var html = data.split("===seperator===");
					$("#postList").html(html[0]);
					$(".pagination").html(html[1]);
				}
		})
	}
</script>
<style>
	#writeBtn{
		float:right;
	}
</style>
<title>Jsp</title>
<%@ include file="../layout/commonLib.jsp" %>
</head>

<body>
<%@ include file="../layout/header.jsp" %>	
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="../layout/left.jsp" %>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2>${board_name }</h2>
				<div class="row">
					<div class="col-sm-8 blog-main">
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호	</th>
									<th>제목</th>
									<th>작성자아이디</th>
									<th>작성일시</th>
								</tr>
								<tbody id="postList">
								
								</tbody>
							</table>
							<input type="button" id="writeBtn" value="글작성"
								class="btn btn-default">
							<div class="text-center">
								<ul class="pagination">
								
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>
</body>
</html>
