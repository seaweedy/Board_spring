<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="blank" value =" " scope="page"/>
<c:choose>
	<c:when test="${postList.size() == 0}">
		<tr>
			<td>작성글이</td>
			<td>없습니다.</td>
			<td>첫글을</td>
			<td>작성하시겠어요?</td>
		</tr>
	</c:when>
	<c:when test="${postList.size() != 0}">
		<c:forEach items="${postList }" var="post">
			<c:choose>
				<c:when test="${post.post_status == 1 }">
					<tr id="post" data-post_seq="${post.post_seq }">
						<td>${post.post_seq }</td>
						<td>${fn:replace(post.post_title, blank, "&nbsp;") }</td>
						<td>${post.userid }</td>
						<td><fmt:formatDate value="${post.post_date }"
								pattern="YYYY-MM-dd" /></td>
					</tr>
				</c:when>
				<c:when test="${post.post_status == 2 }">
					<tr data-post_seq="${post.post_seq }">
						<td>${post.post_seq }</td>
						<td colspan="2" style="text-align: center">삭제된 글입니다.</td>
						<td></td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</c:when>
</c:choose>

===seperator===

<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<li class="active"><span>&lt;&lt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != 1 }">
		<li><a href="javascript:postListHTML(${1 });">&lt;&lt;</a></li>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<li class="active"><span>&lt;</span></li>
	</c:when>
	<c:when test="${page != 1 }">
		<li><a href="javascript:postListHTML(${1-1 });">&lt;</a></li>
	</c:when>
</c:choose>


<c:forEach var="i" begin="1" end="${pages}">
	<c:choose>
		<c:when test="${i == pageVo.page}">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="${cp }/selectAllPost?page=${i}&boardname=${boardname}">${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>


<c:choose>
	<c:when test="${pageVo.page == pages }">
		<li class="active"><span>&gt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != pages }">
		<li><a href="javascript:postListHTML(${i+1 });">&gt;</a></li>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${pageVo.page == pages }">
		<li class="active"><span>&gt;&gt;</span></li>
	</c:when>
	<c:when test="${pageVo.page != pages }">
		<li><a href="javascript:postListHTML(${pages });">&gt;&gt;</a></li>
	</c:when>
</c:choose>