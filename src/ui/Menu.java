package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import exceptions.ClientDoesNotExistException;
import exceptions.ClientExistsException;
import exceptions.ProductDoesNotBelongToRestaurant;
import exceptions.ProductDoesNotExistException;
import exceptions.ProductExistsException;
import exceptions.ProductsAreNotOfTheSameRestaurantException;
import exceptions.RestaurantDoesNotExistException;
import exceptions.RestaurantExistsException;
import model.Controller;

public class Menu {

	// constants
	public static final int EXIT_OPTION = 17;

	// attributes

	private Scanner sc;
	private Controller control;

	// methods

	public Menu() {
		sc = new Scanner(System.in);
		control = new Controller();
	}

	public void startMenu() {
		String msg = getMenuText();
		int dec;
		do {
			System.out.print(msg);
			dec = Integer.parseInt(sc.nextLine());
			decisionSwitch(dec);
		} while (dec != EXIT_OPTION);
	}

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
			//updateProduct();
			break;
		case 7:
			updateClient();
			break;
		case 8:
			// updateOrder();
			break;
		case 9:
			// reportOrders();
			break;
		case 10:
			// showRestaurants();
			break;
		case 11:
			// showClients();
			break;
		case 12:
			// seekClient();
			break;
		case 13:
			// importRestaurants();
			break;
		case 14:
			// importClients();
			break;
		case 15:
			// importProducts();
			break;
		case 16:
			// importOrders();
			break;
		case 17:
			// exit();
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
			control.registerRestaurant(name, nit, adminName);
			System.out.println("Restaurant registered successfully");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the Restaurant data is to be saved could not be found");
		} catch (RestaurantExistsException e) {
			System.err.println("There is a restaurant already registered with that NIT");
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
	}

	/**
	 * this method checks if a restaurant exists with the NIT provided, in order to
	 * add a new restaurant with that NIT
	 * 
	 * @param nit the NIT to be checked
	 * @throws RestaurantExistsException if there is a restaurant registered with
	 *                                   that NIT
	 */
	public void resCheckNit(String nit) throws RestaurantExistsException {
		if (control.checkNit(nit)) {
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
			control.registerProduct(n, cd, d, ct, rn);
			System.out.println("Product registered successfully");
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		} catch (ProductExistsException e) {
			System.err.println("There is a propduct already registered with that code");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the product data is to be saved could not be found");
		} catch (IOException e) {
			System.err.println("Product data could not be saved properly");
		}

	}

	/**
	 * checks if a restaurant exists with the NIT provided, in order to register a
	 * product to that restaurant
	 * 
	 * @param nit the NIT to be checked
	 * @throws RestaurantExistsException if there is a restaurant registered with
	 *                                   that NIT
	 */
	public void otherCheckNit(String nit) throws RestaurantDoesNotExistException {
		if (!control.checkNit(nit)) {
			throw new RestaurantDoesNotExistException();
		}
	}

	/**
	 * checks if a product exists with the code provided, in order to register a new
	 * product with that code
	 * 
	 * @param c the code to be checked
	 * @throws ProductExistsException if there is a product registered with that
	 *                                code
	 */
	public void checkCode(String c) throws ProductExistsException {
		if (control.checkProdCode(c)) {
			throw new ProductExistsException();
		}
	}

	public void registerClient() {
		int idt;
		boolean valid = false;
		do {
			System.out.println("Type 1 if the new client's identifies with an Identity Card");
			System.out.println("Type 2 if the new client's identifies with a Citizenship Card");
			System.out.println("Type 3 if the new client's identifies with a Foreigner Card");
			System.out.println("Type 4 if the new client's identifies with a Passport");
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
		} catch (ClientExistsException e) {
			System.err.println("There is a client already registered with that ID number");
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
	}

	public void clientCheckId(String idn) throws ClientExistsException {
		boolean found = control.checkId(idn);
		if (found) {
			throw new ClientExistsException();
		}
	}

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
				Date date = new Date();
				String code = getAlphaNumericString(10);
				control.registerOrder(code, idn, nitRes, codes, quantities, date);
				System.out.println("Order registered successfully");
			}
		} catch (ClientDoesNotExistException e) {
			System.err.println("A client with that ID number is not registered");
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		} catch (ProductDoesNotBelongToRestaurant e) {
			System.err.println("This product does not belong to the restaurant to which the order is being made");
		} catch (ProductDoesNotExistException e) {
			System.err.println("A product with this code is not registered");
		} catch (ProductsAreNotOfTheSameRestaurantException e) {
			System.err.println(
					"One of the products of this order does not belong to the restaurant to which the order is being made");
		} catch (FileNotFoundException e) {
			System.err.println("The file where the Order data is to be saved could not be found");
		} catch (IOException e) {
			System.err.println("Order data could not be saved properly");
		}
	}

	public void otherCheckId(String idn) throws ClientDoesNotExistException {
		boolean found = control.checkId(idn);
		if (!found) {
			throw new ClientDoesNotExistException();
		}
	}

	public void showProductsFromRestaurant(String nitRes) {
		System.out.println(control.showProductsFromRestaurant(nitRes));

	}

	public void checkProdToAdd(String restNit, String codeOfProd)
			throws ProductDoesNotBelongToRestaurant, ProductDoesNotExistException {
		control.checkProdToAdd(restNit, codeOfProd);
	}

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
	 * Taken from geeksforgeeks, generates an alphanumeric random String
	 * @param n
	 * @return
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

	public boolean codeIsRepeated(String code) {
		boolean result= false;
		result=control.checkIfCodeIsRepeated(code);
		return result;
	}

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
				} while (contin2);
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
		} catch (RestaurantDoesNotExistException e) {
			System.err.println("A restaurant with that NIT is not registered");
		}
	}
	/**
	 * 
	 * @param restNit
	 */
	public void updateRestaurantAdminName(String restNit) {
		System.out.println("Type in the name of the new Administrator");
		String newAdminName = sc.nextLine();
		try {
			control.updateRestaurantAdminName(restNit, newAdminName);
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
		System.out.println("Administrator name updated successfully");
	}
	/**
	 * 
	 * @param restNit
	 */
	public void updateRestaurantName(String restNit) {
		System.out.println("Type in the new name of the restaurant");
		String newRestName = sc.nextLine();
		try {
			control.updateRestaurantName(restNit, newRestName);
		} catch (IOException e) {
			System.err.println("Restaurant data could not be saved properly");
		}
		System.out.println("Restaurant name updated successfully");
	}
	/**
	 * 
	 * @param restNit
	 */
	public void updateRestaurantNit(String restNit) {
		System.out.println("Type in the new NIT of the restaurant");
		String newRestNit = sc.nextLine();
		try {
			control.updateRestaurantNit(restNit, newRestNit);
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
		System.out.println("Restaurant Nit updated successfully");
	}

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
				} while (contin2);
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
				} while (contin2);
			}
		} catch (ClientDoesNotExistException e) {
			System.err.println("A client with that identification number is not registered");
		}
	}

	public void updateClientAddress(String idn) {
		System.out.println("Type in the new address of the client");
		String newClientAddress= sc.nextLine();
		try {
			control.updateClientAdress(idn, newClientAddress);
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
		System.out.println("Client address updated successfully");
	}

	public void updateClientFirstName(String idn) {
		System.out.println("Type in the new first name of the client");
		String newClientFirstName= sc.nextLine();
		try {
			control.updateClientFirstName(idn, newClientFirstName);
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
		System.out.println("Client first name updated successfully");
	}

	public void updateClientSurName(String idn) {
		System.out.println("Type in the new first surname of the client");
		String newClientSurname= sc.nextLine();
		try {
			control.updateClientSurname(idn, newClientSurname);
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
		System.out.println("Client first surname updated successfully");
	}

	public void updateClientIdn(String idn) {
		System.out.println("Type in the new ID number of the client");
		String newClientIdNum= sc.nextLine();
		try {
			control.updateClientIdNum(idn, newClientIdNum);
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
		System.out.println("Client ID number updated successfully");
	}

	public void updateClientIdt(String idn) {
		int idt;
		boolean valid=false;
		do {
		System.out.println("Specify the new ID type of the client");
		System.out.println("Type 1 if the new client's identifies with an Identity Card");
		System.out.println("Type 2 if the new client's identifies with a Citizenship Card");
		System.out.println("Type 3 if the new client's identifies with a Foreigner Card");
		System.out.println("Type 4 if the new client's identifies with a Passport");
		idt = Integer.parseInt(sc.nextLine());
		if (idt == 1 | idt == 2 | idt == 3 | idt == 4) {
			valid = true;
		} else {
			System.out.println("Type in a valid option (1-4)");
		}
	} while (!valid);
		try {
			control.updateClientIdType(idn, idt);
		} catch (IOException e) {
			System.err.println("Client data could not be saved properly");
		}
		System.out.println("Client ID type updated successfully");
	}

	public void updateClientPhone(String idn) {
		System.out.println("Type in the new phone number of the client");
		String newClientPhone= sc.nextLine();
		try {
			control.updateClientPhone(idn, newClientPhone);
		} catch (IOException e) {
			System.err.println("Data could not be saved properly");
		}
		System.out.println("Client ID number updated successfully");
	}

}