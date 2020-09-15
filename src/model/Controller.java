package model;

import java.util.List;

import exceptions.restaurantExistsException;

import java.util.ArrayList;
import model.Client;
import model.Restaurant;
import model.Order;
import model.Product;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Controller {

	// constants
	public static final String CLIENTS_FILE_NAME = "data/clients.cli";
	public static final String RESTAURANTS_FILE_NAME = "data/restaurants.res";
	public static final String ORDERS_FILE_NAME = "data/orders.ord";
	public static final String PRODUCTS_FILE_NAME = "data/products.pro";

	// attributes

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

	
	private void loadRestaurants() throws IOException, ClassNotFoundException {
		File f = new File(RESTAURANTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      restaurants = (ArrayList<Restaurant>)ois.readObject();
	      ois.close();
	}
	private void loadClients() throws IOException, ClassNotFoundException {
		File f = new File(CLIENTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      clients = (ArrayList<Client>)ois.readObject();
	      ois.close();
	}
	private void loadProducts() throws IOException, ClassNotFoundException {
		File f = new File(PRODUCTS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      products = (ArrayList<Product>)ois.readObject();
	      ois.close();
	}
	private void loadOrders() throws IOException, ClassNotFoundException {
		File f = new File(ORDERS_FILE_NAME);
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      orders = (ArrayList<Order>)ois.readObject();
	      ois.close();
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


	public void registerRestaurant(String name, String nit, String adminName) {
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
	}


	public void checkNit(String nit) throws restaurantExistsException {
		for(int i=0; i<restaurants.size();i++)
		{
			if(restaurants.get(i).getNit().equals(nit))
			{
				throw new restaurantExistsException();
			}
		}
		
	}

}
