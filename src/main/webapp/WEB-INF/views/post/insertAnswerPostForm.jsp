<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/commonLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script>
$(document).ready(function() {
    var maxField = 5; //최대 개수
    extcnt = 1; // 최초 카운트 1
    
    $('#addBtn').click(function(){
        if(extcnt <= maxField){
		    var fieldHTML = '<input type="file" class="btn btn-default" id="fname'+extcnt+'" name="filename">';
	        $('#cnt').val(extcnt)
            extcnt++; // 숫자 증가
            $('#form1').append(fieldHTML); // Add field
        }
    });
	$('#sum_content').summernote({
		  height: 500,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
		  toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	});
});
</script>
</head>
<style>
	#writeBtn{
		float:right;
	}
</style>

<body>
<%@ include file="../layout/header.jsp" %>	
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="../layout/left.jsp" %>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2>${postVo.board_name }</h2>
			<h5>${postVo.post_title }의 답글</h5>
			<c:set var="cnt" value="1"/>
			<form id="form1" method="post" action="${cp }/post/insertAnswerPost" enctype="multipart/form-data">
				<input type="hidden" id="sum_parent" name="post_parent" value="${postVo.post_seq }">
				<input type="hidden" id="userid"     name="userid"      value="${postVo.userid }">
				<input type="hidden" id="board_name" name="board_name"  value="${postVo.board_name }">
		  제목 : <input type="text"   id="sum_title"  name="post_title"  value="" size="80" ><br><br>
  				<textarea id="sum_content" name="post_content"></textarea>
  				<input type="hidden" id="cnt" name="cnt">
		  		<input type="button" id="addBtn" value="첨부파일추가" class="btn btn-default">
  				<input type="submit" id="writeBtn" value="작성완료" class="btn btn-default"> 
			</form>
		</div>
	</div>
</div>
</body>
</html>
