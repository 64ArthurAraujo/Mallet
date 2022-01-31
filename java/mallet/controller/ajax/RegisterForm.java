package mallet.controller.ajax;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.database.table.AccountsManager;

public class RegisterForm extends AjaxHttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usernameCheck = request.getParameter("username_check");
		String emailCheck    = request.getParameter("email_check");
		
		boolean isCheckingUsername = (usernameCheck != null && emailCheck    == null);
		boolean isCheckingEmail    = (emailCheck    != null && usernameCheck == null);
		
		
		if (isCheckingUsername) {
			 boolean usernameIsTaken = 
				new AccountsManager().checkIsUsernameTaken(usernameCheck);
			 
			 if (usernameIsTaken) {
				 writeResponse("true", response);
			 } else {
				 writeResponse("false", response);
			 }
			 
		} else if (isCheckingEmail) {
			boolean emailIsTaken =
				new AccountsManager().checkIsEmailTaken(emailCheck);
			
				if (emailIsTaken) {
					writeResponse("true", response);
				} else {
					writeResponse("false", response);
				}
				
		} else {
			response.sendError(403);
		}
	} 
}
