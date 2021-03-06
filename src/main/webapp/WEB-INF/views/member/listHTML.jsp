<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${memberList }" var="member">
	<%-- jstl로 선언하지않고 바로 for문  --%>
	<tr data-userid="${member.userid }">
		<td>${member.userid }</td>
		<td>${member.usernm }</td>
		<td>${member.alias }</td>
		<td><fmt:formatDate value="${member.reg_dt }"
				pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>

$$$SEPERATOR$$$

	<c:choose>
		<c:when test="${pageVo.page == 1 }">
			<li class="active"><span>&lt;&lt;</span></li>
		</c:when>
		<c:when test="${pageVo.page != 1 }">
			<li><a href="javascript:memberListHTML(1);">&lt;&lt;</a></li>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${pageVo.page == 1 }">
			<li class="active"><span>&lt;</span></li>
		</c:when>
		<c:when test="${page != 1 }">
			<li><a href="javascript:memberListHTML(${pageVo.page-1 });">&lt;</a></li>
		</c:when>
	</c:choose>

<c:forEach var="i" begin="1" end="${pages}">
	<c:choose>
		<c:when test="${i == pageVo.page}">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:memberListHTML(${i });">${i }</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>

	<c:choose>
		<c:when test="${pageVo.page == pages }">
			<li class="active"><span>&gt;</span></li>
		</c:when>
		<c:when test="${pageVo.page != pages }">
			<li><a href="javascript:memberListHTML(${pageVo.page+1 });">&gt;</a></li>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${pageVo.page == pages }">
			<li class="active"><span>&gt;&gt;</span></li>
		</c:when>
		<c:when test="${pageVo.page != pages }">
			<li><a href="javascript:memberListHTML(${pages });">&gt;&gt;</a></li>
		</c:when>
	</c:choose>
