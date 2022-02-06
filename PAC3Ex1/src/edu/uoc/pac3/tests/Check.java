package edu.uoc.pac3.tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import edu.uoc.pac3.Farm;
import edu.uoc.pac3.Farmer;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class Check {
		
	Farm farm;
	Farmer farmer;
	
	@BeforeAll
	void init() {
		 try{
			 farmer = new Farmer();
			 farm = new Farm("Farm 1", "Rambla Poblenou, 156", 10, farmer);
		 }catch(Exception e) {
				fail("Parameterized constructor failed");
		}
	}
	
	@Test
	@Order(1)
	void testFarmDefault() {
		try{
			Farm f = new Farm();
			assertEquals(1,f.getId());
			assertEquals("Default Farm",f.getName());
			assertEquals("Default Street",f.getStreet());
			assertEquals(30,f.getCapacity());
			assertNull(f.getFarmer());
		}catch(Exception e) {
			fail("Default constructor failed");
		}		
	}

	@Test
	@Order(2)
	void testFarm() {		
		Exception ex = assertThrows(Exception.class, () ->new Farm("Lorem ipsum dolor sit amet, consectetur vestibulum.", "Rambla Poblenou, 156", 10, new Farmer())); 
		assertEquals("[ERROR] Farm's name cannot be longer than 50 characters", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> new Farm("Farm 1", "Rambla Poblenou, 156", 0, new Farmer()));
		assertEquals("[ERROR] Farm's capacity must be greater than 0", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("12345678"));
		assertEquals("[ERROR] Farmer's phone number does not have the correct length", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("a2"));
		assertEquals("[ERROR] Farmer's phone number does not have the correct length", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("12345678a"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("hjs4yeg3h"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
	}

	@Test
	@Order(3)
	void testGetId() {
		assertEquals(0,farm.getId());		
	}

	@Test
	@Order(4)
	void testGetNextId() {
		assertEquals(2,Farm.getNextId());
	}

	@Test
	@Order(5)
	void testGetName() {
		assertEquals("Farm 1",farm.getName());		
	}

	@Test
	@Order(6)
	void testSetName() {
		try{
			farm.setName("Farm other");
			assertEquals("Farm other",farm.getName());
		}catch(Exception e) {
			fail("setName failed");
		}
		
		
		Exception ex = assertThrows(Exception.class, () ->	farm.setName("Lorem ipsum dolor sit amet, consectetur vestibulum."));
		assertEquals("[ERROR] Farm's name cannot be longer than 50 characters", ex.getMessage());
	}
	
	

	@Test
	@Order(7)
	void testGetStreet() {
		assertEquals("Rambla Poblenou, 156",farm.getStreet());	
	}

	@Test
	@Order(8)
	void testSetStreet() {
		farm.setStreet("Plaza Europa");
		assertEquals("Plaza Europa",farm.getStreet());
	}

	@Test
	@Order(9)
	void testGetFarmer() {
		assertEquals("Foo",farm.getFarmer().getName());
		assertEquals("000000000",farm.getFarmer().getPhoneNumber());
	}


	@Test
	@Order(10)
	void testSetFarmer() {
		
		try{
			farmer = new Farmer("Fran", "111222333");
			farm.setFarmer(farmer);
			assertEquals("Fran",farm.getFarmer().getName());
			assertEquals("111222333",farm.getFarmer().getPhoneNumber());
		}catch(Exception e) {
			fail("setLatitude failed");
		}
	}
	
	
	@Test
	@Order(11)
	void testSetFarmerPhoneNmuber() {
		try{
			farm.getFarmer().setPhoneNumber("605111111");
			assertEquals("605111111",farm.getFarmer().getPhoneNumber());
		}catch(Exception e) {
			fail("setName failed");
		}
		
		
		Exception ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("12345678"));
		assertEquals("[ERROR] Farmer's phone number does not have the correct length", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("a2"));
		assertEquals("[ERROR] Farmer's phone number does not have the correct length", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("12345678a"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("hjs4yeg3h"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> farm.getFarmer().setPhoneNumber("-12345678"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
	}


	@Test
	@Order(12)
	void testGetCapacity() {
		assertEquals(10,farm.getCapacity());
	}

	@Test
	@Order(13)
	void testSetCapacity() {
		try{
			farm.setCapacity(15);
			assertEquals(15,farm.getCapacity());
		}catch(Exception e) {
			fail("setCapacity failed");
		}
		
		Exception ex = assertThrows(Exception.class, () ->	farm.setCapacity(0));
		assertEquals("[ERROR] Farm's capacity must be greater than 0", ex.getMessage());
			
		
		ex = assertThrows(Exception.class, () -> farm.setCapacity(-10));
		assertEquals("[ERROR] Farm's capacity must be greater than 0", ex.getMessage());		
	}
	
	@Test
	@Order(14)
	void testSetSameFarmerToAnotherFarm() throws Exception {
		
		Farmer farmer2 = new Farmer("Fran", "111222333");
		Farm farm2 = new Farm("Farm 2", "C/barcelona", 10, farmer2);
		//the farmer (from the farm) and the farmer2 (from the farm2) have the same attributes values but they are different objects (different Object ID)
		assertNotEquals(farm.getFarmer(), farm2.getFarmer());
		
		farm2.setFarmer(farmer);
		//now farm and farm2 have the same farmer object (same Object ID)
		assertEquals(farm.getFarmer(), farm2.getFarmer());
	}
	
}