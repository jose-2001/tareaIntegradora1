package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import exceptions.ClientDoesNotExistException;
import exceptions.ClientExistsException;
import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductExistsException;
import exceptions.RestaurantDoesNotExistException;
import exceptions.RestaurantExistsException;
import model.Controller;

public class Menu {

	//constants
	public static final int EXIT_OPTION=16;
	
	//attributes
	
	private Scanner sc;
	private Controller control;
	
	//methods
	
	public Menu() {
		sc = new Scanner(System.in);
		control= new Controller();
	}
	public void startMenu() {
		String msg = getMenuText();
		int dec;
		do {
			System.out.print(msg);
			dec = Integer.parseInt(sc.nextLine());
			decisionSwitch(dec);
		}while(dec!=EXIT_OPTION);
	}
	public String getMenuText() {
		String menu="";
		menu+="==============\n"
				+"     MENU\n"
				+"==============\n";
		menu+="Type the option you want\n";
		menu+=
				"1. Register a restaurant\n"
				+"2. Register a product\n"
				+"3. Register a client\n"
				+"4. Register an order\n"
				+"5. Update a restaurant's information\n"
				+"6. Update a client's information\n"
				+"7. Update an order's information\n"
				+"8. Generate a report of the orders\n"
				+"9. Show a list of the restaurants\n"
				+"10. Show a list of the clients\n"
				+"11. Look for a client\n"
				+"12. Import restaurants\n"
				+"13. Import clients\n"
				+"14. Import products\n"
				+"15. Import orders\n"
				+"16. Exit\n\n"
		;
		return menu;
	}
	public void decisionSwitch(int dec) {
		switch(dec) {
		case 1:
			registerRestaurant();
		break;
		case 2:
			registerProduct();
		break;
		case 3:
			registerClient();
		break;
		case 4:
			registerOrder();
		break;
		case 5:
			//updateRestaurant();
		break;
		case 6:
			//updateClient();
		break;
		case 7:
			//updateOrder();
		break;
		case 8:
			//reportOrders();
		break;
		case 9:
			//showRestaurants();
		break;
		case 10:
			//showClients();
		break;
		case 11:
			//seekClient();
		break;
		case 12:
			//importRestaurants();
		break;
		case 13:
			//importClients();
		break;
		case 14:
			//importProducts();
		break;
		case 15:
			//importOrders();
		break;
		case 16:
			//exit();
		break;
		default:
			System.out.println("Please enter a valid option");
		break;
		}
	}
	public void registerRestaurant() {
		System.out.println("Type in the new restaurant's name");
		String name = sc.nextLine();
		System.out.println("Type in the new restaurant's NIT");
		String nit = sc.nextLine();
		try {
			resCheckNit(nit);
			System.out.println("Type in the new restaurant's administrator's name");
			String adminName = sc.nextLine();	
				control.registerRestaurant(name, nit,adminName);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file where the Restaurant data is to be saved could not be found");
		}
		catch(RestaurantExistsException e)
		{
			System.err.println("There is a restaurant already registered with that NIT");
		}
		catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
	}
		
