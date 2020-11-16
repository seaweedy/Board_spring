<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

	$("#atchDownBtn").on("click",function(){
		document.location="/profileDownload?userid=${memberVo.userid}";
	})
    
	$('#sum_content').summernote({
		  height: 500,                 // 에디터 높이
		  minHeight: 500,             // 최소 높이
		  maxHeight: 500,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
		  toolbar: false,
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	});
    $('#sum_content').summernote('disable');
});
</script>
</head>
<style>
	#writeBtn{
		float:right;
	}
	label{
		margin :10px;
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
			<c:set var="cnt" value="1"/>
			<form id="postForm" method="post" action="${cp }/insertPost" enctype="multipart/form-data">
				<input type="hidden" id="sum_parent" name="post_parent" value="${postVo.post_parent }">
				<input type="hidden" id="board_name" name="board_name"  value="${postVo.board_name }">
		 		<label>제목 : ${postVo.post_title }</label><br>
		  		<label>작성자 : ${postVo.userid }</label><br>
		  		<label>작성일 : <fmt:formatDate value="${postVo.post_date }" pattern="yyyy-MM-dd"/></label>
		  		<a href="/insertAnswerPost?post_seq=${postVo.post_seq }&boardname=${postVo.board_name}&userid=${postVo.userid }&post_title=${postVo.post_title}" type="button" style="float:right"class="btn btn-default" >답글</a>
  				<c:choose>
					<c:when test="${S_MEMBER.userid == postVo.userid }">
		  				<a href="/deletePost?post_seq=${postVo.post_seq }&board_name=${postVo.board_name}" type="button" style="float:right"class="btn btn-default" >삭제</a>
		  				<a href="/post/updateView?post_seq=${postVo.post_seq }&userid=${postVo.userid }" type="button" style="float:right" class="btn btn-default" >수정</a><br><br>
					</c:when>  				
  				</c:choose>
		  		<input type="hidden"   id="userid"     name="userid"      value="${S_MEMBER.userid }" readonly="readonly"> <!-- 로그인한 사람의 아이디 -->
  				<textarea id="sum_content" name="post_content">${postVo.post_content }</textarea>
  				<input type="hidden" id="cnt" name="cnt">
  				<c:forEach items="${attachmentList }" var="attachment">
<%--   					<img alt="" src="${cp }/showAttachment?post_seq=${attachment.post_seq }"/> --%>
	  				<a href="/downAttachment?atc_seq=${attachment.atc_seq }" type="button" class="btn btn-default" >다운로드 : ${attachment.atc_rfname }</a><br><br>
  				</c:forEach>
			</form>
			<table class="table table-striped">
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${replyList }" var="reply">
					<c:choose>
						<c:when test="${reply.reply_status == 1 }">
							<tr>
								<td>${reply.userid }</td>
								<td>${reply.reply_content }</td>
								<td>
									<fmt:formatDate value="${reply.reply_date }"/>
									<c:choose>
										<c:when test="${userid == reply.userid }">
							  				<a href="/deleteReply?reply_seq=${reply.reply_seq}&userid=${postVo.userid}&post_seq=${reply.post_seq}" type="button" style="float:right"class="btn btn-default" >삭제</a>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:when>
						<c:when test="${reply.reply_status == 2 }">
							<tr>
								<td></td>
								<td>삭제된 댓글입니다.</td>
								<td></td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</table>
			<label style="vertical-align: middle;">아이디 : ${userid }</label>
			<form id="replyForm" method="post" action="${cp}/insertReply ">
		  		<input type="hidden"   id="userid"     name="userid"      value="${userid }" readonly="readonly"> <!-- 로그인한 사람의 아이디 -->
				<input type="hidden" id="post_seq" name="post_seq"  value="${postVo.post_seq }">
				<textarea  name="reply_content" style="width:1200px; height:80px; resize: none" placeholder="댓글을 입력해주세요"></textarea>
				<input type="submit" value="등록" class="btn btn-default" style="margin-bottom: 70px;height: 80px;" >
			</form>
		</div>
	</div>
</div>
</body>
</html>
