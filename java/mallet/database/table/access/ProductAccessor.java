package mallet.database.table.access;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import mallet.cache.ProductsCache;
import mallet.database.hibernate.HibernateConnection;
import mallet.database.object.Product;

/**
 * Class holder for access queries to the <b>products</b> table.
 * 
 * @category
 * Data Access Object
 */

public class ProductAccessor {
	private Session databaseInstance;
	
	// Query product Process
	
	public Product queryProduct(int productId) {
		databaseInstance = HibernateConnection.getMalletProductsInstance();
		
		return doQueryProduct( productId );
	}
	
	private Product doQueryProduct(int productId) {
		Product returnedProduct = null;
		
		if (getProductFromCache(productId) != null) {
			
			returnedProduct = getProductFromCache( productId );
		} else {
			returnedProduct = getProductFromDatabase( productId );
		}
		
		try {
			return returnedProduct;
		} finally {
			databaseInstance.close();
		}
	}
	
	// TODO fix this
	private Product getProductFromCache(int productId) {
		return ProductsCache.findElement(productId);
	}
	
	private Product getProductFromDatabase(int productId) {
		Query<Product> productQuery = getQuery("from Product where id = :id");
		
		productQuery.setParameter("id", productId);
		
		Product queryiedProduct = productQuery.getSingleResult();
		
		addProductToCache( queryiedProduct );
		
		return queryiedProduct;
	}
	
	private void addProductToCache(Product product) {
		if (product.getDescription() != null) {
			ProductsCache.addElement( product );
		}
	}
	
	// Query Multiple Products Process
	
	public List<Product> queryMultipleProducts(int querySize) {
		databaseInstance = HibernateConnection.getMalletProductsInstance();
		
		return doQueryMultipleProducts( querySize );
	}
	
	private List<Product> doQueryMultipleProducts(int querySize) {
		Query<Product> multipleProductsQuery = getQuery("from Product");
		
		multipleProductsQuery.setMaxResults(querySize);
		
		return multipleProductsQuery.list();
	}
	
	
	// Methods used by one or more methods
	
	private Query<Product> getQuery(String query) {
		return databaseInstance.createQuery(
			query, Product.class
		);
	}

}
