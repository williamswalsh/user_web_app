<%-- 
 Title:			registered.jsp
 @author		William Walsh
 @version		1.0
 @since			28-6-2017
 Purpose:		If the user successfully registers with the site they will be forwarded to this resource.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registered</title>
</head>
<body>
	Hello
	<%=request.getSession().getAttribute("firstName") %>
	<br> Welcome to the website.
	<br> Personal Data:
	<table border="1">
		<tr>
			<td>First Name:</td>
			<td><%=request.getSession().getAttribute("firstName") %></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><%=request.getSession().getAttribute("lastName") %></td>
		</tr>
		<tr>
			<td>E-mail:</td>
			<td><%=request.getSession().getAttribute("email") %></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><%=request.getSession().getAttribute("password") %></td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td><%=request.getSession().getAttribute("mobile") %></td>
		</tr>

	</table>
</body>
</html>