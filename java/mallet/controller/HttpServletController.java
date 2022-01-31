package mallet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class HttpServletController extends HttpServlet {
	
	protected void redirectToPath(String path, HttpServletResponse response, HttpServletRequest request) {
		/**
		 * Redirects the response to the path sent by the method
		 */
		
		try {
			response.sendRedirect(
				"http://" + request.getServerName() + ":" + request.getServerPort() + "/Mallet" + path
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
