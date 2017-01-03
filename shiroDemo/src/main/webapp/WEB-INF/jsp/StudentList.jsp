<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学生列表</title>
</head>
<body>
	<h3 align="center">学生信息表</h3>
	<h1>Hello, <shiro:principal/>, how are you today?</h1>
	<form action="${pageContext.request.contextPath}/student/findStudentList" method="post">
	<table align="center">
		<tr>
			<td>查询条件：</td>
			<td>学号：<input type="text" name="id" value="${student.id}"></td>
			<td>姓名：<input type="text" name="name" value="${student.name}"></td>
			<td>年龄：<input type="text" name="age" value="${student.age}"></td>
			<td>性别：<input type="text" name="sex" value="${student.sex}"></td>
			<td>班级：<input type="text" name="classid" value="${student.classid}"></td>
			<shiro:user>  
				<td><input type="submit"  value="查询"></td>
			</shiro:user>
		</tr>
	</table>
	</form>
	
	<table width="80%" border="1" align="center">
		<thead>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th>班级</th>
			<th colspan="2">操作</th>
		<tr>
		</thead>
		<tbody>
			<c:forEach items="${sList}" var="stu">
				<tr>
					<td>${stu.id}</td>
					<td>${stu.name}</td>
					<td>${stu.age}</td>
					<td>${stu.sex}</td>
					<td>${stu.classid}</td>
					<td align="center">
						<shiro:hasPermission name="student:update">  
						    <form action="${pageContext.request.contextPath}/student/${stu.id}" method="get">
							<input type="submit" value="修改">
							</form>
						</shiro:hasPermission>
					</td>
					<td align="center">
						<shiro:hasPermission name="student:delete">  
						    <form action="${pageContext.request.contextPath}/student/${stu.id}" method="post">
							<input type="hidden" name="_method" value="DELETE"/>
							<input type="submit" value="删除">
							</form>
						</shiro:hasPermission>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>