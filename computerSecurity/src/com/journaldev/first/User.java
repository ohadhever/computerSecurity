package com.journaldev.first;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.media.jfxmedia.track.Track.Encoding;
import java.util.Scanner;      // Required for the scanner
import java.io.BufferedReader;
import java.io.File;    
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;

public class User {

	String fName;
	String lName;
	String email;
	String pass;
	String secPass;
	String trdPass;
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
		return this.email;
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
	
	
	public void setEmail(String newEmail) {
		this.email=newEmail;
	}
	
	public String getPass() {
		return pass;
	}
	
	public boolean setPass(String pass) throws NoSuchAlgorithmException {
		
		Conf conf = getConf();
		
		if (!passCheck(pass, conf)) {
			System.out.println("Password is not valid!");
			return false;
			}
		
		MessageDigest digest = MessageDigest.getInstance("SHA256");
		digest.reset();
		digest.update(this.getSalt());
		System.out.println("this is user salt: "+this.toString());
		byte[] hash = digest.digest(pass.getBytes());
		this.pass = bytesToStringHex(hash);
			return true;
			
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
		return "User [fName=" + fName + ", lName=" + lName + ", email=" + email + ", pass=" + pass + ", secPass="
				+ secPass + ", trdPass=" + trdPass + ", salt=" + Arrays.toString(salt) + "]";
	}

	public String getSecPass() {
		return secPass;
	}

	public void setSecPass(String secPass) {
		this.secPass = secPass;
	}

	public String getTrdPass() {
		return trdPass;
	}

	public void setTrdPass(String trdPass) {
		this.trdPass = trdPass;
	}

