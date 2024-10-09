<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath }/admin/category/insert">Add
	Category</a>

<table border="1" width=100%>
	<tr>
		<th>STT</th>
		<th>CategoryID</th>
		<th>Images</th>
		<th>CategoryName</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
	<tr>
		<c:forEach items="${listcate}" var="cate" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>

				<td>${cate.categoryid}</td>

				<td><c:if test="${cate.images.substring(0,5) != 'https' }">
						<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
					</c:if> <c:if test="${cate.images.substring(0,5) == 'https' }">
						<c:url value="${cate.images }" var="imgUrl"></c:url>
					</c:if> <img height="150" width="200" src="${imgUrl}" /></td>

				<td>${cate.categoryname }</td>

				<td><c:if test="${cate.status == 1 }">
						<span>Hoạt động</span>
					</c:if> <c:if test="${cate.status != 1}">
						<span>Khoá</span>
					</c:if></td>

				<td><a
					href="<c:url value='/admin/category/update?id=${cate.categoryid }'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/category/delete?id=${cate.categoryid }'/>">Xóa</a></td>
			</tr>
		</c:forEach>
	</tr>
</table>
