<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/commonLib.jsp"%>

<script>
	$(document).ready(function(){
	    $('#zipcodeBtn').on('click',function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
			        console.log(data);
			        $("#addr1").val(data.roadAddress);
			        $("#zipcode").val(data.zonecode);
			        console.log(data.roadAddress);
			        console.log(data.zonecode);
		        }
		    }).open();
    	});
    	$('#regBtn').on('click',function(){
        	$('#frm').submit();
        });
	});
</script>
<div class="container-fluid">
	<div class="row">
		updateMemberFormContent.jsp
		<div class="col-sm-8 blog-main">
			<form id="frm" class="form-horizontal" role="form"
				action="${cp }/member/update" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
					<div class="col-sm-10">
						<img alt="" src="${cp }/profileImg?userid=${memberVo.userid }" /><br>
						<input type="hidden" name="filename" value="${memberVo.filename }" />
						<input type="hidden" name="realfilename"
							value="${memberVo.realfilename }" /> <input type="file"
							name="file" />
					</div>
				</div>

				<div class="form-group">
					<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
					<div class="col-sm-10">
						<label class="form-control" id="userid">${memberVo.userid }</label>
						<input type="hidden" name="userid" value="${memberVo.userid }">
					</div>
				</div>

				<div class="form-group">
					<label for="usernm" class="col-sm-2 control-label">사용자 이름</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="usernm" name="usernm"
							placeholder="사용자 이름" value="${memberVo.usernm }">
					</div>
				</div>
				<div class="form-group">
					<label for="alias" class="col-sm-2 control-label">별명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="alias" name="alias"
							placeholder="사용자 별명" value="${memberVo.alias }">
					</div>
				</div>
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pass" name="pass"
							placeholder="사용자 비밀번호" value="${memberVo.pass }">
					</div>
				</div>

				<div class="form-group">
					<label for="addr1" class="col-sm-2 control-label">주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="addr1" name="addr1"
							placeholder="사용자 주소" READONLY value="${memberVo.addr1 }"><br>
						<input type="button" id="zipcodeBtn" class="btn btn-default"
							value="우편번호 찾기">
					</div>
				</div>

				<div class="form-group">
					<label for="addr1" class="col-sm-2 control-label">상세주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="addr2" name="addr2"
							placeholder="사용자 상세주소" value="${memberVo.addr2 }">
					</div>
				</div>

				<div class="form-group">
					<label for="addr1" class="col-sm-2 control-label">우편번호</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="zipcode"
							name="zipcode" placeholder="사용자 우편번호" READONLY
							value="${memberVo.zipcode }">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="regBtn" type="button" class="btn btn-default">사용자
							수정</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</html>