	public Boolean insertUserToDB() {
		try {
					
			//System.out.println("INSERT INTO user"+"(username,email,password) VALUES "+"('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"')");
			
			if (retrunUserFromDB(this.getfName()+this.getlName()) != null) {System.out.println("this user is alredy exist in DB"); return false;}
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			///System.out.println("User details is: " + this.toString());	

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security","root","Zamel13");
			if (conn != null) {
				System.out.println("connection OK");
			}
			else {System.out.println("connection NOT ok");};
			Statement myStmt = conn.createStatement();
			System.out.println("setSameSalt - insert:"+Arrays.toString(this.getSalt()));
			
			if (this.getPass() == null) {System.out.println("pass invalid - insert"); return false;}
			
			int x = myStmt.executeUpdate("INSERT INTO user"+"(username,email,password,salt,secPass,trdPass) VALUES "+"('"+this.getfName()+this.getlName()+"','"+this.getEmail()+"','"+this.getPass()+"','"+Arrays.toString(this.getSalt())+"','"+this.getSecPass()+"','"+this.getTrdPass()+"')");

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
				user.setEmail(rs.getString(2));
				user.setSamePass (rs.getString(3));
				user.setSameSalt(rs.getString(5));
				user.setSecPass(rs.getString(6));
				user.setTrdPass(rs.getString(7));
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
		char [] charArr;
		int sign = 1;
		int sum = 0,byteIndex = 0;
		charArr = string.toCharArray();
		for (int i = 1 ; i < charArr.length ; i ++) {
			if (charArr[i] == '-') {
				sign = -1;
			}
			
			if (charArr[i] == ',') {
				tempByte[byteIndex] = (byte) (sum*sign);
				sign = 1;
				sum = 0;
				byteIndex++;
			}
			
			sum += charArr[i];
		}
		
		System.out.println("setSameSalt:"+string.toString());
		System.out.println("setSameSalt byteArr:"+Arrays.toString(tempByte));

		this.salt = tempByte;
		
	}

	public void setSamePass(String string) {
		this.pass = string;
		
	}
	
	
	public Boolean deleteUserFromDB() {
		System.out.println("delete "+this.fName+" from DB");
		String query = "DELETE FROM user WHERE username = \""+this.fName+this.lName+"\";";
			
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
			this.setEmail(dbUser.getEmail());
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
		//System.out.println("pass befor"+this.getPass());
		this.setPass(this.getPass());
		if (this.getPass().equals(dbUser.getPass())) {
		
			return true;
		}
		
		//System.out.println("pass after"+this.getPass());
		
		return false;
	}
	
	private boolean passCheck (String pass, Conf conf) {
		System.out.println("this is pass - passCheck:" +pass);
		
		boolean hasdigits = (!conf.isDigits());
		boolean isUpper = (!conf.isUpCase()); 
		boolean isLower = (!conf.isLowCase());
		boolean isSpecial = (!conf.isSepChar());
		boolean isDictionary = true;
		
		if (pass.length() < conf.getLenght()) {
			System.out.println("the pass is too short");
			return false;
		}
		
		hasdigits = pass.matches(".*\\d.*");
		isSpecial = pass.matches(".*[!@#$%^&*].*");
		
		char ch;
		
		for (int i = 0 ; i < pass.length() ; i ++) {
			ch = pass.charAt(i);
			if (Character.isLowerCase(ch)) isLower = true;
			if (Character.isUpperCase(ch)) isUpper = true;	
		}
		String line;
		
		try {
			Scanner scanner = new Scanner(new File("C:\\Users/ohad/git/computerSecurity/computerSecurity/dictionary.txt"));
			while (scanner.hasNextLine() && isDictionary == true) {
				line = scanner.nextLine();
				if(line.equals(pass)) {
					isDictionary = false;
					System.out.println(line +" compere "+ isDictionary);
				}
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	    
		
		
		System.out.println("this is hasdigits, isUpper, isLower, isSpecial , isDictionary: "+hasdigits + isUpper + isLower + isSpecial + isDictionary);
		
		return hasdigits & isUpper & isLower & isSpecial & isDictionary;
		
	}
	
	public Conf getConf() {
		
		Conf conf = new Conf();
		
	
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("C:\\Users/ohad/git/computerSecurity/computerSecurity/src/conf.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			conf.setUpCase(jsonObject.get("upCase"));
			conf.setLowCase(jsonObject.get("lowCase"));
			conf.setSepChar(jsonObject.get("speChar"));
			conf.setDigits(jsonObject.get("digits"));
			conf.setLenght((long) jsonObject.get("length"));
			conf.setLogAtt((long) jsonObject.get("logAtt"));
			
			System.out.println("this is conf: "+ conf.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conf;
		}
	
	public Boolean insertPassUserToDB() {
		try {
					
			//System.out.println("INSERT INTO user"+"(username,email,password) VALUES "+"('"+textField.getText()+"','"+textField_1.getText()+"','"+textField_2.getText()+"')");
			String newSecPass =this.getPass();
			String newTrdPass = this.getTrdPass();
			
	
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			///System.out.println("User details is: " + this.toString());	

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/security","root","Zamel13");
			if (conn != null) {
				System.out.println("connection OK");
			}
			else {System.out.println("connection NOT ok");};
			Statement myStmt = conn.createStatement();
			
			this.deleteUserFromDB();
			
			int x = myStmt.executeUpdate("INSERT INTO user"+"(username,email,password,salt,secPass,trdPass) VALUES "+"('"+this.getfName()+this.getlName()+"','"+this.getEmail()+"','"+this.getPass()+"','"+Arrays.toString(this.getSalt())+"','"+newSecPass+"','"+newTrdPass+"')");            
			
			if(x>0) {
				System.out.println("insertion - new Password - registration is ok");
				myStmt.close();
				conn.close();
				return true;
			}
			else {
				System.out.println("insertion - new Password - registration Failed..");
				myStmt.close();
				conn.close();
				return false;
			}
			
		}catch(Exception e1) {System.out.println("insertion - new Password - co nection NOT ok catch"); return false;}
		
		
	}
}


