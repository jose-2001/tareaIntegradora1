package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	private Restaurant myRestaurant;
	
	public void setupScenario1() {
			
		}
	public void setupScenario2() {
		myRestaurant= new Restaurant("Burguers","1233124587","Eduardo Mejia");
	}
	@Test
	void testRestaurant() {
		setupScenario1();
		myRestaurant= new Restaurant("Burguers","1233124587","Eduardo Mejia");
		assertNotNull(myRestaurant);
		assertEquals("Burguers",myRestaurant.getName());
		assertEquals("1233124587",myRestaurant.getNit());
		assertEquals("Eduardo Mejia",myRestaurant.getAdminName());
		}
	@Test
	void testSetNit() {
		setupScenario2();
		assertEquals("1233124587",myRestaurant.getNit());
		myRestaurant.setNit("newRestNit");
		assertEquals("newRestNit",myRestaurant.getNit());
	}
}
