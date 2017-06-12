package web;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetSession extends HttpServlet {
  
	public void isUserRegistered(){
		
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    try {
      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<html><body>");

      if (session == null) {
        writer.println("<p>You are not logged in</p>");
      } else {
        writer.println("Thank you, you are already logged in");
        writer.println("Here is the data in your session");
        Enumeration names = session.getAttributeNames();
        while (names.hasMoreElements()) {
          String name = (String) names.nextElement();
          Object value = session.getAttribute(name);
          writer.println("<p>name=" + name + " value=" + value + "</p>");
        }
      }
      writer.println("<p><a href=\"/web_11_sessionMgmt/login.html\">Return" + 
                     "</a> to login page</p>");
      writer.println("</body></html>");
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
