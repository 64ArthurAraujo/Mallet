package mallet.controller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.controller.HttpServletController;
import mallet.database.object.Product;
import mallet.database.table.ProductsManager;

public class ProductInsert extends HttpServletController {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		if ( informationIsValid( request ) ) {
			String name        = request.getParameter("name");
			String description = request.getParameter("description");
			String category    = request.getParameter("category");
			String price       = request.getParameter("price");
		
			Product product = new Product(
				name, description, category, Integer.parseInt(price)
			);
			
			new ProductsManager().insertProduct( product );
		} else {
			redirectToPath("/product/new.jsp?e=INVALID_PRODUCT_INFO", response, request);
		}
	}
	
	private boolean informationIsValid(HttpServletRequest request) {
		String name  = request.getParameter("name");
		String price = request.getParameter("price");
		
		return (
			// Product name validation
			!name.isBlank()            &&
			name != null               &&
			
			// Product price validation
			!price.isBlank()           &&
			canConvertToInteger(price) 
			
		) ? true : false;
	}
	
	private boolean canConvertToInteger(String checkedString) {
		try {
			
			@SuppressWarnings("unused")
			int convertedString = Integer.parseInt(checkedString);
		
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
