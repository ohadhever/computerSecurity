package com.journaldev.first;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

import com.sun.media.jfxmedia.track.Track.Encoding;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;

public class User {

	String fName;
	String lName;
	String email;
	String pass;
	byte[] salt; 
	//byte[] pass;
	
	
	//SecureRandom random = new SecureRandom();
	//byte[] salt = new byte[16];
	
	
	
	public String getfName() {
		return fName;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setSalt() {
		byte[] bytes = new byte[5];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		this.salt = bytes;
		System.out.println("this is the salt array: "+Arrays.toString(bytes));
	}
	
	public byte[] getSalt() {
		return this.salt;
	}
	
	
	public void setEmail(User user) {
		String newEmail = user.fName + user.lName+"@CS.com";
		this.email=newEmail;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.reset();
		digest.update(this.getSalt());
		System.out.println("this is user salt: "+this.toString());
		byte[] hash = digest.digest(pass.getBytes());
		this.pass = bytesToStringHex(hash);
		
		
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private String bytesToStringHex(byte[] hash) {
		char[] hexChars = new char[hash.length * 2];
		for (int j = 0 ; j< hash.length; j++) {
			
			int v = hash[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	@Override
	public String toString() {
		return "User [fName=" + fName + ", lName=" + lName + ", email=" + email + ", pass=" + pass + ", salt= "+Arrays.toString(getSalt())+ "]";
	}
	
	public Boolean insertUserToDB() {
		try {
					
			//System.out.println("INSERT INTO user"+"(username,email,password) VALUES "+"('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"')");
			
			
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			///System.out.println("User details is: " + this.toString());	

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security","root","Zamel13");
			if (conn != null) {
				System.out.println("connection OK");
			}
			else {System.out.println("connection NOT ok");};
			Statement myStmt = conn.createStatement();
			System.out.println("setSameSalt:"+Arrays.toString(this.getSalt()));
		
			int x = myStmt.executeUpdate("INSERT INTO user"+"(username,email,password,salt) VALUES "+"('"+this.getfName()+this.getlName()+"','"+this.getEmail()+"','"+this.getPass()+"','"+Arrays.toString(this.getSalt())+"')");

			if(x>0) {
				System.out.println("insertion registration is ok");
				myStmt.close();
				conn.close();
				return true;
			}
			else {
				System.out.println("insertion registration Failed..");
				myStmt.close();
				conn.close();
				return false;
			}
			
		}catch(Exception e1) {System.out.println("insertion connection NOT ok catch"); return false;}
		
		
	}
	private String getSaltString() {
		byte[] bytes = this.getSalt();
		
		return Arrays.toString(bytes);
	}

	
	public User retrunUserFromDB(String userName) {
		System.out.println("return "+userName+" from DB");
		User user = new User();
		String query = "SELECT * FROM user WHERE username = \""+userName+"\";";
			
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security","root","Zamel13");
			if (conn != null) {
				System.out.println("return User connection OK");
			}
			else 
				{System.out.println("return User connection NOT ok");};		
			Statement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery(query);
			
				
				//ResultSet x = myStmt.executeQuery("SELECT * FROM user  WHERE username = "+userName+"\";");
				
				
				//System.out.println(rs.getString("username"));
			while(rs.next()) {
					
				user.setfName(rs.getString(1));
				user.setlName(rs.getString(1));
				user.email = (rs.getString(2));
				user.setSamePass (rs.getString(3));
				user.setSameSalt(rs.getString(5));
				System.out.println("this is string salt: "+(rs.getString(5)));
				System.out.println("this is returned items: "+user.toString());
				}
				
			if(user.fName == null) {
				System.out.println("return user "+userName+" NOT found in DB");
			}
				
			else {
				//System.out.println("this is user: "+user.toString());
					
				myStmt.close();
				conn.close();
				return user;
					
				}
				myStmt.close();
				conn.close();
				return null;		
			
		}
			catch(Exception e1) {System.out.println("return user connection NOT ok catch"); return null;
			}
			}
		

	private void setSameSalt(String string) throws UnsupportedEncodingException {
		byte [] tempByte = new byte[5];
		
			tempByte = string.getBytes("UTF-8");

		
		System.out.println("setSameSalt:"+string.toString());
		System.out.println("setSameSalt byteArr:"+Arrays.toString(tempByte));

		this.salt = tempByte;
		
	}

	public void setSamePass(String string) {
		this.pass = string;
		
	}
	
	
	public Boolean deleteUserFromDB() {
		System.out.println("delete "+this.fName+" from DB");
		String query = "DELETE FROM user WHERE email = \""+this.email+"\";";
			
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security","root","Zamel13");
			
			if (conn != null) {
				System.out.println("delete User connection OK");
			}
			else 
				{System.out.println("delete User connection NOT ok");};		
			Statement myStmt = conn.createStatement();
				
				
			User dbUser = new User();
			dbUser = dbUser.retrunUserFromDB(this.getfName()+this.getlName());
			this.salt = dbUser.salt;
			//String userPass = this.getPass();
			//String DBuserPass = dbUser.getPass();
			System.out.println("this is dbuser: "+dbUser.toString());
			System.out.println("this is user: "+this.toString());
			//System.out.println("this is user "+this.toString());
			//System.out.println("this is DB user "+dbUser.toString());
			
			if(this.passTest(dbUser) ) {
				int x = myStmt.executeUpdate(query);
				if(x == 0) {
					System.out.println("user "+this.fName+" "+this.lName+ " NOT found in DB");
					myStmt.close();
					conn.close();
					return false;	
				}
				System.out.println("this user has been deleted from DB : "+this.toString());
				
				myStmt.close();
				conn.close();
				
				
				return true;
				
				}
				
			else {
				System.out.println("user "+this.fName+" "+this.lName+ " NOT found in DB on password");
				myStmt.close();
				conn.close();
				return false;
				
				
			}
				
			
		}
			catch(Exception e1) {System.out.println("delete user connection NOT ok catch"); return null;}
		
	}

	private boolean passTest(User dbUser) throws NoSuchAlgorithmException {
		System.out.println("pass befor"+this.getPass());
		this.setPass(this.getPass());
		if (this.getPass().equals(dbUser.getPass())) {////////////////////////////////////****************************
		
			return true;
		}
		
		System.out.println("pass after"+this.getPass());
		
		return true;
	}
	
	
	
	
	
}


