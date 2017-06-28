/**
 * Title:			Controller.java
 * @author			William Walsh
 * @version			1.0
 * @since			28-6-2017
 * Purpose:			This is the Controller in my MVC paradigm. It will process request and forward users to appropriate resources.
 * */
package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/controller/*"}, description = "This is the Controller in my MVC paradigm. It will process request and forward users to appropriate resources.")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}
	
	private void performTask(HttpServletRequest request, HttpServletResponse response){
		String path = request.getPathInfo();
		System.out.println(path);
		String name = path.substring(1);
		System.out.println(name);
		
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(path);
		
		if(rd == null){
			rd = context.getRequestDispatcher("/Error");
		}
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
