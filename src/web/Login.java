/**
 * Title:			Login.java
 * @author			William Walsh
 * @version			1.0
 * @since			28-6-2017
 * Purpose:			This is the Servlet which contains the business logic for logging the user into the site with the supplied credentials.
 * 					The controller will forward the HTTP request and response to this Servlet if the request path contains the URL-mapping for this Servlet.
 * */
package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dto.UserInfo;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = { "/login" })
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public String longTimeToString(long time) {
		Instant instant = Instant.ofEpochMilli(time);
		ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC+01:00"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formatter.format(zdt);
	}

	public boolean isNull(String input) {
		return (input == null || input.equals("")) ? true : false;
	}

	public boolean emailExists(SessionFactory sf, String email) {
		// Check if email exists in DB
		Session dbSession = null;
		boolean result = false;
		try {
			dbSession = sf.openSession();
			dbSession.beginTransaction();

			// Possible failure point >> determine what .get does  
			result = (dbSession.get(UserInfo.class, email) == null) ? false : true;

		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			System.out.println("Login.java>>emailExists()>>tryCatchBlock");
		} finally {
			dbSession.close();
		}
		return result;
	}

	public UserInfo retrieveUserInfo(SessionFactory sf, String email) {
		Session dbSession = null;
		UserInfo user = null;
		try {
			dbSession = sf.openSession();
			dbSession.beginTransaction();
			user = (UserInfo) dbSession.get(UserInfo.class, email);
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			System.out.println("Login.java>>emailExists()>>tryCatchBlock");
		} finally {
			dbSession.close();
		}
		return user;
	}

	public boolean isPasswordCorrect(String enteredPassword, String dbPassword) {
		return (enteredPassword.equals(dbPassword)) ? true : false;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String enteredPassword = request.getParameter("password");
		HttpSession httpSession = request.getSession();
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session dbSession = null;
		UserInfo user = null;
		httpSession.setAttribute("error", "");
		
		if (isNull(email) || isNull(enteredPassword)) {
			httpSession.setAttribute("error", "Please enter both an E-mail and a password");
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			httpSession.setAttribute("error", null);
		}

		if (emailExists(sf, email)) {
			httpSession.setAttribute("error", "");
			
			// WARNING:  user >> Potentially could be null ***
			user = retrieveUserInfo(sf, email);
			
			if(user !=null){
				if(isPasswordCorrect(enteredPassword, user.getPassword())){
					httpSession.setAttribute("userInfo", user);
					getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
					return;
				}else{
					httpSession.setAttribute("error", "Login credentials incorrect, please re-enter them.");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
				
			}else{
				httpSession.setAttribute("error", "Error occurred while connecting to DB.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		} else { // !emailExists
			httpSession.setAttribute("error", "Email doesn't exist, please register.");
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}

		// if yes
		// Check if password in DB matches entered password

		// if yes
		// Allow into site to view their confidential data >> redirect to their
		// home page
		// if not
		// Redirect user to login jsp with error that password is incorrect

		/*
		 * try { response.setContentType("text/html"); PrintWriter p =
		 * response.getWriter();
		 * 
		 * p.println("<html><body>"); p.print("Hello " + email);
		 * p.print("your password is " + password);
		 * 
		 * 
		 * UserInfo u = null;
		 * 
		 * // Hibernate exists() function?? >> tests whether class exists as //
		 * record in table try { // if(exists(email)){retrieve} dbSession =
		 * sf.openSession(); dbSession.beginTransaction();
		 * 
		 * if (dbSession.get(UserInfo.class, email) == null) {
		 * 
		 * getServletContext().getRequestDispatcher("/login.jsp").forward(
		 * request, response);
		 * 
		 * } else { u = (UserInfo) dbSession.get(UserInfo.class, email);
		 * System.out.println(u.getEmail());
		 * System.out.println(u.getFirstName());
		 * System.out.println(u.getPassword()); } } catch (Exception e) {
		 * dbSession.getTransaction().rollback(); } finally { dbSession.close();
		 * } // Does email and hashed password match hashed password of email //
		 * record in DB // If yes forward/redirect to successful page //
		 * Otherwise redirect to login with error message
		 * 
		 * p.println("</body></html>"); p.close(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}
}
