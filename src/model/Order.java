package model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable,Comparable<Order>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum State
	{
		SOLICITED, IN_PROGRESS,SENT,DELIVERED
	}
	//attributes
	private String code;
	private Date dateAndTime;
	private String clientID;
	private String restNit;
	private List<Product> products;
	private List<Integer> quantities;
	private State state;
	/**
	 * creates an instance of the class Order
	 * @param code a String, not empty nor null
	 * @param dateAndTime a Date, not null
	 * @param clientID a String, not empty nor null
	 * @param restNit a String, not empty nor null
	 * @param products a List or Product, not null
	 * @param quantities a List or int, not null
	 */
	public Order(String code, Date dateAndTime, String clientID, String restNit, List<Product> products,
			List<Integer> quantities) {
		this.code = code;
		this.dateAndTime = dateAndTime;
		this.clientID = clientID;
		this.restNit = restNit;
		this.products = products;
		this.quantities = quantities;
		state=State.SOLICITED;
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

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	@Override
	/**
	 * compares an Order to another given order, 
	 * @return an int, positive it it's greater, negative if it's less, 0 if it's equal
	 */
	public int compareTo(Order o) {
		int comp=0;
		comp=getRestNit().compareTo(o.getRestNit());
		if(comp==0)
		{
			comp=getClientID().compareTo(o.getClientID());
			if(comp<0)
			{
				comp=1;
			}
			if(comp>0)
			{
				comp=-1;
			}
		}
		if(comp==0)
		{
			comp=getDateAndTime().compareTo(o.getDateAndTime());
		}
		
		return comp;
	}

}
