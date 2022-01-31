package mallet.database.object;

import java.util.Date;

public class Account {
	public String username;
	public String email;
	public String password;
	public String token;
	public Date   joinDate;
	
	public Account() { }
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Account(String username, String email, String password) {
		this(username, password);
		this.email = email;
	}

	public Account(String username, String email, String password, String token, Date joinDate) {
		this(username, email, password);
		this.token    = token;
		this.joinDate = joinDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}	
}
