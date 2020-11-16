<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-sidebar">
	<li class="active"><a href="${cp }/main.jsp">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="${cp }/member/listPage">사용자(Ajax)</a></li>
	<li class="active"><a href="${cp }/board/insertView">게시판생성</a></li>
	<c:forEach items="${activeBoardList }" var="activeBoard">
		<li class="active"><a href="/post/listView?board_name=${activeBoard.board_name}">${activeBoard.board_name }</a></li>
	</c:forEach>
	
</ul>