	/**
	 * this method checks if a restaurant exists with the NIT provided, in order to add a new restaurant with that NIT
	 * @param nit the NIT to be checked
	 * @throws RestaurantExistsException if there is a restaurant registered with that NIT
	 */
	public void resCheckNit(String nit) throws RestaurantExistsException{
	if(control.checkNit(nit))
		{
		throw new RestaurantExistsException();
		}
	}
	public void registerProduct() {
		System.out.println("Type in the NIT of the restaurant offering this product");
		String rn = sc.nextLine();
		try {
			otherCheckNit(rn);
			System.out.println("Type in the new product's code");
			String cd = sc.nextLine();
			checkCode(cd);
			System.out.println("Type in the new product's name");
			String n = sc.nextLine();
			System.out.println("Type in the new product's description");
			String d = sc.nextLine();
			System.out.println("Type in the new product's cost");
			double ct = Double.parseDouble(sc.nextLine());
			control.registerProduct(n,cd,d,ct,rn);
			
		}
		catch(RestaurantDoesNotExistException e)
		{
			System.err.println("A restaurant with that NIT is not registered");
		}
		catch( ProductExistsException e)
		{
			System.err.println("There is a propduct already registered with that code");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file where the product data is to be saved could not be found");
		}
		catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}
		
	}
	/**
	 * checks if a restaurant exists with the NIT provided, in order to register a product to that restaurant
	 * @param nit the NIT to be checked
	 * @throws RestaurantExistsException if there is a restaurant registered with that NIT
	 */
	public void otherCheckNit(String nit) throws RestaurantDoesNotExistException{
		if(!control.checkNit(nit))
			{
			throw new RestaurantDoesNotExistException();
			}
		}
	/**
	 * checks if a product exists with the code provided, in order to register a new product with that code
	 * @param c the code to be checked
	 * @throws ProductExistsException if there is a product registered with that code
	 */
	public void checkCode(String c) throws ProductExistsException{
		if(control.checkProdCode(c))
		{
			throw new ProductExistsException();
		}
	}
	public void registerClient() {
		int idt;
		boolean valid=false;
		do{
			System.out.println("Type 1 if the new client's identifies with an Identity Card");
			System.out.println("Type 2 if the new client's identifies with a Citizenship Card");
			System.out.println("Type 3 if the new client's identifies with a Foreigner Card");
			System.out.println("Type 4 if the new client's identifies with a Passport");
			idt= Integer.parseInt(sc.nextLine());
			if(idt==1|idt==2|idt==3|idt==4)
			{
				valid=true;
			}
			else {
				System.out.println("Type in a valid option (1-4)");
			}
		}while(!valid);
		System.out.println("Type in the new client's ID number");
		String idn = sc.nextLine();
		try {
			clientCheckId(idn);
			System.out.println("Type in the new client's first name");
			String fn = sc.nextLine();
			System.out.println("Type in the new client's first surname");
			String sn = sc.nextLine();
			System.out.println("Type in the new client's phone number");
			String p = sc.nextLine();
			System.out.println("Type in the new client's address");
			String a = sc.nextLine();	
				control.registerClient(idn, fn,sn, p, a, idt);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file where the Client data is to be saved could not be found");
		}
		catch(ClientExistsException e)
		{
			System.err.println("There is a client already registered with that ID number");
		}
		catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	public void clientCheckId(String idn) throws ClientExistsException {
		boolean found=control.checkId(idn); 
		if(found)
		{
			throw new ClientExistsException();
		}
	}
	public void registerOrder() {
		System.out.println("Type in the ID number of the client making the new order");
		String idn = sc.nextLine();
		String nitRes="";
		try {
			orderCheckId(idn);
			System.out.println("Type in the NIT of the restaurant to which this order is being made");
			nitRes = sc.nextLine();
			otherCheckNit(nitRes);
		}
		catch(ClientDoesNotExistException e)
		{
			System.err.println("A client with that ID number is not registered");
		}
		catch(RestaurantDoesNotExistException e)
		{
			System.err.println("A restaurant with that NIT is not registered");
		}
		boolean contin=true;
		List<String> codes = new ArrayList<String>();
		List<Integer> quantities = new ArrayList<Integer>();
		while(contin)
		{
			System.out.println("Type in the code of a product to order");
			showProductsFromRestaurant(nitRes);
			String codeOfProdToAdd =sc.nextLine();
			try {
				checkProdToAdd(nitRes,codeOfProdToAdd);
				codes.add(codeOfProdToAdd);
				System.out.println("Type in how many/much of this product is to be ordered");
				int quant = Integer.parseInt(sc.nextLine());
				quantities.add(quant);
			}
			catch(ProductDoesNotBelongToRestaurant e)
			{
				System.err.println("This product does not belong to the restaurant to which the order is being made");
			}
			catch(ProductDoesNotExistException e)
			{
				System.err.println("A product with this code is not registered");
			}
		}
	}
	public void orderCheckId(String idn)throws ClientDoesNotExistException {
		boolean found=control.checkId(idn); 
		if(!found)
		{
			throw new ClientDoesNotExistException();
		}
	}
	public void showProductsFromRestaurant(String nitRes) {
		System.out.println(control.showProductsFromRestaurant(nitRes));
		
	}
	public void checkProdToAdd(String restNit, String codeOfProd) throws ProductDoesNotBelongToRestaurant, ProductDoesNotExistException{
		control.checkProdToAdd( restNit, codeOfProd);
	}
}