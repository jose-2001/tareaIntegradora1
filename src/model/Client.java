package model;

import java.io.Serializable;

public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//constants
	public enum IdType
	{
		IC, CC, FC, P
	}
	
	//attributes
	
	private String idNum;
	private String firstName;
	private String surName;
	private String phone;
	private String address;
	private IdType idType;
	/**
	 * creates an instance of the class Client
	 * @param idn a String, not empty nor null
	 * @param fn a String, not empty nor null
	 * @param sn a String, not empty nor null
	 * @param p a String, not empty nor null
	 * @param a a String, not empty nor null
	 * @param idt an int, 1,2,3 or 4
	 */
	public Client(String idn, String fn, String sn,String p, String a, int idt) {
		idNum = idn;
		firstName = fn;
		surName=sn;
		phone = p;
		address = a;
		switch(idt) {
		case 1:
			idType=IdType.IC;
			break;
		case 2:
			idType=IdType.CC;
			break;
		case 3:
			idType=IdType.FC;
			break;
		case 4:
			idType=IdType.P;
			break;
			
		}
	}
	/**
	 * @return the idNum
	 */
	public String getIdNum() {
		return idNum;
	}
	/**
	 * @param idNum the idNum to set
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	/**
	 * @return the fullName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}
	/**
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return an int representing the idType
	 */
	public int getIdType() {
		if(idType==IdType.IC){return 1;}
		else {if(idType==IdType.CC){return 2;}	
		else{if(idType==IdType.FC){return 3;}
		else{return 4;}}}
	}
	/**
	 * @param idt the idType to set
	 */
	public void setIdType(int idt) {
		switch(idt){
		case 1: idType=IdType.IC;break;
		case 2: idType=IdType.CC;break;
		case 3: idType=IdType.FC;break;
		case 4: idType=IdType.P;break;
		}
		
	}
	
}
