package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {
	private Client myClient;

	public void setupScenario1()
	{
		
	}
	public void setupScenario2()
	{
		myClient= new Client("123456789","Juan","Perez","3124567","Grove Street",1);
	}
	@Test
	void testClient() {
		setupScenario1();
		myClient= new Client("123456789","Juan","Perez","3124567","Grove Street",1);
		assertNotNull(myClient,"Client should not be null");
		assertEquals("123456789",myClient.getIdNum());
		assertEquals("Juan",myClient.getFirstName());
		assertEquals("Perez",myClient.getSurName());
		assertEquals("3124567",myClient.getPhone());
		assertEquals("Grove Street",myClient.getAddress());
		assertEquals(1,myClient.getIdType());
	}
	@Test
	void testSetIdType() {
		setupScenario2();
		assertEquals(1,myClient.getIdType());
		myClient.setIdType(2);
		if(myClient.getIdType()==1)
			fail();
		assertEquals(2,myClient.getIdType());
	}
	
}
