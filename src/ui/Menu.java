package ui;

import java.util.Scanner;
import model.Controller;

public class Menu {

	//constants
	public static final int EXIT_OPTION=3;
	
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
		menu+="Type the option you want";
		menu+=
				""
		;
		return menu;
	}
	public void decisionSwitch(int dec) {
		switch(dec) {
		case 1:
			//addCar();
		break;
		case 2:
			//showCars();
		break;
		case 3:
			//exitProgram();
		break;
		default:
		break;
		}
	}
}