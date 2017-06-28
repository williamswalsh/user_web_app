<%-- 
 Title:			success.jsp
 @author		William Walsh
 @version		1.0
 @since			28-6-2017
 Purpose:		If the user successfully logs into the site they will be forwarded to this resource.
--%>
<%@ page 	import="dto.UserInfo" 
			language="java"
			contentType="text/html; charset=UTF-8"
			pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>
Hello and welcome to our VIP site.
Your personal data is as follows:
<%
UserInfo user = (UserInfo)session.getAttribute("userInfo");
%>
<table>
		<tr>
			<td>Email:</td>
			<td><%=user.getEmail()%></td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><%=user.getFirstName()%></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><%=user.getLastName()%></td>
		</tr>
		<tr>
			<td>Mobile:</td>
			<td><%=user.getMobile()%></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><%=user.getPassword()%></td>
		</tr>
		<tr>
			<td>Class:</td>
			<td><%=user.getClass()%></td>
		</tr>		
	</table>
	Secret Data that only you can see.
</body>
</html>