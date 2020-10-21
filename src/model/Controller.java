package model;
import java.util.List;
import exceptions.ClientDoesNotExistException;
import exceptions.ClientAlreadyExistsException;
import exceptions.OrderAlreadyExistsException;
import exceptions.OrderDoesNotExistException;
import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductAlreadyExistsException;
import exceptions.RestaurantAlreadyExistsException;
import exceptions.ProductsAreNotOfTheSameRestaurantException;
import exceptions.RestaurantDoesNotExistException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Controller {

	// constants
	public static final String CLIENTS_FILE_NAME = "data/clients.cli";
	public static final String RESTAURANTS_FILE_NAME = "data/restaurants.res";
	public static final String ORDERS_FILE_NAME = "data/orders.ord";
	public static final String PRODUCTS_FILE_NAME = "data/products.pro";
	public static final String CSV_EXPORT_FILE_NAME = "data/ordersReport.csv";
	public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy"; 
	public static final String IMPORT_SEPARATOR = ",";
	public static final String OUTPUT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

	// relations

	private List<Restaurant> restaurants;
	private List<Client> clients;
	private List<Product> products;
	private List<Order> orders;

	// methods

	/** 
	 * creates an instance of the class Controller, if possible,  loads data available
	 * 
	 */
	public Controller() {

		restaurants = new ArrayList<Restaurant>();
		File fr = new File(RESTAURANTS_FILE_NAME);

		if (fr.exists()) {
			try {
				loadRestaurants();
			} catch (IOException ioe) {
				System.err.println("Restaurant data could not be loaded properly");
				ioe.printStackTrace();
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("The class was not found");
			}
		}
		clients = new ArrayList<Client>();
		File fc = new File(CLIENTS_FILE_NAME);

		if (fc.exists()) {
			try {
				loadClients();
			} catch (IOException ioe) {
				System.err.println("Client data could not be loaded properly");
				ioe.printStackTrace();
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("The class was not found");
			}
		}
		products = new ArrayList<Product>();
		File fp = new File(RESTAURANTS_FILE_NAME);

		if (fp.exists()) {
			try {
				loadProducts();
			} catch (IOException ioe) {
				System.err.println("Product data could not be loaded properly");
				ioe.printStackTrace();
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("The class was not found");
			}
		}
		orders = new ArrayList<Order>();
		File fo = new File(RESTAURANTS_FILE_NAME);

		if (fo.exists()) {
			try {
				loadOrders();
			} catch (IOException ioe) {
				System.err.println("Order data could not be loaded properly");
				ioe.printStackTrace();
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("The class was not found");
			}
		}
	}

	/**
	 * loads the information available of the restaurants
	 * @throws IOException if it cannot read the file properly
	 * @throws ClassNotFoundException if it cannot find the class
	 */
	@SuppressWarnings("unchecked")
	private void loadRestaurants() throws IOException, ClassNotFoundException {
		File f = new File(RESTAURANTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      restaurants = (List<Restaurant>)ois.readObject();
	      ois.close();
	}
	/**
	 * loads the information available of the clients
	 * @throws IOException if it cannot read the file properly
	 * @throws ClassNotFoundException if it cannot find the class
	 */
	@SuppressWarnings("unchecked")
	private void loadClients() throws IOException, ClassNotFoundException {
		File f = new File(CLIENTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      clients = (ArrayList<Client>)ois.readObject();
	      ois.close();
	}
	/**
	 * loads the information available of the products
	 * @throws IOException if it cannot read the file properly
	 * @throws ClassNotFoundException if it cannot find the class
	 */
	@SuppressWarnings("unchecked")
	private void loadProducts() throws IOException, ClassNotFoundException {
		File f = new File(PRODUCTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      products = (ArrayList<Product>)ois.readObject();
	      ois.close();
	}
	/**
	 * loads the information available of the orders
	 * @throws IOException if it cannot read the file properly
	 * @throws ClassNotFoundException if it cannot find the class
	 */
	@SuppressWarnings("unchecked")
	private void loadOrders() throws IOException, ClassNotFoundException {
		File f = new File(ORDERS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      orders = (ArrayList<Order>)ois.readObject();
	      ois.close();
	}
	/**
	 * saves the information of the restaurants
	 * @throws IOException if it cannot write the file properly
	 */
	private void saveRestaurants() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESTAURANTS_FILE_NAME));
	    oos.writeObject(restaurants);
	    oos.close();
	}
	/**
	 * saves the information of the clients
	 * @throws IOException if it cannot write the file properly
	 */
	private void saveClients() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE_NAME));
	    oos.writeObject(clients);
	    oos.close();
	}
	/**
	 * saves the information of the orders
	 * @throws IOException if it cannot write the file properly
	 */
	private void saveOrders() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE_NAME));
	    oos.writeObject(orders);
	    oos.close();
	}
	/**
	 * saves the information of the products
	 * @throws FileNotFoundException if it cannot find the file where it is supposed to save
	 * @throws IOException if it cannot write the file properly
	 */
	private void saveProducts() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE_NAME));
	    oos.writeObject(products);
	    oos.close();
	}

	/**
	 * @return the restaurants
	 */
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	/**
	 * @param res the restaurants to set
	 */
	public void setRestaurants(List<Restaurant> res) {
		restaurants = res;
	}

	/**
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param cli the clients to set
	 */
	public void setClients(List<Client> cli) {
		clients = cli;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param prod the products to set
	 */
	public void setProducts(List<Product> prod) {
		products = prod;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param ord the orders to set
	 */
	public void setOrders(List<Order> ord) {
		orders = ord;
	}

	/**
	 * creates and adds and instance of the class Restaurant to the list of restaurants
	 * @param name a String, not null not empty
	 * @param nit a String, not null not empty
	 * @param adminName a String, not null not empty
	 * @throws FileNotFoundException if cannot find the file where it is supposed to save data after adding the restaurant
	 * @throws IOException  if it cannot write the file properly while saving after adding the restaurant
	 */
	public void registerRestaurant(String name, String nit, String adminName) throws FileNotFoundException, IOException {
		Restaurant toAdd = new Restaurant(name, nit, adminName);
		if(restaurants.isEmpty()) {
			restaurants.add(toAdd);
		}
		else {
			int i =0;
			while(i<restaurants.size()&&toAdd.getName().compareTo(restaurants.get(i).getName())>0)
			{
				i++;
			}
			((ArrayList<Restaurant>)restaurants).add(i,toAdd);
		}
		saveRestaurants();
	}

	/**
	 * checks if a restaurant exists given its NIT
	 * @param nit a String, not null not empty
	 * @return true if it exists, false if not
	 */
	public boolean checkNit(String nit) {
		boolean found=false;
		for(int i=0; i<restaurants.size();i++)
		{
			if(restaurants.get(i).getNit().equals(nit))
			{
				found=true;
			}
		}
		return found;
	}
	
	/**
	 * checks if a product exists given its code
	 * @param c a String, not null not empty
	 * @return true if it exists, false if not
	 */
	public boolean checkProdCode(String c) {
		boolean found=false;
		for(int i=0; i<products.size();i++)
		{
			if(products.get(i).getCode().equals(c))
			{
				found=true;
			}
		}
		return found;
	}
	/**
	 * creates and adds and instance of the class Product to the list of products
	 * @param name  a String, not null not empty
	 * @param code a String, not null not empty
	 * @param description a String, not null not empty
	 * @param cost a double, positive
	 * @param restNit a String, not null not empty
	 * @throws FileNotFoundException if cannot find the file where it is supposed to save data after adding the product
	 * @throws IOException if it cannot write the file properly while saving after adding the product
	 */
	public void registerProduct(String name, String code, String description, double cost, String restNit) throws FileNotFoundException, IOException {
		Product toAdd = new Product(code, name, description, cost,restNit);
		if(products.isEmpty()) {
			products.add(toAdd);
		}
		else {
			int i =0;
			while(i<products.size()&&toAdd.getCode().compareTo(products.get(i).getCode())>0)
			{
				i++;
			}
			((ArrayList<Product>)products).add(i,toAdd);
		}
		saveProducts();
	}

	/**
	 * checks if a client exists given its ID number
	 * @param idn a String, not null nor empty
	 * @return true if it exists, false if not
	 */
	public boolean checkId(String idn) {
		boolean found=false;
		for(int i=0; i<clients.size();i++)
		{
			if(clients.get(i).getIdNum().equals(idn))
			{
				found=true;
			}
		}
		return found;
	}
	/**
	 * creates and adds and instance of the class Client to the list of clients
	 * @param idn a String, not null nor empty
	 * @param fn a String, not null nor empty
	 * @param sn a String, not null nor empty
	 * @param p a String, not null nor empty
	 * @param a a String, not null nor empty
	 * @param idt an int, 1,2,3 or 4
	 * @throws IOException if it cannot write the file properly while saving after adding the client
	 */
	public void registerClient(String idn, String fn,String sn, String p, String a, int idt) throws IOException {
		Client toAdd = new Client(idn, fn,sn, p, a, idt);
		if(clients.isEmpty()) {
			clients.add(toAdd);
		}
		else {
			int i =0;
			while(i<clients.size()&&toAdd.getSurName().compareTo(clients.get(i).getSurName())>0)
			{
				i++;
			}
			while(i<clients.size()&&toAdd.getFirstName().compareTo(clients.get(i).getFirstName())>0)
			{
				i++;
			}
			((ArrayList<Client>)clients).add(i,toAdd);
		}
		saveClients();
	}
	/**
	 * gives a String with the products of a restaurant given its NIT
	 * @param nitRes a String, the NIT of a registered restaurant
	 * @return a String containing the information of the products of the restaurant
	 */
	public String showProductsFromRestaurant(String nitRes) {
		String msg="";
		for(int i=0;i<products.size();i++)
		{
			if(products.get(i).getRestNit().equals(nitRes))
			{
				msg+="Name: "+products.get(i).getName()+". Code: "+products.get(i).getCode()+"\n";
			}
		}
		return msg;
	}
	/**
	 * checks if the Product to be added belongs to the restaurant the order is being made to
	 * @param restNit a String, not null, the NIT of the restaurant the order is being made to
	 * @param codeOfProd a String, not null, the code of the product to be checked 
	 * @throws ProductDoesNotBelongToRestaurant if the product does not belong to the same restaurant the order is being made to
	 * @throws ProductDoesNotExistException if there is not a product registered with that code
	 */
	public void checkProdToAdd(String restNit, String codeOfProd) throws ProductDoesNotBelongToRestaurant, ProductDoesNotExistException {
		boolean found =false;
		for(int i=0;i<products.size();i++)
		{
			if(products.get(i).getCode().equals(codeOfProd))
			{
				found=true;
				if(!(products.get(i).getRestNit().equals(restNit)))
				{
					throw new ProductDoesNotBelongToRestaurant();
				}
			}
		}
		if(!found)
		{throw new ProductDoesNotExistException();}
	}
	/**
	 * creates and adds and instance of the class Order to the list of orders
	 * @param code a String,not null nor empty
	 * @param idn a String,not null nor empty
	 * @param nitRes a String,not null nor empty
	 * @param codes a List of String, not null
	 * @param quantities a List of int, not null
	 * @param date a Date, not null
	 * @throws ProductsAreNotOfTheSameRestaurantException if the products are not of the same restaurant as the one the order is being made to
	 * @throws FileNotFoundException if cannot find the file where it is supposed to save data after adding the order
	 * @throws IOException  if it cannot write the file properly while saving after adding the order
	 */
	public void registerOrder(String code,String idn, String nitRes, List<String> codes, List<Integer> quantities, Date date) throws ProductsAreNotOfTheSameRestaurantException,FileNotFoundException, IOException {
		ArrayList<Product> prodsOfOrder = new ArrayList<Product>();
		for(int i=0;i<codes.size(); i++)
		{
			for(int j=0;j<products.size();j++) {
				if(codes.get(i).equals(products.get(j).getCode()))
				{
					prodsOfOrder.add(products.get(j));
				}
			}
		}
		checkProdsOfSameRes(nitRes, prodsOfOrder);
		Order toAdd = new Order(code, date, idn, nitRes,prodsOfOrder, quantities);
		orders.add(toAdd);
		saveOrders();
	}
	/**
	 * checks if the products of the given ArrayList belong to the same restaurant 
	 * @param nitRes a String, not empty not null, the NIT of the restaurant to which the products should belong to
	 * @param prodsOfOrder an ArrayList of Product, not empty nor null
	 * @throws ProductsAreNotOfTheSameRestaurantException  if the products do not belong to the restaurant
	 */
	public void checkProdsOfSameRes(String nitRes, ArrayList<Product> prodsOfOrder) throws ProductsAreNotOfTheSameRestaurantException {
		for (int i = 0; i < prodsOfOrder.size(); i++) {
			if(!(prodsOfOrder.get(i).getRestNit().equals(nitRes)))
			{
				System.err.println(nitRes);
				System.err.println(prodsOfOrder.get(i));
				throw new ProductsAreNotOfTheSameRestaurantException();
			}
		}
		
	}
	/**
	 * checks if the given code already exists among the order codes of the orders already registered
	 * @param code a String, the code to be checked
	 * @return true if it exists, false if not
	 */
	public boolean checkIfCodeIsRepeated(String code) {
		boolean result=false;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				result=true;
			}
		}
		return result;
	}

	/**
	 * updates a restaurant's administrator name
	 * @param restNit a String, not null the NIT of the restaurant to update
	 * @param newAdminName a String, not null 
	 * @throws IOException if it cannot write the file properly while saving after updating the restaurant
	 */
	public void updateRestaurantAdminName(String restNit, String newAdminName) throws IOException {
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				Restaurant newData=restaurants.get(i);
				newData.setAdminName(newAdminName);
				restaurants.set(i,newData);
			}
		}
		saveRestaurants();
	}
	/**
	 * updates a restaurant's name
	 * @param restNit a String, not null the NIT of the restaurant to update
	 * @param newRestName a String, not null 
	 * @throws IOException if it cannot write the file properly while saving after updating the restaurant
	 */
	public void updateRestaurantName(String restNit, String newRestName) throws IOException {
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				Restaurant newData=restaurants.get(i);
				newData.setName(newRestName);
				restaurants.set(i,newData);
			}
		}
		saveRestaurants();
	}
	/**
	 * updates a restaurant's NIT
	 * @param restNit a String, not null the NIT of the restaurant to update
	 * @param newRestNit a String, not null 
	 * @throws IOException if it cannot write the file properly while saving after updating the restaurant
	 */
	public void updateRestaurantNit(String restNit, String newRestNit) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getRestNit().equals(restNit))
			{
				Order newData=orders.get(i);
				newData.setRestNit(newRestNit);
				orders.set(i,newData);
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestNit().equals(restNit))
			{
				Product newData=products.get(i);
				newData.setRestNit(newRestNit);
				products.set(i,newData);
			}
		}
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				Restaurant newData=restaurants.get(i);
				newData.setNit(newRestNit);
				restaurants.set(i,newData);
			}
		}
		saveOrders();
		saveProducts();
		saveRestaurants();
	}
	/**
	 * updates a product's code 
	 * @param code a String, not null, the code of the product to update
	 * @param newCode a String, not null 
	 * @throws IOException if it cannot write the file properly while saving after updating the product
	 */
	public void updateProductCode(String code, String newCode) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.size(); j++) {
				if (orders.get(i).getProducts().get(j).getCode().equals(code)) {
					
					Product newData=orders.get(i).getProducts().get(j);
					newData.setCode(newCode);
					List<Product> newOrderProducts=orders.get(i).getProducts();
					for (int k = 0; k < newOrderProducts.size(); k++) {
						if(newOrderProducts.get(k).getCode().equals(code))
						{
							newOrderProducts.set(k,newData);
						}
					}
					Order newOrderWithNewData = orders.get(i);
					newOrderWithNewData.setProducts(newOrderProducts);
					orders.set(i,newOrderWithNewData);
				}
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				Product newData=products.get(i);
				newData.setCode(newCode);
				products.set(i,newData);
			}
		}
		saveOrders();
		saveProducts();
	}
	/**
	 * updates a product's cost
	 * @param code a String, not null, the code of the product to update
	 * @param newCost a double, positive
	 * @throws IOException if it cannot write the file properly while saving after updating the product
	 */
	public void updateProductCost(String code, double newCost) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				Product newData=products.get(i);
				newData.setCost(newCost);
				products.set(i,newData);
			}
		}
		saveProducts();
	}
	/**
	 * updates a product's description
	 * @param code a String, not null, the code of the product to update
	 * @param newDesc a String, not null 
	 * @throws IOException if it cannot write the file properly while saving after updating the product
	 */
	public void updateProductDescription(String code, String newDesc) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				Product newData=products.get(i);
				newData.setDescription(newDesc);
				products.set(i,newData);
			}
		}
		saveProducts();
	}
	/**
	 * updates a product's name
	 * @param code a String, not null, the code of the product to update
	 * @param newName a String, not null 
	 * @throws IOException  if it cannot write the file properly while saving after updating the product
	 */
	public void updateProductName(String code, String newName) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				Product newData=products.get(i);
				newData.setName(newName);
				products.set(i,newData);
			}
		}
		saveProducts();
	}
	/**
	 * updates a product's NIT of the restaurant offering it 
	 * @param code a String, not null, the code of the product to update
	 * @param newRestNit a String, not null, the NIT of a registered restaurant
	 * @throws IOException if it cannot write the file properly while saving after updating the product
	 */
	public void updateProductRestNit(String code, String newRestNit) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				Product newData=products.get(i);
				newData.setRestNit(newRestNit);
				products.set(i,newData);
			}
		}
		saveProducts();
	}

	/**
	 * updates a client's address 
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newClientAddress a String, not null nor empty
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientAdress(String idn, String newClientAddress)throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				Client newData=clients.get(i);
				newData.setAddress(newClientAddress);
				clients.set(i,newData);
			}
		}
		saveClients();
	}
	/**
	 * updates a client's first name
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newClientFirstName a String, not null nor empty
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientFirstName(String idn, String newClientFirstName) throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setFirstName(newClientFirstName);	
			}
		}
		saveClients();
	}
	/**
	 * updates a client's surname
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newClientSurname a String, not null nor empty
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientSurname(String idn, String newClientSurname) throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setSurName(newClientSurname);
			}
		}
		saveClients();
	}
	/**
	 * updates a client's ID number
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newClientIdNum a String, not null nor empty
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientIdNum(String idn, String newClientIdNum)  throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getClientID().equals(idn))
			{
				orders.get(i).setClientID(newClientIdNum);
			}
		}

		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setIdNum(newClientIdNum);
			}
		}
		saveOrders();
		saveClients();
	}
	/**
	 * updates a client's ID Type
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newIdt a String, not null, the new ID type of the client
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientIdType(String idn, int newIdt)  throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setIdType(newIdt);
			}
		}
		saveClients();
	}
	/**
	 * updates a client's phone
	 * @param idn a String, not null, the ID number of the client to update
	 * @param newClientPhone a String, not null nor empty
	 * @throws IOException if it cannot write the file properly while saving after updating the client
	 */
	public void updateClientPhone(String idn, String newClientPhone) throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setPhone(newClientPhone);
			}
		}
		saveClients();
	}
	/**
	 * checks if an order exists given its code
	 * @param code a String not empty nor null
	 * @return true  if it exists, false if not
	 */
	public boolean checkOrdCode(String code) {
		boolean found=false;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				found=true;
			}
		}
		return found;
	}
	/**
	 * updates an order's client's ID number
	 * @param code a String not empty nor null, identifies the order
	 * @param newOrderClientID a String not empty nor null
	 * @throws IOException if it cannot write the file properly while saving after updating the order
	 */
	public void updateOrderClientID(String code, String newOrderClientID) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				orders.get(i).setClientID(newOrderClientID);
			}
		}
		saveOrders();
	}
	/**
	 * returns the NIT if the restaurant to which the order with the code provided was made
	 * @param code a String, not null, the code of the order to be checked, the order and its restaurant must have been registered already
	 * @return a String, the NIT of the restaurant to which the order was made
	 */
	public String getRestNitOfOrder(String code) {
		String result="";
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				result= orders.get(i).getRestNit();
			}
		}
		return result;
	}
	/**
	 * updates an order's products and quantities
	 * @param code a String not empty nor null, identifies the order 
	 * @param codes a List of Strings, not null, contains the codes of the new products of the order
	 * @param quantities a List of int, not null, contains the quantities of the new products of the order
	 * @param nitRes a String not empty nor null, the NIT of the restaurant to which the order with the code provided was made
	 * @throws ProductsAreNotOfTheSameRestaurantException  if the products are not of the same restaurant as the one the order is being made to
	 * @throws IOException if it cannot write the file properly while saving after updating the order
	 */
	public void updateOrderProductsAndQuantities(String code, List<String> codes, List<Integer> quantities, String nitRes) throws ProductsAreNotOfTheSameRestaurantException, IOException {
		ArrayList<Product> prodsOfOrder = new ArrayList<Product>();
		for(int i=0;i<codes.size(); i++)
		{
			for(int j=0;j<products.size();j++) {
				if(codes.get(i).equals(products.get(j).getCode()))
				{
					prodsOfOrder.add(products.get(j));
				}
			}
		}
		checkProdsOfSameRes(nitRes, prodsOfOrder);
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				orders.get(i).setProducts(prodsOfOrder);
				orders.get(i).setQuantities(quantities);				
			}
		}
		saveOrders();
	}
	/**
	 * updates an order's NIT of the restaurant that orders it
	 * @param code a String not empty nor null, identifies the order
	 * @param newRestNit the new NIT of the restaurant to which the order with the code provided was made
	 * @throws IOException  if it cannot write the file properly while saving after updating the order
	 */
	public void updateOrderRestNit(String code, String newRestNit) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				orders.get(i).setRestNit(newRestNit);
			}
		}
		saveOrders();
	}
	/**
	 * returns a String with the possible further states for the order with the code given
	 * @param code a String not empty nor null, identifies the order
	 * @return a String with the possible further states for the order with the code given
	 */
	public String getFollowingStatesText(String code) {
		String result="";
		Order.State currentState = null;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				currentState=orders.get(i).getState();
			}
		}		
		switch(currentState)
		{
		case DELIVERED:
			result="There are no further states";
			break;
		case IN_PROGRESS:
			result="Type 1 for Sent\n"+"Type 2 for Delivered\n";
			break;
		case SENT:
			result="Type 1 for Delivered\n";
			break;
		case SOLICITED:
			result="Type 1 for In Progress\n" + "Type 2 for Sent\n"+"Type 3 for Delivered\n";
			break;
		default:
			break;
		
		}
		result+="&"+currentState;
		return result;
	}
	/**
	 * updates an order's state
	 * @param code a String not empty nor null, identifies the order to update
	 * @param dec an int, 1,2 or 3 identifying the new state of the order
	 * @param stateInt an int, 1,2,3 or 4 identifying the current state of the order
	 * @throws IOException if it cannot write the file properly while saving after updating the order
	 */
	public void updateOrderState(String code, int dec, int stateInt) throws IOException {
		Order.State newState=null;
		switch(stateInt)
		{
		case 1:
			switch(dec)
			{
			case 1:
				newState=Order.State.IN_PROGRESS;
				break;
			case 2:
				newState=Order.State.SENT;
				break;
			case 3: 
				newState=Order.State.DELIVERED;
				break;
			}
			break;
		case 2:
			switch(dec)
			{
			case 1: 
				newState=Order.State.SENT;
				break;
			case 2: 
				newState=Order.State.DELIVERED;
				break;
			}
			break;
		case 3:
			newState=Order.State.DELIVERED;
			break;
		case 4:
			break;
		}
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				orders.get(i).setState(newState);
			}
		}
		saveOrders();
	}
	/**
	 * generates a .csv file with all the information of every order
	 * @param s a char, the separator to be used to separate the information int the file
	 * @throws FileNotFoundException if the file in which the report is to be made cannot be found
	 */
	public void generateOrdersReport(char s) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(CSV_EXPORT_FILE_NAME);
		pw.println("OrderCode"+s+"OrderState"+s+
					"RestaurantNit"+s+"RestaurantAdminName"+s+"RestaurantName"+s+
					"ClientIDNumber"+s+"ClientIDType"+s+"ClientAddress"+s+"ClientFirstName"+s+"Client Surname"+s+"ClientPhone"+s+
					"DateOfOrder"+s+
					"ProductCode"+s+"ProductQuantity"+s+"ProductCost"+s+"ProductName"+s+"ProductDescription"
				);
		Collections.sort(orders);
		for(int i =0; i<orders.size();i++)
		{
				for (int j = 0; j < orders.get(i).getProducts().size(); j++) 
				{
					Order currentOr = orders.get(i);
					ArrayList<Product> currentOrderProds = (ArrayList<Product>) currentOr.getProducts();
					Collections.sort(currentOrderProds);
					Product currentProduct=currentOrderProds.get(j);
					int currentProductQuantity =0;
					for(int k=0;k<currentOr.getProducts().size();k++)
					{
						if (currentOr.getProducts().get(k).equals(currentProduct))
						{
							currentProductQuantity =currentOr.getQuantities().get(k);
						}
					}
					String prodInfoText =currentProduct.getCode()+s
							+currentProductQuantity+s+currentProduct.getCost()+s
							+currentProduct.getName()+s+currentProduct.getDescription();
					pw.println(getOrderInfoText(s,currentOr)+prodInfoText);	
				}
		}
		pw.close();
	}
	/**
	 * generates a String with the information of an order
	 * @param s a char, the separator to be used to separate the information int the file
	 * @param o the order which's information should be retrieved
	 * @return a String with the information of the order provided
	 */
	public String getOrderInfoText(char s, Order o){
		String result="";
		DateFormat df = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
		result=o.getCode()+s+o.getState()+s+o.getRestNit()+s;
		Restaurant or =null;
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(o.getRestNit()))
			{
				or=restaurants.get(i);
			}
		}
		result+=or.getAdminName()+s+or.getName()+s;
		Client oc =null;
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(o.getClientID()))
			{
				oc=clients.get(i);
			}
		}
		result+=oc.getIdNum()+s;
		switch(oc.getIdType()) {
		case 1:
			result+="IC";
			break;
		case 2:
			result+="CC";
			break;
		case 3:
			result+="FC";
			break;
		case 4:
			result+="P";
			break;
		}

		result+=s+oc.getAddress()+s+oc.getFirstName()+s+oc.getSurName()+s+oc.getPhone()+s
				+df.format(o.getDateAndTime())+s;
		
		return result;
	}
	/**
	 * generates a String with the information of the restaurants registered
	 * @return a String with the information of the restaurants registered
	 */
	public String showRestaurants() {
		String msg="Showing Restaurants:\n";
		for (int i = 0; i < restaurants.size(); i++) {
			msg+=(i+1)+"Name: "+restaurants.get(i).getName()
					+". NIT: "+restaurants.get(i).getNit()
					+". Admin Name: "+restaurants.get(i).getAdminName()+"\n";
		}
		return msg;
	}
	/**
	 * generates a String with the information of the clients registered
	 * @return a String with the information of the clients registered
	 */
	public String showClients() {
		ArrayList<Client> clientsOrderedByPhone = (ArrayList<Client>) clients;
		String msg="Showing clients:\n";
		PhoneComparator pc = new PhoneComparator();
		Collections.sort(clientsOrderedByPhone,pc);
		for (int i = 0; i < clientsOrderedByPhone .size(); i++) {
			Client cc = clientsOrderedByPhone.get(i);
			msg+=(i+1)+"Phone: "+cc.getPhone()+". ID Number: "
					+cc.getIdNum()+". ID Type: ";
			switch(cc.getIdType()) {
			case 1:
				msg+="IC";
				break;
			case 2:
				msg+="CC";
				break;
			case 3:
				msg+="FC";
				break;
			case 4:
				msg+="P";
				break;
			}

			msg+=". Name: "+cc.getFirstName()+" "+cc.getSurName()
					+". Address: "+cc.getAddress()+"\n";
		}
		return msg;
	}
	/**
	 * generates a String that says whether or not a client was found and how long it took
	 * @param cfn a String containing the first name of the client to be sought
	 * @return a String that says whether or not a client was found and how long it took
	 */
	public String seekClient(String cfn) {
		String msg="";
		ArrayList<Client> clientsOrderedByFirstName = (ArrayList<Client>) clients;
		FirstNameComparator fnc = new FirstNameComparator();
		Collections.sort(clientsOrderedByFirstName,fnc);
		long start=System.currentTimeMillis();
		
		boolean found = false;
		int strt = 0;
		int fnsh = clientsOrderedByFirstName.size() - 1;
		while (strt <= fnsh && !found) {
			int mid = (strt + fnsh) / 2;
			if (clientsOrderedByFirstName.get(mid).getFirstName().equals(cfn)) {
				found = true;
			} else if (clientsOrderedByFirstName.get(mid).getFirstName().compareTo(cfn) > 0) {
				fnsh = mid - 1;
			} else {
				strt = mid + 1;
			}
		}		
		long end= System.currentTimeMillis();
		long duration=end-start;
		if(found)
		{
			msg="Found! it took "+duration+" milliseconds";
		}
		else {
			msg="Not found! it took "+duration+" milliseconds";
		}
		return msg;
	}
	/**
	 * imports , if possible, restaurant information from a .csv file
	 * @param fn a String, the name of the file to import
	 * @throws FileNotFoundException if the file to import cannot be found
	 * @throws IOException  if it cannot write the file properly while saving after importing the restaurants
	 */
	public void importRestaurants(String fn) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(fn));
		String line=br.readLine();
		do{
			String[] parts = line.split(IMPORT_SEPARATOR);
		    registerRestaurant(parts[0],parts[1],parts[2]);
		    line = br.readLine();   
		}
		while(line!=null);		
		br.close();
		saveRestaurants();
	}
	/**
	 * imports , if possible, client information from a .csv file
	 * @param fn a String, the name of the file to import
	 * @throws FileNotFoundException if the file to import cannot be found
	 * @throws IOException  if it cannot write the file properly while saving after importing the clients
	 */
	public void importClients(String fn)throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(fn));
		String line=br.readLine(); 
		do{
			String[] parts = line.split(IMPORT_SEPARATOR);
		    registerClient(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]));
		    line = br.readLine();   
		}
		while(line!=null);		
		br.close();
		saveClients();
	}
	/**
	 * imports , if possible, product information from a .csv file
	 * @param fn a String, the name of the file to import
	 * @throws FileNotFoundException if the file to import cannot be found
	 * @throws IOException  if it cannot write the file properly while saving after importing the products
	 * @throws RestaurantDoesNotExistException if there is not a restaurant registered with the imported code 
	 */
	public void importProducts(String fn)throws FileNotFoundException, IOException, RestaurantDoesNotExistException {
		BufferedReader br = new BufferedReader(new FileReader(fn));
		String line=br.readLine();
		do{
			String[] parts = line.split(IMPORT_SEPARATOR);
			otherCheckNit(parts[4]);
		    registerProduct(parts[0],parts[1],parts[2],Double.parseDouble(parts[3]),parts[4]);
		    line = br.readLine();   
		}
		while(line!=null);		
		br.close();
		saveProducts();
	}

	/**
	 * imports , if possible, order information from a .csv file
	 * @param fn a String, the name of the file to import
	 * @throws FileNotFoundException if the file to import cannot be found
	 * @throws IOException if it cannot write the file properly while saving after importing the orders
	 * @throws OrderAlreadyExistsException if an order with the code of an order being imported already exists
	 */
	public void importOrders(String fn) throws FileNotFoundException, IOException, OrderAlreadyExistsException {
		/*
		 * 0<OrderCode>|
		 * 1<OrderState>|
		 * 2<RestaurantNit>|
		 * 3<RestaurantAdminName>|
		 * 4<RestaurantName>|
		 * 5<ClientIDNumber>|
		 * 6<ClientIDType>|
		 * 7<ClientAddress>|
		 * 8<ClientFirstName>|
		 * 9<Client Surname>|
		 * 10<ClientPhone>|
		 * 11<DateOfOrder>|
		 * 12<ProductCode>|
		 * 13<ProductQuantity>|
		 * 14<ProductCost>|
		 * 15<ProductName>|
		 * 16<ProductDescription>");
		 */
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fn));
		List<String> codes = new ArrayList<String>();
		List<Integer> quantities = new ArrayList<Integer>();
		String line=br.readLine();
		String lastOrderCode="";
		String lastOrderIdn="";
		String lastOrderNitRes="";
		Date lastOrderDate=null;
		DateFormat df=new SimpleDateFormat(INPUT_DATE_FORMAT);
		do{
			
				String[] parts = line.split(IMPORT_SEPARATOR);
				String currentOrderCode=parts[0];
				if(checkOrdCode(parts[0]))
				{
					throw new OrderAlreadyExistsException();
				}
				if(!checkNit(parts[2]))
				{
					//String name, String nit, String adminName
					registerRestaurant(parts[4],parts[2],parts[3]);
				}
				if(!checkProdCode(parts[12]))
				{
					//String name, String code, String description, double cost, String restNit
					registerProduct(parts[15],parts[12],parts[16],Double.parseDouble(parts[14]),parts[2]);
				}
				if(!checkId(parts[5]))
				{
					//String idn, String fn,String sn, String p, String a, int idt
					registerClient(parts[5],parts[8],parts[9],parts[10],parts[7],Integer.parseInt(parts[6]));
				}
				
				
				if(!(currentOrderCode.equals(lastOrderCode))&&!(lastOrderCode.equals("")))
				{
					try {
						registerOrder(lastOrderCode,lastOrderIdn,lastOrderNitRes,codes,quantities,lastOrderDate);
					} 
					catch (ProductsAreNotOfTheSameRestaurantException e) {
						System.err.println("Some of the products were not of the same Restaurant, aborting order");
						e.printStackTrace();
					} 
					codes.clear();
					quantities.clear();
				}
					lastOrderCode=currentOrderCode;
					lastOrderIdn=parts[5];
					lastOrderNitRes=parts[2];
					try {
						lastOrderDate=df.parse(parts[11]);
					} catch (ParseException e) {
						System.err.println("The date could not be read correctly, setting current date and time as default");
						lastOrderDate=new Date();
					}
					codes.add(parts[12]);
					quantities.add(Integer.parseInt(parts[13]));
					line = br.readLine();
			}while(line!=null);	
		
		br.close();
		saveOrders();	
	}
	/**
	 * checks if a restaurant exists with a given NIT in order to register a restaurant with that NIT
	 * @param nit a String, the NIT to be checked
	 * @throws RestaurantAlreadyExistsException if it exists already
	 */
	public void resCheckNit(String nit) throws RestaurantAlreadyExistsException {
		if(checkNit(nit))
			throw new RestaurantAlreadyExistsException();
	}
	/**
	 * checks if a product exists with a given code in order to register a product with that code
	 * @param c a String, the code to be checked
	 * @throws ProductAlreadyExistsException if it exists already
	 */
	public void prodCheckProdCode(String c) throws ProductAlreadyExistsException {
		if(checkProdCode(c))
			throw new ProductAlreadyExistsException();
	}
	/**
	 * checks if a restaurant exists with the NIT provided, in order to use that restaurant somewhere else 
	 * @param nit a String, not null, the NIT to be checked
	 * @throws RestaurantDoesNotExistException  if there is not a restaurant registered with that NIT
	 */
	public void otherCheckNit(String nit) throws RestaurantDoesNotExistException {
		if(!checkNit(nit))
		throw new RestaurantDoesNotExistException();
	}
	/**
	 * checks if a product exists with the code provided, in order to use that product somewhere else
	 * @param c a String, not null, the code to be checked
	 * @throws ProductDoesNotExistException if there is not a product registered with that code
	 */
	public void otherCheckProdCode(String c) throws ProductDoesNotExistException {
		if(!checkProdCode(c))
		throw new ProductDoesNotExistException();
	}
	/**
	 * checks if a client exists with the ID number provided, in order to register a new
	 * client with that ID number  
	 * @param idn a String, not null, the ID number to be checked
	 * @throws ClientAlreadyExistsException if there is a client registered with that ID number 
	 */
	public void clientCheckId(String idn) throws ClientAlreadyExistsException {
		if(checkId(idn))
		throw new ClientAlreadyExistsException();
	}
	/**
	 * checks if a client exists with the ID number provided, in order to use that client somewhere else
	 * @param idn a String, not null, the ID number to be checked
	 * @throws ClientDoesNotExistException if there is not a client registered with that ID number
	 */
	public void otherCheckId(String idn) throws ClientDoesNotExistException {
		if(!checkId(idn))
		throw new ClientDoesNotExistException();
	}
	/**
	 * checks if an order exists with the code provided, in order to use that Order somewhere else
	 * @param code a String, not null, the code of the order to check
	 * @throws OrderDoesNotExistException if there is not an order with that code registered
	 */
	public void otherCheckOrdCode(String code) throws OrderDoesNotExistException {
		if(!checkOrdCode(code))
		throw new OrderDoesNotExistException();
	}

}
