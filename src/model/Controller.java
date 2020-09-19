package model;
import java.util.List;

import exceptions.OrderDoesNotExistException;
import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.RestaurantExistsException;
import exceptions.ProductsAreNotOfTheSameRestaurantException;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

	// relations

	private List<Restaurant> restaurants;
	private List<Client> clients;
	private List<Product> products;
	private List<Order> orders;

	// methods

	/**
	 * @param restaurants
	 * @param clients
	 * @param products
	 * @param orders
	 */
	public Controller() {

		restaurants = new ArrayList<Restaurant>();
		File fr = new File(RESTAURANTS_FILE_NAME);

		if (fr.exists()) {
			try {
				loadRestaurants();
			} catch (IOException ioe) {
				System.err.println("Restaurant data could not be loaded properly");
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
			}
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("The class was not found");
			}
		}
	}

	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void loadRestaurants() throws IOException, ClassNotFoundException {
		File f = new File(RESTAURANTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      restaurants = (ArrayList<Restaurant>)ois.readObject();
	      ois.close();
	}
	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void loadClients() throws IOException, ClassNotFoundException {
		File f = new File(CLIENTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      clients = (ArrayList<Client>)ois.readObject();
	      ois.close();
	}
	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void loadProducts() throws IOException, ClassNotFoundException {
		File f = new File(PRODUCTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      products = (ArrayList<Product>)ois.readObject();
	      ois.close();
	}
	/**
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void loadOrders() throws IOException, ClassNotFoundException {
		File f = new File(ORDERS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      orders = (ArrayList<Order>)ois.readObject();
	      ois.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
	private void saveRestaurants() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESTAURANTS_FILE_NAME));
	    oos.writeObject(restaurants);
	    oos.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
	private void saveClients() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE_NAME));
	    oos.writeObject(clients);
	    oos.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
	private void saveOrders() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE_NAME));
	    oos.writeObject(orders);
	    oos.close();
	}
	/**
	 * 
	 * @throws FileNotFoundException 
	 * @throws IOException
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
	 * 
	 * @param name
	 * @param nit
	 * @param adminName
	 * @throws FileNotFoundException, IOException 
	 */
	public void registerRestaurant(String name, String nit, String adminName) throws FileNotFoundException, IOException {
		Restaurant toAdd = new Restaurant(name, nit, adminName);
		if(restaurants.isEmpty()) {
			restaurants.add(toAdd);
		}
		else {
			int i =0;
			while(toAdd.getName().compareTo(restaurants.get(i).getName())>0)
			{
				i++;
			}
			((ArrayList<Restaurant>)restaurants).add(i,toAdd);
		}
		saveRestaurants();
	}

	/**
	 * 
	 * @param nit
	 * @throws RestaurantExistsException
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
	 * 
	 * @param c
	 * @return
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
	 * 
	 * @param name
	 * @param code
	 * @param description
	 * @param cost
	 * @param restNit
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void registerProduct(String name, String code, String description, double cost, String restNit) throws FileNotFoundException, IOException {
		Product toAdd = new Product(code, name, description, cost,restNit);
		if(products.isEmpty()) {
			products.add(toAdd);
		}
		else {
			int i =0;
			while(toAdd.getCode().compareTo(products.get(i).getCode())>0)
			{
				i++;
			}
			((ArrayList<Product>)products).add(i,toAdd);
		}
		saveProducts();
	}

	/**
	 * 
	 * @param idn
	 * @return
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
	 * 
	 * @param idn
	 * @param fn
	 * @param sn
	 * @param p
	 * @param a
	 * @param idt
	 * @throws IOException
	 */
	public void registerClient(String idn, String fn,String sn, String p, String a, int idt) throws IOException {
		Client toAdd = new Client(idn, fn,sn, p, a, idt);
		if(clients.isEmpty()) {
			clients.add(toAdd);
		}
		else {
			int i =0;
			while(toAdd.getSurName().compareTo(clients.get(i).getSurName())>0)
			{
				i++;
			}
			while(toAdd.getFirstName().compareTo(clients.get(i).getFirstName())>0)
			{
				i++;
			}
			((ArrayList<Client>)clients).add(i,toAdd);
		}
		saveClients();
	}
	/**
	 * 
	 * @param nitRes
	 * @return
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
	 * 
	 * @param restNit
	 * @param codeOfProd
	 * @throws ProductDoesNotBelongToRestaurant
	 * @throws ProductDoesNotExistException
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
	 * 
	 * @param code
	 * @param idn
	 * @param nitRes
	 * @param codes
	 * @param quantities
	 * @param date
	 * @throws ProductsAreNotOfTheSameRestaurantException
	 * @throws FileNotFoundException
	 * @throws IOException
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
	 * 
	 * @param nitRes
	 * @param prodsOfOrder
	 * @throws productsAreNotOfTheSameRestaurantException 
	 */
	public void checkProdsOfSameRes(String nitRes, ArrayList<Product> prodsOfOrder) throws ProductsAreNotOfTheSameRestaurantException {
		for (int i = 0; i < prodsOfOrder.size(); i++) {
			if(!(prodsOfOrder.get(i).getRestNit().equals(nitRes)))
			{
				throw new ProductsAreNotOfTheSameRestaurantException();
			}
		}
		
	}
	/**
	 * 
	 * @return
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
	 * 
	 * @param restNit
	 * @param newAdminName
	 * @throws IOException
	 */
	public void updateRestaurantAdminName(String restNit, String newAdminName) throws IOException {
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				restaurants.get(i).setAdminName(newAdminName);
			}
		}
		saveRestaurants();
	}
	/**
	 * 
	 * @param restNit
	 * @param newRestName
	 * @throws IOException
	 */
	public void updateRestaurantName(String restNit, String newRestName) throws IOException {
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				restaurants.get(i).setName(newRestName);
			}
		}
		saveRestaurants();
	}
	/**
	 * 
	 * @param restNit
	 * @param newRestNit
	 * @throws IOException
	 */
	public void updateRestaurantNit(String restNit, String newRestNit) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getRestNit().equals(restNit))
			{
				orders.get(i).setRestNit(newRestNit);
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestNit().equals(restNit))
			{
				products.get(i).setRestNit(newRestNit);
			}
		}
		for (int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equals(restNit))
			{
				restaurants.get(i).setNit(newRestNit);
			}
		}
		saveOrders();
		saveProducts();
		saveRestaurants();
	}
	/**
	 * 
	 * @param code
	 * @param newCode
	 * @throws IOException
	 */
	public void updateProductCode(String code, String newCode) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.size(); j++) {
				if (orders.get(i).getProducts().get(j).getCode().equals(code)) {
					orders.get(i).getProducts().get(j).setCode(newCode);
				}
			}
		}
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				products.get(i).setCode(newCode);
			}
		}
		saveOrders();
		saveProducts();
	}
	/**
	 * 
	 * @param code
	 * @param newCost
	 * @throws IOException
	 */
	public void updateProductCost(String code, double newCost) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				products.get(i).setCost(newCost);
			}
		}
		saveProducts();
	}
	/**
	 * 
	 * @param code
	 * @param newDesc
	 * @throws IOException
	 */
	public void updateProductDescription(String code, String newDesc) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				products.get(i).setDescription(newDesc);
			}
		}
		saveProducts();
	}
	/**
	 * 
	 * @param code
	 * @param newName
	 * @throws IOException 
	 */
	public void updateProductName(String code, String newName) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				products.get(i).setName(newName);
			}
		}
		saveProducts();
	}
	/**
	 * 
	 * @param code
	 * @param newRestNit
	 * @throws IOException 
	 */
	public void updateProductRestNit(String code, String newRestNit) throws IOException {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code))
			{
				products.get(i).setRestNit(newRestNit);
			}
		}
		saveProducts();
	}

	/**
	 * 
	 * @param idn
	 * @param newClientAddress
	 * @throws IOException
	 */
	public void updateClientAdress(String idn, String newClientAddress)throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setAddress(newClientAddress);
			}
		}
		saveClients();
	}

	public void updateClientFirstName(String idn, String newClientFirstName) throws IOException {
		for (int i = 0; i < orders.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setFirstName(newClientFirstName);
			}
		}
		saveClients();
	}

	public void updateClientSurname(String idn, String newClientSurname) throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setSurName(newClientSurname);
			}
		}
		saveClients();
	}

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

	public void updateClientIdType(String idn, int idt)  throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setIdType(idt);
			}
		}
		saveClients();
	}

	public void updateClientPhone(String idn, String newClientPhone) throws IOException {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getIdNum().equals(idn))
			{
				clients.get(i).setPhone(newClientPhone);
			}
		}
		saveClients();
	}

	public void checkOrdCode(String code) throws OrderDoesNotExistException {
		boolean found=false;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCode().equals(code))
			{
				found=true;
			}
		}
		if(!found)
		{
			throw new OrderDoesNotExistException();
		}
	}

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
	 * 
	 * @param code
	 * @return
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
	 * 
	 * @param code
	 * @param codes
	 * @param quantities
	 * @param nitRes
	 * @throws ProductsAreNotOfTheSameRestaurantException
	 * @throws IOException
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
	 * 
	 * @param code
	 * @param newRestNit
	 * @throws IOException 
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

	public void updateOrderState(String code, int dec, int stateInt) {
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
	}
}
