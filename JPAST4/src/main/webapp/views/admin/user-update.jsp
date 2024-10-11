<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/user/update"
	method="post" enctype="multipart/form-data">
	
	<input type="text" id="userid" name="userid" hidden="hidden" value="${user.userid}"><br>
	 
	 <label for="name">Name:</label><br>
	 <input type="text" id="name" name="name" value="${user.name}"><br> 
	 
	 <label for="username">Username:</label><br>
	 <input type="text" id="username" name="username" value="${user.username}"><br> 
	 
	 <label for="password">Password:</label><br>
	 <input type="text" id="password" name="password" value="${user.password}"><br>
	 
	 <label for="images">Images:</label><br>
	<c:if test="${user.images.substring(0,5) != 'https' }">
		<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
	</c:if>

	<c:if test="${user.images.substring(0,5) == 'https' }">
		<c:url value="${user.images }" var="imgUrl"></c:url>
	</c:if>

	<img id="imagess" height="150" width="200" src="${imgUrl}" /> <input
		type="file" onchange="chooseFile(this)" id="images" name="images"
		value="${user.images}"><br>
		
		
	 <p>Active:</p>
	<input type = "radio" id="ston" name="active" value="1" checked>
	<label for="html">Đang hoạt động</label><br>
	<input type = "radio" id="stoff" name="active" value="0">
	<label for="css">Khoá</label><br> <input type="submit"
			value="update">

</form>