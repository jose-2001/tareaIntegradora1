package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTest {

	private Order myOrder;
	
	public void setupScenario1() {
		
	}
	public void setupScenario2() {
		Date testDate = new Date();
		List<Product> testProducts = new ArrayList<Product>();
		testProducts.add(null);
		List<Integer> testQuantities= new ArrayList<Integer>();
		testQuantities.add(null);
		myOrder= new Order("5s9dj7l5d7", testDate, "123456789", "134679852645",  testProducts,	testQuantities);
	}
	@Test
	void testOrder() {
		Date testDate = new Date();
		List<Product> testProducts = new ArrayList<Product>();
		testProducts.add(null);
		List<Integer> testQuantities= new ArrayList<Integer>();
		testQuantities.add(null);
		myOrder= new Order("5s9dj7l5d7", testDate, "123456789", "134679852645",  testProducts,	testQuantities);
		assertNotNull(myOrder);
		assertEquals("5s9dj7l5d7",myOrder.getCode());
		assertEquals(testDate,myOrder.getDateAndTime());
		assertEquals("123456789",myOrder.getClientID());
		assertEquals("134679852645",myOrder.getRestNit());
		assertEquals(testProducts,myOrder.getProducts());
		assertEquals(testQuantities,myOrder.getQuantities());
		assertEquals(Order.State.SOLICITED,myOrder.getState());
	}

	@Test
	void testSetRestNit() {
		setupScenario2();
		assertEquals("134679852645",myOrder.getRestNit());
		myOrder.setRestNit("newRestNit");
		assertEquals("newRestNit",myOrder.getRestNit());
	}
}
