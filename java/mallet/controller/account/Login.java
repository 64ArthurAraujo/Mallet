package mallet.controller.account;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.controller.HttpServletController;
import mallet.database.object.Account;
import mallet.database.table.AccountsManager;

public class Login extends HttpServletController {
	/**
	 * This servlet logs the user in if the sent credentials
	 * match the ones in the database.
	 * This servlet is called by the Account controller.
	 */
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Account loginAccount = new AccountsManager().loginUser(
			new Account(username, password)
		);
		
		boolean userIsFound = ( loginAccount.getToken() != null );
		
		System.out.print(loginAccount.getToken());
		
		if (userIsFound) {
			setAuthToken(loginAccount.getToken(), response);
			
			redirectToPath("/home.jsp", response, request);
		} else {
			// TODO Send error type using the POST method instead.
			
			redirectToPath("/signin.jsp?e=CREDENTIALS_NOT_FOUND", response, request);
		}
	}
	
	private void setAuthToken(String authToken, HttpServletResponse response) {
		  Cookie token = new Cookie("auth_token", authToken);
		  
		  token.setMaxAge(60*60*72);
		  
		  response.addCookie(token);
	}
}
