package com.journaldev.first;

public class Conf {
	long lenght;
	boolean upCase;
	boolean lowCase;
	boolean sepChar;
	boolean digits;
	long logAtt;
	public int getLenght() {
		return (int) lenght;
	}
	public void setLenght(long object) {
		this.lenght =  object;}
	public boolean isUpCase() {
		return upCase;
	}
	public void setUpCase(Object object) {
		this.upCase = (boolean) object;
	}
	public boolean isLowCase() {
		return lowCase;
	}
	public void setLowCase(Object object) {
		this.lowCase = (boolean) object;
	}
	public boolean isSepChar() {
		return sepChar;
	}
	public void setSepChar(Object object) {
		this.sepChar = (boolean) object;
	}
	public boolean isDigits() {
		return digits;
	}
	public void setDigits(Object object) {
		this.digits = (boolean) object;
	}
	public int getLogAtt() {
		return (int) logAtt;
	}
	public void setLogAtt(long object) {
		this.logAtt =  object;
	}
	@Override
	public String toString() {
		return "Conf [lenght=" + lenght + ", upCase=" + upCase + ", lowCase=" + lowCase + ", sepChar=" + sepChar
				+ ", digits=" + digits + ", logAtt=" + logAtt + "]";
	}
	
	
	

}
