package model;
import java.util.List;

import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.RestaurantExistsException;
import java.util.ArrayList;
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
		throw new ProductDoesNotExistException();
	}

}
