package mallet.controller.account;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.controller.HttpServletController;
import mallet.database.object.Account;
import mallet.database.table.AccountsManager;

public class Register extends HttpServletController {
	/**
	 * This servlet creates a new user's account.
	 * It is called by the Account redirect controller.
	 */
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ( credentialsAreValid(request) ) {
			String username = request.getParameter("username");
			String email    = request.getParameter("email");
			String password = request.getParameter("password");
			
			Account registerAccount = new Account(username, email, password);
			
			new AccountsManager().registerUser( registerAccount );
			
			new Login().doPost(request, response);
		} else {
			redirectToPath("/signup.jsp?e=INVALID_CREDENTIALS", response, request);
		}
	}
	
	private boolean credentialsAreValid(HttpServletRequest request) {
		String username    = request.getParameter("username");
		String email       = request.getParameter("email");
		String password    = request.getParameter("password");
		String secPassword = request.getParameter("sec-password");
		
		return (
			// Username Validation
			username.matches("^[a-zA-Z0-9_]*$")                  &&	
			username != null                                     &&
			new AccountsManager().checkIsUsernameTaken(username) &&
			!username.isBlank()                                  &&
			
			// Email Validation
			email.matches("^(.+)@(\\S+)$")                       &&
			email != null                                        &&
			new AccountsManager().checkIsEmailTaken(email)       &&
			!email.isBlank()                                     &&
			
			// Password Validation 
			password != null                                     &&
			!password.isBlank()                                  &&
			password.equals(secPassword)
			
		) ? true : false;
	}
}
