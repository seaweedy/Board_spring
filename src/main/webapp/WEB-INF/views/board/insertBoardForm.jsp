<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		boardListHTML(1);
		
		$('#editBtn').on('click',function(){
		});
		
	});

	function boardListHTML(p){
		$.ajax(
			{url:"/board/listHTML",
			data : {page : p, pageSize : 10},
			method : "get",
			success : function(data){
				var html = data.split("===seperator===");
				console.log(html[0]);
				console.log(html[1])
				$("#boardList").html(html[0]);
				$(".pagination").html(html[1]);
			}
		});
	}

	
</script>
<style>
	label{
		width:178px;
		height:26px;
		text-align:center;
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
			<h2>게시판 생성</h2>
			<div class="row">
				<div class="col-sm-8 blog-main">
					<div class="table-responsive">
						<hr>
						<form action="${cp }/board/insert" method="post">
							<label>게시판 이름 :</label>
							<input type="text" name="board_name">
							<select name="board_status">
    							<option value="1">활성화</option>
								<option value="2">비활성화</option>
							</select>
							<input type="submit" id="createBtn" value="생성" class="btn btn-default">
						</form>
						<hr>
						<table class="table table-striped">
							<tr>
								<th>게시판이름</th>
								<th>활성화상태</th>
								<th>수정</th>
							</tr>
							<tbody id="boardList">
							
							</tbody>
						</table>
					</div>
					
					<div class="text-center">
						<ul class="pagination">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
