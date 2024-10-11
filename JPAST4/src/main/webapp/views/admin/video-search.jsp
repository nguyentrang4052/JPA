<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath }/admin/video/search" method="post">
<label for="title">Video title:</label><br>
 	<input type="text" id="title" name="title"><br> 
 	<input type="submit" value="Search">
</form>
	
