package model;

public class Client {

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
	 * @param idn
	 * @param fn
	 * @param p
	 * @param a
	 * @param idt
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
	 * @param fullName the fullName to set
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
	 * @return the idType
	 */
	public IdType getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(IdType idType) {
		this.idType = idType;
	}
	
}
