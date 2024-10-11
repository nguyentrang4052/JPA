<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath }/admin/user/insert">Add
	User</a>

<table border="1" width=100%>
	<tr>
		<th>STT</th>
		<th>UserID</th>
		<th>Name</th>
		<th>Username</th>
		<th>Password</th>
		<th>Images</th>
		<th>Active</th>
		<th>Action</th>
	</tr>
		<c:forEach items="${listuser}" var="user" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>

				<td>${user.userid}</td>
				
				<td>${user.name }</td>
				
				<td>${user.username }</td>
				
				<td>${user.password }</td>
		
				<td><c:if test="${user.images.substring(0,5) != 'https' }">
						<c:url value="/image?fname=${user.images }" var="imgUrl"></c:url>
					</c:if> <c:if test="${user.images.substring(0,5) == 'https' }">
						<c:url value="${user.images }" var="imgUrl"></c:url>
					</c:if> <img height="150" width="200" src="${imgUrl}" /></td>

				

				<td><c:if test="${user.active == true }">
						<span>Hoạt động</span>
					</c:if> <c:if test="${user.active != true}">
						<span>Khoá</span>
					</c:if></td>

				<td><a
					href="<c:url value='/admin/user/update?id=${user.userid}'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/user/delete?id=${user.userid}'/>">Xóa</a></td>
			</tr>
		</c:forEach>
</table>
