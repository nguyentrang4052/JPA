<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/user/insert"
	method="post" enctype="multipart/form-data">
   
   <label for="name">Name:</label><br>
	<input type="text" id="name" name="name" value=""><br> 
	
	<label for="username">Username:</label><br>
	<input type="text" id="username" name="username" value=""><br> 
	
	<label for="password">Password:</label><br>
	<input type="text" id="password" name="password" value=""><br> 
	
	<label for="images">Images:</label><br>
	<img id="imagess" height="150" width="200" src="" /> <input
		type="file" onchange="chooseFile(this)" id="images" name="images"
		value=""><br>
		
	 <p>Active:</p>
	<input type = "radio" id="ston" name="active" value="1" checked>
	<label for="html">Đang hoạt động</label><br>
	<input type = "radio" id="stoff" name="active" value="0">
	<label for="css">Khoá</label><br> <input type="submit"
			value="insert">

</form>
