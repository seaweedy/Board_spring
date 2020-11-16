<%@page import="kr.or.ddit.member.model.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				

			<div class="blog-header">
				<h1 class="blog-title">Main</h1>
				<p class="lead blog-description">Jsp / Spring.</p>
			</div>

			<div class="row">

				<div class="col-sm-8 blog-main">

					<div class="blog-post">
						<h2 class="blog-post-title">Main페이지</h2>
						</p>
						<hr>
			
					</div>
				</div>
			</div>	
		</div>
	</div>
</div>
</body>
</html>
