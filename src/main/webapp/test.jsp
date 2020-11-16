<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
	<form id="writeForm"method="post" action="${cp }/insertPost">
		<input type="text" id="board_name" name="board_name">
		<input type="text" id="userid" name="userid">
		<input type="text" id="post_title" name="post_title">
		<input type="text" id="post_content" name="post_content">
		<input type="text" id="post_parent" name="post_parent">
		<input type="submit" >
	</form>
</body>
</html>