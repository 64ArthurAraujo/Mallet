package mallet.database.table.access;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import mallet.database.hibernate.HibernateConnection;
import mallet.database.object.Account;
import mallet.util.HashedPassword;

/**
 * Class holder for access queries to the <b>accounts</b> table.
 * 
 * @category
 * Data Access Object
 */

public class AccountAccessor {
	private Session databaseInstance;
	
	// Login Process
	 
	public Account loginUser(Account account) {
		databaseInstance = HibernateConnection.getMalletAccountsInstance();
		
		return doLoginUser( account );
	}
	
	private Account doLoginUser(Account account) {
		Query<Account> loginQuery = getQuery("FROM Account WHERE username = :usernm AND password = :passwd");
		setQueryParameters(loginQuery, account);
	
		return loginQuery.getSingleResult();
	}
	
	private void setQueryParameters(Query<Account> query, Account account) {
		query.setParameter("usernm", account.getUsername());
		
		query.setParameter("passwd", this.getPasswordHash(
			account.getUsername(), account.getPassword()
		));
	}
	
	// Check Username Process
	
	public boolean checkIsUsernameTaken(String username) {
		databaseInstance = HibernateConnection.getMalletAccountsInstance(); 
		
		return doCheckUsernameIsTaken( username );
	}
	
	private boolean doCheckUsernameIsTaken(String username) {
		Query<Account> checkUsernameQuery = getQuery("FROM Account WHERE username = :usernm"); 
		checkUsernameQuery.setParameter("usernm", username); 
		
		return usernameIsTaken( checkUsernameQuery );
	}
	
	private boolean usernameIsTaken(Query<Account> query) {
		try {
			 query.getSingleResult();
			 
			 return true;
		} catch (NoResultException e) {
			return false;
			
		} finally {
			databaseInstance.close();
		}
	}
	
	// Check Email Process
	
	public boolean checkEmailIsTaken(String email) {
		databaseInstance = HibernateConnection.getMalletAccountsInstance();
		
		return doCheckEmailIsTaken( email ); 
	}
	
	private boolean doCheckEmailIsTaken(String email) {
		Query<Account> checkEmailQuery = getQuery("FROM Account WHERE email = :email");
		checkEmailQuery.setParameter("email", email);
		
		return emailIsTaken( checkEmailQuery  );
	}
	
	private boolean emailIsTaken(Query<Account> query) {
		try {
			query.getSingleResult();
			
			return true;
		} catch (NoResultException e) {
			return false;
		} finally {
			databaseInstance.close();
		}
	}
	
	/**
	 * < Methods used by two or more processes 
	 */
	
	private String getPasswordHash(String username, String password) {
		return new HashedPassword(username, password).getValue();
	}
	
	private Query<Account> getQuery(String query) {
		return databaseInstance.createQuery(
			query, Account.class
		);
	}
}
