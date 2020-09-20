package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class ControllerTest {

	private Controller myController;

	public void setupScenario1()
	{
		
	}
	public void setupScenario2()
	{
		myController= new Controller();
	}
	
	@Test
	void testController() {
		myController = new Controller();
		assertNotNull(myController,"Controller should not be null");
		assertNotNull(myController.getRestaurants(),"restaurants should not be null");
		assertEquals(0,myController.getRestaurants().size(),"restaurants should be Empty");
		assertNotNull(myController.getClients(),"clients should not be null");
		assertEquals(0,myController.getClients().size(),"clients should be Empty");
		assertNotNull(myController.getOrders(),"orders should not be null");
		assertEquals(0,myController.getOrders().size(),"orders should be Empty");
		assertNotNull(myController.getProducts(),"products should not be null");
		assertEquals(0,myController.getProducts().size(),"products should be Empty");	
	}
	void testRegisterRestaurant() {
		setupScenario2();
		try {
			myController.registerRestaurant("Burguers","1233124587","Eduardo Mejia");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("File should be found or created");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Saving should work");
		}
		assertNotNull(myController.getRestaurants(),"restaurants should not be null");
		assertEquals(1,myController.getRestaurants().size(),"restaurants should have 1 Restaurant");
		assertEquals("Burguers",myController.getRestaurants().get(0).getName());
		assertEquals("1233124587",myController.getRestaurants().get(0).getNit());
		assertEquals("Eduardo Mejia",myController.getRestaurants().get(0).getAdminName());
		
	}
}
