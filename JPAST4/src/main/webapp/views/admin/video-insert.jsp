<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/video/insert"
	method="post" enctype="multipart/form-data">
	
	<label for="title">Video title:</label><br>
 	<input type="text" id="title" name="title" value=""><br> 
 	<label for="poster">Poster:</label><br>
	<img id="imagess" height="150" width="200" src="" /> <input
		type="file" onchange="chooseFile(this)" id="poster" name="poster"
		value=""><br>
		
	<label for="description">Description:</label><br>
	<textarea id="description" name="description" rows="4" cols="25"></textarea><br> 

	<label for="views">Views:</label><br>
	<input type="text" id="views" name="views" value=""><br> 
	
	<label for="categories">Category:</label><br>
    <select id="categories" name="categoryid" required>
        <c:forEach items="${listcate}" var="cate">
            <option value="${cate.categoryid}">${cate.categoryname}</option>
        </c:forEach>
    </select><br>
    
    <p>Active:</p>
	<input type = "radio" id="ston" name="active" value="1" checked>
	<label for="html">Đang hoạt động</label><br>
	<input type = "radio" id="stoff" name="active" value="0">
	<label for="css">Khoá</label><br> <input type="submit"
			value="insert">

</form>