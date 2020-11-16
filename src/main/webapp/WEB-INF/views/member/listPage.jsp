<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	// 해당 html이 로딩이 완료 되었을 대 실행되는 이벤트 핸들러 함수
	$(document).ready(function() {
// 		memberList(1);
		memberListHTML(1);
		
		$('#memberList').on('click',"tr", function() {
			// data-userid
			var userid = $(this).data("userid");
			console.log(userid);
			document.location = "/member/get?userid=" + userid;
		});
		
	});
	
	function memberList(p){
		// ajax call을 통해 1페이지에 해당하는 사용자 정보를 json으로 받는다
		$.ajax(
				{url: "/member/list",
				data : {page : p, pageSize : 5},
// 				data : "page=1&pageSize=5",
// 				data : JSON.stringify({page : 1, pageSize : 5}),
//				Controller에서 @RequestBody를 붙여야함   JSON <=> JAVA OBJECT
				method : "get",
				success : function(data){

					// memberList tbody 영역에 들어갈 html 문자열 생성
					var html ="";
					for(var i = 0; i < data.memberList.length; i++){
						var member = data.memberList[i];
						html += "<tr data-userid='"+member.userid+"'>";
						html += "<td>" +member.userid +  "</td>";
						html += "<td>" +member.usernm +  "</td>";
						html += "<td>" +member.alias +  "</td>";
						html += "<td>" +member.fmt_reg_dt +  "</td>";
						html += "</tr>";
					}
					$('#memberList').html(html);

					//페이지 네비게이션 html 문자열 동적으로 생성하기
					html= "";
					for(var i = 1; i < data.pages; i++){
						if(data.pageVo.page == i ){
							html += "<li class='active'><span>"+i+"</span></li>";
						}else{
							console.log(data.pages);
							 // <a href = "javascript:memberListAjax(1);"/>
							html += "<li><a href=\"javascript:memberListAjax("+i+");\"/ >"+i+"</a></li>";
							
						}
					}
					$(".pagination").html(html);

				}
				
		});	
	}

	function memberListHTML(p){
		// ajax call을 통해 1페이지에 해당하는 사용자 정보를 json으로 받는다
		$.ajax(
				{url: "/member/listHTML",
				data : {page : p, pageSize : 5},
// 				data : "page=1&pageSize=5",
// 				data : JSON.stringify({page : 1, pageSize : 5}),
//				Controller에서 @RequestBody를 붙여야함   JSON <=> JAVA OBJECT
				method : "get",
				success : function(data){
					var html = data.split("$$$SEPERATOR$$$");
					$('#memberList').html(html[0]);
					$(".pagination").html(html[1]);
			}
		});	
	}
</script>
<div class="row">
	ajax : listPage.jsp
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				<tbody id="memberList">
				
				</tbody>
			</table>
		</div>

		<a class="btn btn-default pull-right" href="${cp }/member/registform">사용자
			등록</a>
		<div class="text-center">
			<ul class="pagination">
			
			</ul>
		</div>
	</div>
</div>
</html>
