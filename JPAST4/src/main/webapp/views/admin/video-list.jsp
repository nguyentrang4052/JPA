<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath }/admin/video/insert">Add
	Video</a><br>
<a href="${pageContext.request.contextPath }/admin/video/search">Search
	Video</a>
<table border="1" width=100%>
	<tr>
		<th>STT</th>
		<th>VideoID</th>
		<th>Video title</th>
		<th>Poster</th>
		<th>Description</th>
		<th>Category</th>
		<th>Views</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
	<tr>
	<c:if test="${alert != null}">
			<h3 class="alert-alertdanger">${alert}</h3>
		</c:if>
		<c:forEach items="${listvideo}" var="video" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>

				<td>${video.videoid}</td>

				<td>${video.title}</td>

				<td><c:if test="${video.poster.substring(0,5) != 'https' }">
						<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
					</c:if> <c:if test="${video.poster.substring(0,5) == 'https' }">
						<c:url value="${video.poster}" var="imgUrl"></c:url>
					</c:if> <img height="150" width="200" src="${imgUrl}" /></td>

				<td>${video.description}</td>

				<td>${video.categories.categoryname}</td>

				<td>${video.views}</td>

				<td><c:if test="${video.active == true}">
						<span>Hoạt động</span>
					</c:if> <c:if test="${video.active != true}">
						<span>Khoá</span>
					</c:if></td>

				<td><a
					href="<c:url value='/admin/video/update?id=${video.videoid }'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/video/delete?id=${video.videoid }'/>">Xóa</a></td>

			</tr>
		</c:forEach>
	</tr>
</table>