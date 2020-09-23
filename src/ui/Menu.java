package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import exceptions.ClientDoesNotExistException;
import exceptions.ClientAlreadyExistsException;
import exceptions.OrderAlreadyExistsException;
import exceptions.OrderDoesNotExistException;
import exceptions.ProductAlreadyExistsException;
import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductsAreNotOfTheSameRestaurantException;
import exceptions.RestaurantDoesNotExistException;
import exceptions.RestaurantAlreadyExistsException;
import model.Controller;

public class Menu {

	// constants
	public static final int EXIT_OPTION = 17;

	// attributes

	private Scanner sc;
	private Controller control;

	// methods

	/**
	 * creates an instance of the class Menu
	 */
	public Menu() {
		sc = new Scanner(System.in);
		control = new Controller();
	}
	/**
	 * starts the menu
	 */
	public void startMenu() {
		String msg = getMenuText();
		int dec;
		do {
			System.out.print(msg);
			dec = Integer.parseInt(sc.nextLine());
			decisionSwitch(dec);
		} while (dec != EXIT_OPTION);
	}
	/**
	 * generates the menu text
	 * @return menu text as a String
	 */
	public String getMenuText() {
		String menu = "";
		menu += "==============\n" + "     MENU\n" + "==============\n";
		menu += "Type the option you want\n";
		menu +=   "1. Register a restaurant\n"
				+ "2. Register a product\n"
				+ "3. Register a client\n"
				+ "4. Register an order\n"
				+ "5. Update a restaurant's information\n"
				+ "6. Update a product's information\n"
				+ "7. Update a client's information\n" 
				+ "8. Update an order's information\n"
				+ "9. Generate a report of the orders\n" 
				+ "10. Show a list of the restaurants\n"
				+ "11. Show a list of the clients\n" 
				+ "12. Look for a client\n" 
				+ "13. Import restaurants\n"
				+ "14. Import clients\n" 
				+ "15. Import products\n" 
				+ "16. Import orders\n" 
				+ "17. Exit\n\n";
		return menu;
	}
	/**
	 * calls the methods involving the menu options
	 * @param dec an int representing which menu option to open
	 */
	public void decisionSwitch(int dec) {
		switch (dec) {
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
			updateRestaurant();
			break;
		case 6:
			updateProduct();
			break;
		case 7:
			updateClient();
			break;
		case 8:
			updateOrder();
			break;
		case 9:
			reportOrders();
			break;
		case 10:
			showRestaurants();
			break;
		case 11:
			showClients();
			break;
		case 12:
			seekClient();
			break;
		case 13:
			importRestaurants();
			break;
		case 14:
			importClients();
			break;
		case 15:
			importProducts();
			break;
		case 16:
			importOrders();
			break;
		case 17:
			System.out.println("GoodBye!");
			break;
		default:
			System.out.println("Please enter a valid option");
			break;
		}
	}
	/**
	 * registers a new restaurant after asking for data and checking if it can be added
	 */
	public void registerRestaurant() {
		System.out.println("Type in the new restaurant's name");
		String name = sc.nextLine();
		System.out.println("Type in the new restaurant's NIT");
		String nit = sc.nextLine();
		try {
			resCheckNit(nit);
			System.out.println("Type in the new restaurant's administrator's name");
			String adminName = sc.nextLine();
			control.registerRestaurant(name, nit, adminName);
			System.out.println("Restaurant registered successfully");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the Restaurant data is to be saved could not be found");
		} catch (RestaurantAlreadyExistsException e) {
			System.err.println("There is a restaurant already registered with that NIT");
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
	}

	/**
	 * this method checks if a restaurant exists with the NIT provided, in order to
	 * add a new restaurant with that NIT
	 * 
	 * @param nit a String, not null, the NIT to be checked
	 * @throws RestaurantAlreadyExistsException if there is a restaurant registered with that NIT
	 */
	public void resCheckNit(String nit) throws RestaurantAlreadyExistsException {
		control.resCheckNit(nit);
	}
	/**
	 * registers a new product after asking for data and checking if it can be added
	 */
	public void registerProduct() {
		System.out.println("Type in the NIT of the restaurant offering this product");
		String rn = sc.nextLine();
		try {
			otherCheckNit(rn);
			System.out.println("Type in the new product's code");
			String cd = sc.nextLine();
			prodCheckProdCode(cd);
			System.out.println("Type in the new product's name");
			String n = sc.nextLine();
			System.out.println("Type in the new product's description");
			String d = sc.nextLine();
			System.out.println("Type in the new product's cost");
			double ct = Double.parseDouble(sc.nextLine());
			control.registerProduct(n, cd, d, ct, rn);
			System.out.println("Product registered successfully");
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		} catch (ProductAlreadyExistsException e) {
			System.err.println("There is a propduct already registered with that code");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the product data is to be saved could not be found");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}

	}

	/**
	 * checks if a restaurant exists with the NIT provided, in order to use that restaurant somewhere else 
	 * @param nit a String, not null, the NIT to be checked
	 * @throws RestaurantDoesNotExistException  if there is not a restaurant registered with that NIT
	 */
	public void otherCheckNit(String nit) throws RestaurantDoesNotExistException {
		control.otherCheckNit(nit);
	}

	/**
	 * checks if a product exists with the code provided, in order to register a new
	 * product with that code 
	 * @param c a String, not null, the code to be checked
	 * @throws ProductAlreadyExistsException if there is a product registered with that code
	 */
	public void prodCheckProdCode(String c) throws ProductAlreadyExistsException {
		control.prodCheckProdCode(c);
	}
	/**
	 * checks if a product exists with the code provided, in order to use that product somewhere else
	 * @param c a String, not null, the code to be checked
	 * @throws ProductDoesNotExistException if there is not a product registered with that code
	 */
	public void otherCheckProdCode(String c) throws ProductDoesNotExistException {
		control.otherCheckProdCode(c);

	}

	/**
	 * registers a new client after asking for data and checking if it can be added
	 */
	public void registerClient() {
		int idt;
		boolean valid = false;
		do {
			System.out.println("Type 1 if the new client identifies with an Identity Card");
			System.out.println("Type 2 if the new client identifies with a Citizenship Card");
			System.out.println("Type 3 if the new client identifies with a Foreigner Card");
			System.out.println("Type 4 if the new client identifies with a Passport");
			idt = Integer.parseInt(sc.nextLine());
			if (idt == 1 | idt == 2 | idt == 3 | idt == 4) {
				valid = true;
			} else {
				System.out.println("Type in a valid option (1-4)");
			}
		} while (!valid);
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
			control.registerClient(idn, fn, sn, p, a, idt);
			System.out.println("Client registered successfully");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the Client data is to be saved could not be found");
		} catch (ClientAlreadyExistsException e) {
			System.err.println("There is a client already registered with that ID number");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	/**
	 * checks if a client exists with the ID number provided, in order to register a new
	 * client with that ID number  
	 * @param idn a String, not null, the ID number to be checked
	 * @throws ClientAlreadyExistsException if there is a client registered with that ID number 
	 */
	public void clientCheckId(String idn) throws ClientAlreadyExistsException {
		control.clientCheckId(idn);
		
	}
	/**
	 * registers a new order after asking for data and checking if it can be added
	 */
	public void registerOrder() {
		System.out.println("Type in the ID number of the client making the new order");
		String idn = sc.nextLine();
		String nitRes = "";
		try {
			otherCheckId(idn);
			System.out.println("Type in the NIT of the restaurant to which this order is being made");
			nitRes = sc.nextLine();
			otherCheckNit(nitRes);
			boolean contin = true;
			List<String> codes = new ArrayList<String>();
			List<Integer> quantities = new ArrayList<Integer>();
			while (contin) {
				System.out.println("Type in the code of a product to order");
				showProductsFromRestaurant(nitRes);
				String codeOfProdToAdd = sc.nextLine();
				checkProdToAdd(nitRes, codeOfProdToAdd);
				
				boolean contin2 = false;
				do {
					System.out.println("Type in how many of this product is to be ordered (integer greater than 0)");
					int quant = Integer.parseInt(sc.nextLine());
					if (quant > 0) {
						contin2 = true;
						if (addIfSameProd(codeOfProdToAdd, codes, quantities, quant)) {
						} else {
							codes.add(codeOfProdToAdd);quantities.add(quant);
						}
					} else {
						System.out.println("Please type in a valid amount (integer greater than 0)");
					}
				} while (!contin2);
				System.out.println("Is there another product in this order that is to be added?");
				System.out.println("Type 1 if yes");
				System.out.println("Type 2 if no");
				int dec = Integer.parseInt(sc.nextLine());
				switch (dec) {
				case 1:
					break;
				case 2:
					contin = false;
					break;
				default:
					System.out.println("Type a valid option");
					break;
				}
			}
			Date date = new Date();
			String code = getAlphaNumericString(10);
			control.registerOrder(code, idn, nitRes, codes, quantities, date);
			System.out.println("Order registered successfully");
		} catch (ClientDoesNotExistException e) {
			System.err.println("A client with that ID number is not registered");
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		} catch (ProductDoesNotBelongToRestaurant e) {
			System.err.println("This product does not belong to the restaurant to which the order is being made");
		} catch (ProductDoesNotExistException e) {
			System.err.println("A product with this code is not registered");
		} catch (ProductsAreNotOfTheSameRestaurantException e) {
			System.err.println("One of the products of this order does not belong to the restaurant to which the order is being made");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the Order data is to be saved could not be found");
		} catch (IOException e) {
			System.err.println("Order data could not be saved properly");
		}
	}
	/**
	 * checks if a client exists with the ID number provided, in order to use that client somewhere else
	 * @param idn a String, not null, the ID number to be checked
	 * @throws ClientDoesNotExistException if there is not a client registered with that ID number
	 */
	public void otherCheckId(String idn) throws ClientDoesNotExistException {
		control.otherCheckId(idn);
		
	}
	/**
	 * shows a list of products from the restaurant with the NIT given
	 * @param nitRes a String, not null, the NIT of the restaurant that will display its products; a restaurant with this NIT must be registered
	 */
	public void showProductsFromRestaurant(String nitRes) {
		System.out.println(control.showProductsFromRestaurant(nitRes));

	}
	/**
	 * checks if the Product to be added belongs to the restaurant the order is being made to
	 * @param restNit a String, not null, the NIT of the restaurant the order is being made to
	 * @param codeOfProd a String, not null, the code of the product to be checked 
	 * @throws ProductDoesNotBelongToRestaurant if the product does not belong to the same restaurant the order is being made to
	 * @throws ProductDoesNotExistException if there is not a product registered with that code
	 */
	public void checkProdToAdd(String restNit, String codeOfProd)
			throws ProductDoesNotBelongToRestaurant, ProductDoesNotExistException {
		control.checkProdToAdd(restNit, codeOfProd);
	}
	/**
	 * checks if the code of the product to add is already in the products of the order, if so, adds to its first appearance's quantities, instead of registering the same product twice
	 * @param codeToCheck a String, not null, the code of the product to check 
	 * @param codes an ArrayList of String, not null, the codes of the products already in the order
	 * @param quantities an ArrayList of ints, not null, the quantities of the products already in the order
	 * @param quant an int, positive
	 * @return true if the product was already in the order, false if it was not.
	 */
	public boolean addIfSameProd(String codeToCheck, List<String> codes, List<Integer> quantities, int quant) {
		boolean result = false;
		for (int i = 0; i < codes.size(); i++) {
			if (codeToCheck.equals(codes.get(i))) {
				int newQ = quantities.get(i) + quant;
				quantities.set(i, newQ);
				result = true;
			}
		}
		return result;
	}

	/**
	 * Adapted from geeksforgeeks, generates an alphanumeric random String used as Order code
	 * @param n the number of characters of the code generated
	 * @return a random alphanumeric string of 10 characters that has not been used as an order code before
	 */
	public String getAlphaNumericString(int n) 
		{  
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
										+ "0123456789"
										+ "abcdefghijklmnopqrstuvxyz"; 
			StringBuilder sb = new StringBuilder(n); 
			for (int i = 0; i < n; i++) { 
				int index= (int)(AlphaNumericString.length() * Math.random()); 
				sb.append(AlphaNumericString.charAt(index)); 
			} 
			String result =sb.toString();
			if(codeIsRepeated(result))
			{
				result=getAlphaNumericString(n);
			}
			return result; 
		} 
	/**
	 * checks if the order code provided has been used as an order code in the orders registered
	 * @param code a String, not null the code to be checked
	 * @return true if it has been used, false if it has not
	 */
	public boolean codeIsRepeated(String code) {
		boolean result= false;
		result=control.checkIfCodeIsRepeated(code);
		return result;
	}
	/**
	 * updates, if possible, the information of a restaurant, asks which restaurant, what information and its new values
	 */
	public void updateRestaurant() {
		System.out.println("Type in the NIT of the restaurant of which you wish to update data");
		String restNit = sc.nextLine();
		try {
			otherCheckNit(restNit);
			boolean contin = true;
			while (contin) {
				boolean contin2 = true;
				do {
					contin2 = true;
					System.out.println("What do you wish to update?");
					System.out.println("Type 1 if you wish to update the restaurant's administrator's name");
					System.out.println("Type 2 if you wish to update the restaurant's name");
					System.out.println("Type 3 if you wish to update the restaurant's nit");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						updateRestaurantAdminName(restNit);
						break;
					case 2:
						updateRestaurantName(restNit);
						break;
					case 3:
						updateRestaurantNit(restNit);
						break;
					default:
						System.out.println("Type in a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
				do {
					contin2 = true;
					System.out.println("Do you wish to update more information about this restaurant?");
					System.out.println("Type 1 if yes");
					System.out.println("Type 2 if no");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						contin = true;
						break;
					case 2:
						contin = false;
						break;
					default:
						System.out.println("Type a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
			}
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		}
	}
	/**
	 * updates a restaurant's administrator name
	 * @param restNit a String, not null the NIT of the restaurant to update
	 */
	public void updateRestaurantAdminName(String restNit) {
		System.out.println("Type in the name of the new Administrator");
		String newAdminName = sc.nextLine();
		try {
			control.updateRestaurantAdminName(restNit, newAdminName);
			System.out.println("Administrator name updated successfully");
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
	}
	/**
	 * updates a restaurant's name
	 * @param restNit a String, not null the NIT of the restaurant to update
	 */
	public void updateRestaurantName(String restNit) {
		System.out.println("Type in the new name of the restaurant");
		String newRestName = sc.nextLine();
		try {
			control.updateRestaurantName(restNit, newRestName);
			System.out.println("Restaurant name updated successfully");
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
	}
	/**
	 * updates a restaurant's NIT
	 * @param restNit a String, not null the NIT of the restaurant to update
	 */
	public void updateRestaurantNit(String restNit) {
		System.out.println("Type in the new NIT of the restaurant");
		String newRestNit = sc.nextLine();
		try {
			control.updateRestaurantNit(restNit, newRestNit);
			System.out.println("Restaurant Nit updated successfully");
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
	}
	/**
	 * updates, if possible, the information of a product, asks which product, what information and its new values
	 */
	public void updateProduct() {
		System.out.println("Type in the code of the product of which you wish to update data");
		String code= sc.nextLine();
		try {
			otherCheckProdCode(code);
			boolean contin = true;
			while (contin) {
				boolean contin2 = true;
				do {
					contin2 = true;
					System.out.println("What do you wish to update?");
					System.out.println("Type 1 if you wish to update the product's code");
					System.out.println("Type 2 if you wish to update the products's cost");
					System.out.println("Type 3 if you wish to update the product's description");
					System.out.println("Type 4 if you wish to update the products's name");
					System.out.println("Type 5 if you wish to update the products's NIT of the restaurant that offers it");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						updateProductCode(code);
						break;
					case 2:
						updateProductCost(code);
						break;
					case 3:
						updateProductDescription(code);
						break;
					case 4:
						updateProductName(code);
						break;
					case 5:
						updateProductRestNit(code);
						break;
					default:
						System.out.println("Type in a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
				do {
					contin2 = true;
					System.out.println("Do you wish to update more information about this restaurant?");
					System.out.println("Type 1 if yes");
					System.out.println("Type 2 if no");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						contin = true;
						break;
					case 2:
						contin = false;
						break;
					default:
						System.out.println("Type a valid option");
						contin2 = false;
						break;
					}
				} while (contin2);
			}
		} 
		catch (ProductDoesNotExistException e)
		{
			System.err.println("A product with this code is not registered");
		}
	}
	/**
	 * updates a product's code 
	 * @param code a String, not null, the code of the product to update
	 */
	public void updateProductCode(String code) {
		System.out.println("Type in the new code of the product");
		String newCode = sc.nextLine();
		try {
			control.updateProductCode(code, newCode);
			System.out.println("Product code updated successfully");
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
	}

	/**
	 * updates a product's cost
	 * @param code a String, not null the code of the product to update
	 */
	public void updateProductCost(String code) {
		System.out.println("Type in the new cost of the product");
		double newCost= Double.parseDouble(sc.nextLine());
		try {
			control.updateProductCost(code, newCost);
			System.out.println("Product cost updated successfully");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}
	}
	/**
	 * updates a product's description
	 * @param code a String, not null the code of the product to update
	 */
	public void updateProductDescription(String code) {
		System.out.println("Type in the new description of the product");
		String newDesc= sc.nextLine();
		try {
			control.updateProductDescription(code, newDesc);
			System.out.println("Product description updated successfully");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}
	}
	/**
	 * updates a product's name
	 * @param code a String, not null the code of the product to update
	 */
	public void updateProductName(String code) {
		System.out.println("Type in the new name of the product");
		String newName= sc.nextLine();
		try {
			control.updateProductName(code, newName);
			System.out.println("Product name updated successfully");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}
	}
	/**
	 * updates a product's NIT of the restaurant offering it 
	 * @param code a String, not null the code of the product to update
	 */
	public void updateProductRestNit(String code)  {
		System.out.println("Type in the new NIT of the restaurant offering this product");
		String newRestNit= sc.nextLine();
		try {
			otherCheckNit(newRestNit);
			control.updateProductRestNit(code, newRestNit);
			System.out.println("NIT of restaurant offering the product updated successfully");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		}
	}
	/**
	 * updates, if possible, the information of a client, asks which client, what information and its new values
	 */
	public void updateClient() {
		System.out.println("Type in the ID number of the client whose information you wish to update");
		String idn = sc.nextLine();
		try {
			otherCheckId(idn);
			boolean contin = true;
			while (contin) {
				boolean contin2 = true;
				do {
					contin2 = true;
					System.out.println("What do you wish to update?");
					System.out.println("Type 1 if you wish to update the client's address");
					System.out.println("Type 2 if you wish to update the client's first name");
					System.out.println("Type 3 if you wish to update the client's first surname");
					System.out.println("Type 4 if you wish to update the client's ID number");
					System.out.println("Type 5 if you wish to update the client's ID type");
					System.out.println("Type 6 if you wish to update the client's phone number");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						updateClientAddress(idn);
						break;
					case 2:
						updateClientFirstName(idn);
						break;
					case 3:
						updateClientSurName(idn);
						break;
					case 4:
						updateClientIdn(idn);
						break;
					case 5:
						updateClientIdt(idn);
						break;
					case 6:
						updateClientPhone(idn);
						break;
					default:
						System.out.println("Type in a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
				do {
					contin2 = true;
					System.out.println("Do you wish to update more information about this Client?");
					System.out.println("Type 1 if yes");
					System.out.println("Type 2 if no");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						contin = true;
						break;
					case 2:
						contin = false;
						break;
					default:
						System.out.println("Type a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
			}
		} catch (ClientDoesNotExistException e) {
			System.err.println("A client with that identification number is not registered");
		}
	}
	/**
	 * updates a client's address 
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientAddress(String idn) {
		System.out.println("Type in the new address of the client");
		String newClientAddress= sc.nextLine();
		try {
			control.updateClientAdress(idn, newClientAddress);
			System.out.println("Client address updated successfully");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	/**
	 * updates a client's first name
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientFirstName(String idn) {
		System.out.println("Type in the new first name of the client");
		String newClientFirstName= sc.nextLine();
		try {
			control.updateClientFirstName(idn, newClientFirstName);
			System.out.println("Client first name updated successfully");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	/**
	 * updates a client's surname
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientSurName(String idn) {
		System.out.println("Type in the new first surname of the client");
		String newClientSurname= sc.nextLine();
		try {
			control.updateClientSurname(idn, newClientSurname);
			System.out.println("Client first surname updated successfully");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	/**
	 * updates a client's ID number
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientIdn(String idn) {
		System.out.println("Type in the new ID number of the client");
		String newClientIdNum= sc.nextLine();
		try {
			control.updateClientIdNum(idn, newClientIdNum);
			System.out.println("Client ID number updated successfully");
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
	}
	/**
	 * updates a client's ID Type
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientIdt(String idn) {
		int newIdt;
		boolean valid=false;
		do {
		System.out.println("Specify the new ID type of the client");
		System.out.println("Type 1 if the new client identifies with an Identity Card");
		System.out.println("Type 2 if the new client identifies with a Citizenship Card");
		System.out.println("Type 3 if the new client identifies with a Foreigner Card");
		System.out.println("Type 4 if the new client identifies with a Passport");
		newIdt = Integer.parseInt(sc.nextLine());
		if (newIdt == 1 | newIdt == 2 | newIdt == 3 | newIdt == 4) {
			valid = true;
		} else {
			System.out.println("Type in a valid option (1-4)");
		}
	} while (!valid);
		try {
			control.updateClientIdType(idn, newIdt);
			System.out.println("Client ID type updated successfully");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}
	/**
	 * updates a client's phone
	 * @param idn a String, not null the ID number of the client to update
	 */
	public void updateClientPhone(String idn) {
		System.out.println("Type in the new phone number of the client");
		String newClientPhone= sc.nextLine();
		try {
			control.updateClientPhone(idn, newClientPhone);
			System.out.println("Client ID number updated successfully");
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
	}
	/**
	 * updates, if possible, the information of a order, asks which order, what information and its new values
	 */
	public void updateOrder() {
		System.out.println("Type in the code of the order of which you wish to update data");
		String code= sc.nextLine();
		try {
			otherCheckOrdCode(code);
			boolean contin = true;
			while (contin) {
				boolean contin2 = true;
				do {
					contin2 = true;
					System.out.println("What do you wish to update?");
					System.out.println("Type 1 if you wish to update the ID of the client who made the order");
					System.out.println("Type 2 if you wish to update the order's products and quantities");
					System.out.println("Type 3 if you wish to update the NIT of the restaurant to which this order was made (You'll also ave to update products and quantities)");
					System.out.println("Type 4 if you wish to update the order's state");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						updateOrderClientID(code);
						break;
					case 2:
						String restNit= getRestNitOfOrder(code);
						updateOrderProductsAndQuantities(restNit, code);
						break;
					case 3:
						updateOrderRestNit(code);
						break;
					case 4:
						updateOrderState(code);
						break;
					default:
						System.out.println("Type in a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
				do {
					contin2 = true;
					System.out.println("Do you wish to update more information about this restaurant?");
					System.out.println("Type 1 if yes");
					System.out.println("Type 2 if no");
					int dec = Integer.parseInt(sc.nextLine());
					switch (dec) {
					case 1:
						contin = true;
						break;
					case 2:
						contin = false;
						break;
					default:
						System.out.println("Type a valid option");
						contin2 = false;
						break;
					}
				} while (!contin2);
			}
		} 
		catch (OrderDoesNotExistException e)
		{
			System.err.println("An order with this code is not registered");
		} catch (ProductsAreNotOfTheSameRestaurantException e) {
			System.err.println("One of the products of this order does not belong to the restaurant to which the order is being made");
		} 
	}
	/**
	 * checks if an order exists with the code provided, in order to use that Order somewhere else
	 * @param code a String, not null, the code of the order to check
	 * @throws OrderDoesNotExistException if there is not an order with that code registered
	 */
	public void otherCheckOrdCode(String code) throws OrderDoesNotExistException {
		control.otherCheckOrdCode(code);
			
	}
	/**
	 * updates an order's client's ID number
	 * @param code a String, not null, the code of the order to update
	 */
	public void updateOrderClientID(String code) {
		System.out.println("Type in the new ID of the client that made this order");
		String newOrderClientID= sc.nextLine();
		try {
			otherCheckId(newOrderClientID);
			control.updateOrderClientID(code, newOrderClientID);
			System.out.println("The ID Number of the client that made the order was updated successfully");
		} catch (IOException e) {
			System.err.println("Order data could not be saved properly");
		}catch (ClientDoesNotExistException e) {
			System.err.println("A client with that ID number is not registered");
		}
	}
	/**
	 * returns the NIT if the restaurant to which the order with the code provided was made
	 * @param code a String, not null, the code of the order to be checked, the order and its restaurant must have been registered already
	 * @return a String, the NIT of the restaurant to which the order was made
	 */
	public String getRestNitOfOrder(String code) {
		return control.getRestNitOfOrder(code);
	}
	/**
	 * updates an order's products and quantities
	 * @param nitRes a String, not null, the NIT of the restaurant to which the order was made
	 * @param code a String, not null, the code of the order to update
	 * @throws ProductsAreNotOfTheSameRestaurantException if the new products are not of the same Restaurant
	 */
	public void updateOrderProductsAndQuantities(String nitRes, String code) throws ProductsAreNotOfTheSameRestaurantException {
		System.out.println("Rewriting products and quantities of the order");
		List<String> codes = new ArrayList<String>();
		List<Integer> quantities = new ArrayList<Integer>();
		boolean contin=true;
		while (contin) {
			System.out.println("Type in the code of a product to order");
			showProductsFromRestaurant(nitRes);
			String codeOfProdToAdd = sc.nextLine();
			try {
				checkProdToAdd(nitRes, codeOfProdToAdd);
			} catch (ProductDoesNotBelongToRestaurant e) {
				System.err.println("This product does not belong to the restaurant to which the order is being made");
			} catch (ProductDoesNotExistException e) {
				System.err.println("A product with this code is not registered");
			}
			codes.add(codeOfProdToAdd);
			boolean contin2 = false;
			do {
				System.out.println("Type in how many of this product is to be ordered (integer greater than 0)");
				int quant = Integer.parseInt(sc.nextLine());
				if (quant > 0) {
					contin2 = true;
					if (addIfSameProd(codeOfProdToAdd, codes, quantities, quant)) {
					} else {
						quantities.add(quant);
					}
				} else {
					System.out.println("Please type in a valid amount (integer greater than 0)");
				}
			} while (contin2);
			System.out.println("Is there another product in this order that is to be added?");
			System.out.println("Type 1 if yes");
			System.out.println("Type 2 if no");
			int dec = Integer.parseInt(sc.nextLine());
			switch (dec) {
			case 1:
				break;
			case 2:
				contin = false;
				break;
			default:
				System.out.println("Type a valid option");
				break;
			}
	}try {
		control.updateOrderProductsAndQuantities(code, codes,quantities,nitRes);
		System.out.println("The products and quantities of the order were updated successfully");
	} catch (IOException e) {
		System.err.println("Order data could not be saved properly");
	}
}
	/**
	 * updates an order's NIT of the restaurant that orders it, if successful, updates the products and quantities of the order
	 * @param code a String, not null, the code of the order to update
	 */
	public void updateOrderRestNit(String code) {
		System.out.println("Type in the new NIT of the restaurant to which this order is being made");
		String newRestNit= sc.nextLine();
		boolean success=true;
		try {
			otherCheckNit(newRestNit);
			control.updateOrderRestNit(code, newRestNit);
			System.out.println("NIT of restaurant to which this order was made was updated; updating products and quantities");

		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
			success=false;
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
			success=false;
		}
		if (success) {
			boolean repeat = false;
			do {
				try {
					updateOrderProductsAndQuantities(newRestNit, code);
				} catch (ProductsAreNotOfTheSameRestaurantException e) {
					System.err.println("One of the products of this order does not belong to the restaurant to which the order is being made");
					repeat=true;
				}
			} while (repeat);
		}
	}

	/**
	 * updates an order's state
	 * @param code a String, not null, the code of the order to update
	 */
	public void updateOrderState(String code) {
		String followingStates= control.getFollowingStatesText(code);
		String[] states = followingStates.split("&");
		String currentState=states[1];
		int stateInt=0,dec=0;
		boolean contin=true;
		if(currentState.equals("SOLICITED"))
		{
			stateInt=1;
		}
		if(currentState.equals("IN_PROGRESS"))
		{
			stateInt=2;
		}
		if(currentState.equals("SENT"))
		{
			stateInt=3;
		}
		if(currentState.equals("DELIVERED"))
		{
			stateInt=4;
		}
		do {
			contin=true;
			System.out.println("To which state do you wish to update");
			System.out.println(states[0]);
			dec=Integer.parseInt(sc.nextLine());
			if(dec>3|dec<1)
			{
				System.out.println("Type a valid option");contin=false;
			}
			else {
				if(stateInt==2&dec>2)
				{
					System.out.println("Type a valid option");contin=false;
				}
				else {
					if(stateInt==3&dec!=1)
					{
						System.out.println("Type a valid option");contin=false;
					}
				}
			}
		}while(!contin);
		try {
			control.updateOrderState(code,dec,stateInt);
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}
		System.out.println("Order state updated successfully");
	}
	/**
	 * asks for the separator to use to separate information and exports a .csv file with the information of the orders.
	 */
	public void reportOrders() {
		System.out.println("Please specify the separator to be used");
		char separator = sc.nextLine().charAt(0);
		try {
			control.generateOrdersReport(separator);
		} catch (FileNotFoundException e) {
			System.err.println("order report could not be exported properly");
		}
	}
	/**
	 * shows the restaurants' information
	 */
	public void showRestaurants() {
		System.out.println(control.showRestaurants());
	}
	/**
	 * shows the clients' information
	 */
	public void showClients() {
		System.out.println(control.showClients());
	}
	/**
	 * shows how long it took to find (or not) a client given their ID number
	 */
	public void seekClient() {
		System.out.println("Type in the first name of the client you seek");
		String cfn= sc.nextLine();
		System.out.println(control.seekClient(cfn));
	}
	/**
	 * imports , if possible, restaurant information from a .csv file after asking its direction and name
	 */
	public void importRestaurants() {
		System.out.println("The file from which you wish to import must have the following format:");
		System.out.println("<RestaurantName>|<RestaurantNit>|<AdminName>");
		System.out.println("Please type the name of the file you wish to import Restaurants from");
		String fn=sc.nextLine();
		try {
			control.importRestaurants(fn);
			System.out.println("Data imported successfully");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file from which the restaurant info was to be imported or the file where the restaurant info was to be saved was not found");
			System.err.println(e.getStackTrace());
		}
		catch (IOException e) {
			System.err.println("Restaurant data could not be imported or saved properly");
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * imports , if possible, client information from a .csv file after asking its direction and name
	 */
	public void importClients() {
		System.out.println("The file from which you wish to import must have the following format:");
		System.out.println("<ClientIDNumber>|<ClientFirstName>|<ClientSurname>|<ClientPhone>|<ClientAddress>|<ClientIDType(number 1-4)>");
		System.out.println("Please type the name of the file you wish to import Clients from");
		String fn=sc.nextLine();
		try {
			control.importClients(fn);
			System.out.println("Data imported successfully");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file from which the client info was to be imported or the file where the client info was to be saved was not found");
			System.err.println(e.getStackTrace());
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
			System.err.println("Client data could not be imported or saved properly");
		}
	}
	/**
	 * imports , if possible, products information from a .csv file after asking its direction and name
	 */
	public void importProducts() {
		System.out.println("The file from which you wish to import must have the following format:");
		System.out.println("<ProductName>|<ProductCode>|<ProductDescription>|<ProductCost>|<ProductRestNit>");
		System.out.println("Please type the name of the file you wish to import products from");
		String fn=sc.nextLine();
		try {
			control.importProducts(fn);
			System.out.println("Data imported successfully");
		}
		catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with this NIT is not registered");
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file from which the product info was to be imported or the file where the client info was to be saved was not found");
			System.err.println(e.getStackTrace());
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
			System.err.println("Product data could not be imported or saved properly");
		}
	}
	/**
	 * imports , if possible, order information from a .csv file after asking its direction and name
	 */
	public void importOrders() {
		System.out.println("The file from which you wish to import must have the following format:");
		System.out.println("<OrderCode>|<OrderState>|<RestaurantNit>|<RestaurantAdminName>|<RestaurantName>|<ClientIDNumber>|<ClientIDType>|<ClientAddress>|<ClientFirstName>|<Client Surname>|<ClientPhone>|<DateOfOrder>|<ProductCode>|<ProductQuantity>|<ProductCost>|<ProductName>|<ProductDescription>");
		System.out.println("Please type the name of the file you wish to import orders from");
		String fn=sc.nextLine();
		try {
			control.importOrders(fn);
			System.out.println("Data imported successfully");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("The file from which the order info was to be imported or the file where the order info was to be saved was not found");
			System.err.println(e.getStackTrace());
		}
		catch (IOException e) {
			System.err.println(e.getStackTrace());
			System.err.println("Order data could not be imported or saved properly");
		}  catch (OrderAlreadyExistsException e) {
			System.err.println("An order with this code is already registered");
			e.printStackTrace();
		}
	}

	
}