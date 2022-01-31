package mallet.cache;

import java.util.ArrayList;
import java.util.List;

import mallet.cache.thread.object.ProductThread;
import mallet.database.object.Product;

public class ProductsCache  {
	private static List<Product> cacheHolder        = new ArrayList<Product>();
	private static List<ProductThread> threadHolder = new ArrayList<ProductThread>();
	
	public static Product findElement(int requestedId) {
		Product productToReturn = null;
		
		for (Product checkedProduct : cacheHolder) {
			if (checkedProduct.getId() == requestedId) {
				productToReturn = checkedProduct;	
			}
		}
		
		if (productToReturn != null) { 
			resetProductTimeout( productToReturn ); 
		}
		
		return productToReturn;
	}
	
	public static void removeElement(int requestedId) {
		int listCursor = 0;
		int locationToBeRemoved = 0;

		for (Product checkedProduct : ProductsCache.cacheHolder) {
			if (checkedProduct.getId() == requestedId) {
				locationToBeRemoved = listCursor;
			} else {
				listCursor++;
			}
		}
		
		cacheHolder.remove(locationToBeRemoved);
	}
	
	public static void addElement(Product productSent) {
		cacheHolder.add(productSent);
		
		Thread elementTimeout = removeElementTimeout(productSent, 30);
		
		threadHolder.add( new ProductThread(productSent.getId(), elementTimeout) );
	}
	
	private static Thread removeElementTimeout(Product productSent, int secondsToRemove) {
		Thread timeoutThread = new Thread(() -> {
			try {
				
				Thread.sleep(1000 * secondsToRemove);
				
				removeElement(productSent.getId());
				removeProductThread(productSent.getId());
				
			} catch (InterruptedException e) {

				removeElement(productSent.getId());
				removeProductThread(productSent.getId());
			}
		});	
		
		return timeoutThread;
	}
	
	// ThreadHolder Methods
	
	private static void removeProductThread(int requestedId) {
		int listCursor = 0;
		int locationToBeRemoved = 0;
		
		for (ProductThread checkedProduct : ProductsCache.threadHolder) {
			if (checkedProduct.getId() == requestedId) {
				locationToBeRemoved = listCursor;
				
			} else {
				listCursor++;
			}
		}
		
		threadHolder.remove(locationToBeRemoved);
	}
	
	private static void resetProductTimeout(Product productResetedFromTimeout) {
		Thread requestedThread = findThreadElement( productResetedFromTimeout.getId() );
		
		requestedThread.interrupt();
		
		addElement(productResetedFromTimeout);
	}
	
	private static Thread findThreadElement(int requestedId) {
		Thread threadReturned = null;
		
		for (ProductThread currentThread : threadHolder) {
			if (currentThread.getId() == requestedId) {
				threadReturned = currentThread.getThread();
			}
		}
		
		return threadReturned;
	}
}
