package mallet.controller.redirect;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.controller.shop.ProductInsert;

@WebServlet("/Shop")   
public class Shop extends HttpServlet {
	/**
	 * This servlet redirect to different servlets that 
	 * searches or buy items from the shop.
	 */
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String formAction = request.getParameter("form-action");
		
		switch (formAction) {
			case "new-product":
				new ProductInsert().doPost(request, response);
				break;
		
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
