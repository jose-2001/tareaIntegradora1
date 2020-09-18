package model;

import java.util.Date;
import java.util.List;

public class Order {
	
	//attributes
	private String code;
	private Date dateAndTime;
	private String clientID;
	private String restNit;
	private List<Product> products;
	private List<Integer> quantities;
	
	/**
	 * @param code
	 * @param dateAndTime
	 * @param clientID
	 * @param restNit
	 * @param products
	 * @param quantities
	 */
	public Order(String code, Date dateAndTime, String clientID, String restNit, List<Product> products,
			List<Integer> quantities) {
		this.code = code;
		this.dateAndTime = dateAndTime;
		this.clientID = clientID;
		this.restNit = restNit;
		this.products = products;
		this.quantities = quantities;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the dateAndTime
	 */
	public Date getDateAndTime() {
		return dateAndTime;
	}

	/**
	 * @param dateAndTime the dateAndTime to set
	 */
	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	/**
	 * @return the clientID
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	/**
	 * @return the restNit
	 */
	public String getRestNit() {
		return restNit;
	}

	/**
	 * @param restNit the restNit to set
	 */
	public void setRestNit(String restNit) {
		this.restNit = restNit;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * @return the quantities
	 */
	public List<Integer> getQuantities() {
		return quantities;
	}

	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}

}
