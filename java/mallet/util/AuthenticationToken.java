package mallet.util;

import java.util.Random;

public class AuthenticationToken {
	private String token;
	
	public AuthenticationToken(int length) {
		StringBuffer tokenHolder = new StringBuffer();
		
		appendRandom(tokenHolder, length);
		
		this.setToken(tokenHolder.toString());
	}
	
	private void appendRandom(StringBuffer holder, int length) {
		char[] possibleDigits = { 
			'0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'C', 'Z',
			'c', 'd', 'e', 'f', 'b', 'a',
			'H', 'B', 'F', 'M', 'L', 'R'
		};
		
		for (int i = 0; i < length; i++) {
			holder.append(possibleDigits[ new Random().nextInt(6*4) ]);
		}
	}

	public String getValue() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
