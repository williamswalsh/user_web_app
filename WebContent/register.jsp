<%-- 
 Title:			register.jsp
 @author		William Walsh
 @version		1.0
 @since			28-6-2017
 Purpose:		This is a jsp page which allow the user to enter their credentials in order to register to the site. 
--%>
<%@ page import="dto.UserInfo,java.util.ArrayList" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
public boolean isNull(String input) {
	return (input == null || input.equals("")) ? true : false;
}
public String displayErrorText(HttpSession session){
	String error = (String)session.getAttribute("error");
	return (isNull(error))? "": error;
}
public ArrayList<String> displayErrors(HttpSession session){
	ArrayList<String>errors = (ArrayList<String>)session.getAttribute("errors");
	return errors;
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>

<body>
	<h1>Registration</h1>

	Please enter your details.
	<br>If you have an account already you can login
	<a href="login.jsp">here</a>
	<form action="controller/register" method="POST">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input title="First Name" type="text" name="firstName" 
					required></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input title="Last Name" type="text" name="lastName"
					required></td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input title="E-Mail Address" type="email" name="email"
					required></td>
			</tr>
			<tr>
				<td>Verify E-mail:</td>
				<td><input title="Re-enter E-Mail Address" type="email"
					name="email2" required></td>
			</tr>
			<tr>
				<td>Mobile:</td>
				<td><select id="countryCode">
						<option value="1">USA +1</option>
						<option value="2">Ireland +353</option>
						<option value="3">England</option>
						<option value="4">France</option>
						<option value="4">Germany</option>
						<option value="4">Spain</option>
						<option value="4">Portugal</option>
						<option value="4">China</option>
						<option value="4">Japan</option>
						<option value="4">Russia</option>
				</select>
				<input 	title="Mobile Phone Number" 
						type="tel" 
						name="mobile"
						required>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input title="Account Password" type="password"
					name="password" required></td>
			</tr>
			<tr>
				<td><input title="Register User" type="submit" value="Register"></td>
				<td><%=displayErrorText(session)%></td>
			</tr>
		</table>
		<%
		ArrayList<String> errors = displayErrors(session);
		if( errors != null ){
			%>
			errors != null
			<%
			for(String s: errors){
			%>
				<br>
				<%=s%>
			<%
			}
		}
		%>
		
		<%
      if( request.getParameter("email")!=null && request.getParameter("email2") != null){
      	if( ! request.getParameter("email").equals(request.getParameter("email2"))){
    	  %>
		Email's are not the same.
		<%
      
      	}
      }
      %>
	</form>
</body>
</html>