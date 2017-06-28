<%-- 
 Title:			login.jsp
 @author		William Walsh
 @version		1.0
 @since			28-6-2017
 Purpose:		This is a jsp page which allow the user to enter their credentials to login to the site. 
--%>

<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
			pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
public boolean isNull(String input) {
	return (input == null || input.equals("")) ? true : false;
}
public String displayErrorText(HttpSession session){
	String error = (String)session.getAttribute("error");
	return (isNull(error))? "": error;
}
%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<h1>Login</h1>
	
	If you don't have an account then you can register
	<a href="register.jsp">here</a>
	<form action="controller/login" method="POST">
		<table>
			<tr>
				<td>E-mail:</td>
				<td><input title="E-Mail Address" type="email" name="email" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input title="Account Password" type="password" name="password" required></td>
			</tr>
			<tr>
				<td><input title="Log User in" type="submit" value="Login"></td>
				<td><%=displayErrorText(session)%></td>
			</tr>
		</table>
	</form>
</body>
</html>