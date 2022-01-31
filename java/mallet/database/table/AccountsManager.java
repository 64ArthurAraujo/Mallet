package mallet.database.table;

import mallet.database.object.Account;
import mallet.database.table.access.AccountAccessor;
import mallet.database.table.transfer.AccountTransferer;

public class AccountsManager {
	/**
	 * This class is used to encapsulate Access and Transfer classes
	 * about this table.
	 */
	
	/**
	 * @category 
	 * Data Transfer Object
	 */
	
	public void registerUser(Account account) {
		new AccountTransferer().registerUser( account );
	}
	
	/**
	 * @category 
	 * Data Access Object
	 */
	
	public Account loginUser(Account account) {
		return new AccountAccessor().loginUser( account );
	}
	
	public boolean checkIsUsernameTaken(String username) {
		return new AccountAccessor().checkIsUsernameTaken( username );
	}
	
	public boolean checkIsEmailTaken(String email) {
		return new AccountAccessor().checkEmailIsTaken( email );
	}
}
