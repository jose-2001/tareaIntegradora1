package ui;

import java.util.Scanner;

import exceptions.restaurantExistsException;
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
			//registerProduct();
		break;
		case 3:
			//registerClient();
		break;
		case 4:
			//registerOrder();
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
			checkNit(nit);
			System.out.println("Type in the new restaurant's administrator's name");
			String adminName = sc.nextLine();
			control.registerRestaurant(name, nit,adminName);
		}
		catch(restaurantExistsException e)
		{
			System.err.println("There is a restaurant already registered with that NIT");
		}
		
	}
	private void checkNit(String nit) throws restaurantExistsException{
	control.checkNit(nit);	
	}
}