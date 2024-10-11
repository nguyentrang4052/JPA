<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/video/update"
	method="post" enctype="multipart/form-data">

	<input type="text" id="videoid" name="videoid" hidden="hidden"
		value="${video.videoid}"><br> <label for="title">Title
		:</label><br> <input type="text" id="title" name="title"
		value="${video.title}"><br> <label for="poster">Poster:</label><br>

	<c:if test="${video.poster.substring(0,5) != 'https' }">
		<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
	</c:if>

	<c:if test="${video.poster.substring(0,5) == 'https' }">
		<c:url value="${video.poster}" var="imgUrl"></c:url>
	</c:if>

	<img id="imagess" height="150" width="200" src="${imgUrl}" /> <input
		type="file" onchange="chooseFile(this)" id="poster" name="poster"
		value="${video.poster}"><br> 
	
	<label for="description">Description:</label><br>
	<textarea id="description" name="description" rows="4" cols="25">
	 <c:out value="${video.description}" />
	 </textarea><br> 	
	
	<label for="views">Views:</label><br> 
	<input type="text" id="views" name="views" value="${video.views}"><br>
	
	<label for="categories">Category:</label><br>
    <select id="categories" name="categoryid" required>
        <c:forEach items="${listcate}" var="cate">
            <option value="${cate.categoryid}">${cate.categoryname}</option>
        </c:forEach>
    </select><br>
    
	<p>Active:</p>
	<input type="radio" id="ston" name="active" value="1" checked>
	<label for="html">Đang hoạt động</label><br> <input type="radio"
		id="stoff" name="active" value="0"> <label for="css">Khoá</label><br>
	<input type="submit" value="update">
</form>



