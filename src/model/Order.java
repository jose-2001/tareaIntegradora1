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
	
}
