<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${boardList }" var="board">
	<%-- jstl로 선언하지않고 바로 for문  --%>
	<tr data-board_name="${board.board_name }">
		<td>${board.board_name }</td>
		<td>
			<select name="use">
			<c:choose>
				<c:when test="${board.board_status == 1 }">
    					<option value="2">비활성화</option>
				</c:when>	 
				<c:when test="${board.board_status == 2 }">
    					<option value="1">활성화</option>
				</c:when>	 
			</c:choose>
			</select>
		</td>
		<td>
			<c:choose>
				<c:when test="${board.board_status == 1 }">
					<a href="${cp }/board/update?board_status=2&board_name=${board.board_name}" class="btn btn-default">수정</a><br>
				</c:when>
				<c:when test="${board.board_status == 2 }">
					<a href="${cp }/board/update?board_status=1&board_name=${board.board_name}" class="btn btn-default">수정</a><br>
				</c:when>
			</c:choose>
		</td>
	</tr>
</c:forEach>

===seperator===

<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<li class="active"><span>&lt;&lt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != 1 }">
		<li><a href="javascript:boardListHTML(${1 });">&lt;&lt;</a></li>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<li class="active"><span>&lt;</span></li>
	</c:when>
	<c:when test="${page != 1 }">
		<li><a href="javascript:boardListHTML(${1-1 });">&lt;</a></li>
	</c:when>
</c:choose>

<c:forEach var="i" begin="1" end="${pages}">
	<c:choose>
		<c:when test="${i == pageVo.page}">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:boardListHTML(${i });">${i }</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:choose>
	<c:when test="${pageVo.page == pages }">
		<li class="active"><span>&gt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != pages }">
		<li><a href="javascript:boardListHTML(${i+1 });">&gt;</a></li>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${pageVo.page == pages }">
		<li class="active"><span>&gt;&gt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != pages }">
		<li><a href="javascript:boardListHTML(${pages });">&gt;&gt;</a></li>
	</c:when>
</c:choose>