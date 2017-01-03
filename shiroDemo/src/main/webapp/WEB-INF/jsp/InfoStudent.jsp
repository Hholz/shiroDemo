<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">学生信息修改</h3>
	<form action="${pageContext.request.contextPath}/student/${student.id}" method="post">
	<input type="hidden" name="_method" value="PUT"/>
	<table align="center" width="65%">
		<tr>
			<td>学号：<input type="text" name="id" value="${student.id}"></td>
		</tr>
		<tr>
			<td>姓名：<input type="text" name="name" value="${student.name}"></td>
		</tr>
		<tr>
			<td>年龄：<input type="text" name="age" value="${student.age}"></td>
		</tr>
		<tr>
			<td>性别：<input type="text" name="sex" value="${student.sex}"></td>
		</tr>
		<tr>
			<td>班级：<input type="text" name="classid" value="${student.classid}"></td>
		</tr>
		<tr>
			<td><input type="submit"  value="修改"></td>
		</tr>
	</table>
	</form>
</body>
</html>