package mallet.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HashedPassword {
	private String hashed;
	
	public HashedPassword(String username, String password) {
		byte[] salt = ( username + password ).getBytes();
		
		KeySpec hashSpecifications = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		
		String generatedHashedPassword = generateHash("PBKDF2WithHmacSHA512", hashSpecifications);
		
		this.setHashedPassword( generatedHashedPassword );
	}
	
	private String generateHash(String algorithm, KeySpec hashSpecifications) {
		try {
			SecretKeyFactory sKFactory = SecretKeyFactory.getInstance(algorithm);
			
			byte[] hashedPassword = sKFactory.generateSecret(hashSpecifications).getEncoded();
			
			return Hex.encodeHexString(hashedPassword);
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			
			return null;
		}
	}

	public String getValue() {
		return hashed;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashed = hashedPassword;
	}
}
