package mallet.controller.redirect;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.controller.account.Login;
import mallet.controller.account.Register;
import mallet.controller.ajax.RegisterForm;

@WebServlet("/Account")   
public class Account extends HttpServlet {
	/**
	 * This servlet redirect to different servlets that 
	 * change or create settings about the user's account.
	 */
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formAction = request.getParameter("form-action");
		
		switch (formAction) {
			case "register":
				// Redirects to the register servlet.
				new Register().doPost(request, response);
				break;
			
			case "login":
				// Redirects to the login servlet.
				new Login().doPost(request, response);
				break;
				
			case "check-username-or-email-exists":
				// Checks if the sent username is taken;
				new RegisterForm().doPost(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
