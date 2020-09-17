package com.journaldev.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ComputerSecurityServlet
 */
@WebServlet("/register")
public class ComputerSecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//public static final String HTML_START="<html><head><link rel=\"stylesheet\" href=\"styles.css\"></head><body>";
	//public static final String HTML_END="</body></html>";
	//public static final String HTML_STAYLE = "<head><link rel=\"stylesheet\"  herf=\"styles.css\"></head>";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerSecurityServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/UserRegister.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("this is delete user info: " + deleteUser.toString() );
		
		
		
		User user = new User();
		user.setfName(request.getParameter("fname"));
		
		
		if (user.getfName() != null) {
			
		user.setlName(request.getParameter("lname"));
		user.setEmail(user);
		user.setSalt();
		
		try {
			user.setPass(request.getParameter("pass"));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println("this is insert user info: " + user.toString() );
			user.insertUserToDB(); 
		}
		
		
		
		User deleteUser = new User();
		deleteUser.setfName(request.getParameter("deletefname"));
		if (deleteUser.getfName() != null)
		{
			
			deleteUser.setlName(request.getParameter("deletelname"));
			deleteUser.setSalt();
			deleteUser.setEmail(deleteUser);
			
			deleteUser.setSamePass(request.getParameter("deletepass"));;
			
			System.out.println("this is delete user info: " + deleteUser.toString() );
			deleteUser.deleteUserFromDB(); 
			
		}
		
		
		
		
		
		//deleteUser = deleteUser.retrunUserFromDB(request.getParameter("deletefname")+request.getParameter("deletelname"));
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsps/Hello.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
