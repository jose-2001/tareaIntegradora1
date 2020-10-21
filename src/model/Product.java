package model;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//attributes
	private String code;
	private String name;
	private String description;
	private double cost;
	private String restNit;
	
	
	//methods 
	
	/**
	 * creates an instance of the class Product
	 * @param cd  a String, not empty nor null
	 * @param n  a String, not empty nor null
	 * @param d a String, not empty nor null
	 * @param ct a double, positive
	 * @param rn a String, not empty nor null
	 */
	public Product(String cd, String n, String d, double ct, String rn) {
		code = cd;
		name = n;
		description = d;
		cost = ct;
		restNit = rn;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
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


	@Override
	public int compareTo(Product o) {
		int comp=0;
		comp=getCode().compareTo(o.getCode());
		return comp;
	}


	@Override
	public String toString() {
		String msg="RestNit:"+getRestNit()+"|Code:"+code+"|Name:"+name;
		return msg;
	}	
	
}
