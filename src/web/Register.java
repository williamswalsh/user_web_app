package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dto.UserInfo;

@WebServlet(description = "This is the servlet that checks if the  is already a registered  "
							+ "and if not registers the data within the database.", urlPatterns = { "/register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean isNull(String input) {
		return (input == null || input.equals("")) ? true : false;
	}
	public boolean isNotNull(String input) {
		return !isNull(input);
	}

	public boolean emailExistsInDB(SessionFactory sf, String email) {
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

	public boolean addUserToDB(SessionFactory sf, UserInfo user){
		Session dbSession = null;
		boolean success = false;
		try{
			dbSession = sf.openSession();
			dbSession.beginTransaction();
			dbSession.save(user);
			dbSession.getTransaction().commit();
			success = true;
		}catch(Exception e){
			success = false;	//Necessary?
			e.printStackTrace();
			dbSession.getTransaction().rollback();
		}finally{
			dbSession.close();
		}
		return success;
	}

	public boolean isValidMobile(String mobile){
		return mobile.matches("[0-9]*");
	}
	
	public boolean isValidName(String aName){
		return aName.matches("[a-zA-Z]*");
	}
	
	public boolean isValidEmail(String email){
		return email.contains("@");	
	}
	
	public void isValidMobile(){
		
	}
	
	public boolean verifyUserFirstName(HttpServletRequest request){
		return isValidName(request.getParameter("firstName"));
	}
	
	public boolean verifyUserLastName(HttpServletRequest request){
		return isValidName(request.getParameter("lastName"));
	}
	public boolean verifyUserEmail(HttpServletRequest request){
		return isValidName(request.getParameter("email"));
	}
	
	public ArrayList<String> verifyUserInput(HttpServletRequest request){

		ArrayList<String> errors = null;
		
		if( !verifyUserFirstName(request) ){
			if(errors == null){
				errors = new ArrayList<String>();
			}
			errors.add("User first name is invalid");
		}
		if( !verifyUserLastName(request) ){
			if(errors == null){
				errors = new ArrayList<String>();
			}
			errors.add("User last name is invalid");
		}
		if( !verifyUserEmail(request) ){
			if(errors == null){
				errors = new ArrayList<String>();
			}
			errors.add("User first name is invalid");
		}
		return errors;
	}
	
	public UserInfo initUser(HttpServletRequest request){
		UserInfo user = null;
		ArrayList<String> errors = verifyUserInput(request);
		
		if(errors != null){
			request.getSession().setAttribute("errors", errors);
			System.out.println("errors != null");
		}else{
			System.out.println("errors == null");
			//Retrieve Parameters
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String password = request.getParameter("password");
			
			//verifyMobile();
			//doPasswordsMatch() >> Different stage >> Maybe JS on Jsp page
			
			//Create UserObject
			user = new UserInfo();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setMobile(mobile);
			user.setPassword(password);
			user.setEmail(email);
		}
		return user;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		UserInfo user = null;
		request.getSession().setAttribute("error", "");
		String email = request.getParameter("email");
		
		if(isNotNull(email)){
			if(emailExistsInDB(sf, email)){
				request.getSession().setAttribute("error","The email you used to register is already registered. Please Login.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}else{
				//Email is not null & email doesn't exist
				user = initUser(request);
				
				if(user == null){
					System.out.println("User is null returning to register.jsp no input to db");
					//initUser >> Verify User input issue
					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}
				
				if(addUserToDB(sf, user)){
					getServletContext().getRequestDispatcher("/registered.jsp").forward(request, response);
					return;
				}else{
					request.getSession().setAttribute("error","Couldn't add user to Database");
					getServletContext().getRequestDispatcher("/registered.jsp").forward(request, response);
					return;
				}
			}
		}else{
			//Email is null
			request.getSession().setAttribute("error","Please enter an email.");
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
	}
}
