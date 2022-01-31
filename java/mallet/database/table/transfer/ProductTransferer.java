package mallet.database.table.transfer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mallet.database.hibernate.HibernateConnection;
import mallet.database.object.Product;

public class ProductTransferer {
	private Session databaseInstance;
	
	public void insertProduct(Product product) {
		databaseInstance = HibernateConnection.getMalletAccountsInstance();
		
		doInsert( product );
	}
	
	private void doInsert(Product product) {
		Transaction transaction = null;
		
		try {
			transaction = databaseInstance.beginTransaction();
			
			databaseInstance.save( product );
			
			transaction.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			
			transaction.rollback();
		} finally {
			databaseInstance.close();
		}
	}
}
