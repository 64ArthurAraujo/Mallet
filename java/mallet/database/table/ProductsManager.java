package mallet.database.table;

import java.util.List;

import mallet.database.object.Product;
import mallet.database.table.access.ProductAccessor;
import mallet.database.table.transfer.ProductTransferer;

/**
 * This class is used to encapsulate <b>Access</b> and <b>Transfer</b> classes
 * about this table.
 * 
 * @category
 * Data Access/Transfer Encapsulator
 */

public class ProductsManager {	
	/**
	 * @category
	 * Data Transfer Object
	 */
	
	public void insertProduct(Product product) {
		new ProductTransferer().insertProduct( product );
	}
	
	/**
	 * @category
	 * Data Access Object
	 */
	
	public Product queryProduct(int productId) {
		return new ProductAccessor().queryProduct( productId );
	}
	
	public List<Product> queryMultipleProducts(int querySize) {
		return new ProductAccessor().queryMultipleProducts( querySize );
	}

}
