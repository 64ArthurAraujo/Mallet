package mallet.database.table.transfer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import mallet.database.hibernate.HibernateConnection;
import mallet.database.object.Account;
import mallet.util.AuthenticationToken;
import mallet.util.HashedPassword;

public class AccountTransferer {
	private Session databaseInstance;
	
	public void registerUser(Account account) {
		databaseInstance = HibernateConnection.getMalletAccountsInstance();
		
		setAdditionalCredentials(account);
		
		doInsert( account );
	}
	
	private void doInsert(Account account) {
		Transaction transaction = null;
		
		try { 
			transaction = databaseInstance.beginTransaction(); 
			
			databaseInstance.save( account ); 
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			
			transaction.rollback();
		} finally { 
			databaseInstance.close();
		}
	}
	
	private void setAdditionalCredentials(Account account) {
		account.setToken( generateAuthToken() );
		
		account.setPassword(
			getPasswordHash( account.getUsername(), account.getPassword() )
		);
	}
	
	/**
	 * < Methods used by two or more processes 
	 */
	
	private String generateAuthToken() {
		return new AuthenticationToken(30).getValue();
	}
	
	private String getPasswordHash(String username, String password) {
		return new HashedPassword(username, password).getValue();
	}
}
