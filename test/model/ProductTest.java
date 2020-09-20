package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	private Product myProduct;
	
	public void setupScenario1() {
		
	}
	public void setupScenario2() {
		myProduct= new Product("productCode","productName","productDescription", 2.5,"productRestNit");
	}
	@Test
	void testProduct() {
		myProduct= new Product("productCode","productName","productDescription", 2.5,"productRestNit");
		assertNotNull(myProduct);
		assertEquals("productCode",myProduct.getCode());
		assertEquals("productName",myProduct.getName());
		assertEquals("productDescription",myProduct.getDescription());
		assertEquals(2.5,myProduct.getCost());
		assertEquals("productRestNit",myProduct.getRestNit());
	}
	@Test
	void testSetRestNit() {
		setupScenario2();
		assertEquals("productRestNit",myProduct.getRestNit());
		myProduct.setRestNit("newRestNit");
		assertEquals("newRestNit",myProduct.getRestNit());
	}
}
