package mallet.database.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {	
	private static SessionFactory malletAccountsSession = getSessionFactoryConfig("mallet.accounts.cfg.xml");
	private static SessionFactory malletProductsSession = getSessionFactoryConfig("mallet.products.cfg.xml");
	
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	private static SessionFactory getSessionFactoryConfig(String hibernateConfigXML) {
		return new Configuration().configure( hibernateConfigXML ).buildSessionFactory();
	}
	
	public static Session getMalletAccountsInstance() {
		return getDatabaseInstance( malletAccountsSession );
	}
	
	public static Session getMalletProductsInstance() {
		return getDatabaseInstance( malletProductsSession );
	}
	
	private static Session getDatabaseInstance(SessionFactory databaseSession) {
		Session session = threadLocal.get();
		
		session = databaseSession.openSession();
		threadLocal.set(session);
		
		return session;
	}
}
