package edu.uoc.pac3.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import edu.uoc.pac3.Animal;
import edu.uoc.pac3.Farm;
import edu.uoc.pac3.Farmer;


@TestInstance(Lifecycle.PER_CLASS)
class FarmTest {

	Farm farm;
	private final static int CAPACITY = 25;
			
	@BeforeEach
	void init() {
		 try{
			 farm = new Farm("Can VilaFarm", "Av. Vilanova s/n", CAPACITY, new Farmer());			 
		 }catch(Exception e) {
			e.printStackTrace();
			fail("Parameterized constructor failed");
		}
	}
	
	@Test
	void testFarmDefault() {
		try{
			Farm farmDefault = new Farm();
			assertEquals(2,farmDefault.getId());
			assertEquals("Default Farm",farmDefault.getName());
			assertEquals("Default Street",farmDefault.getStreet());
			assertEquals(CAPACITY,farm.getCapacity());
			assertNull(farmDefault.getFarmer());
			
		}catch(Exception e) {
			fail("Default constructor failed");
		}		
	}


	@Test
	void testGetId() {
		assertEquals(0,farm.getId());		
	}

	@Test
	void testGetNextId() {
		assertEquals(13,Farm.getNextId());
	}

	@Test
	void testGetName() {
		assertEquals("Can VilaFarm",farm.getName());		
	}

	@Test
	void testSetName() {
		try{
			farm.setName("Can VilaFarm 2");
			assertEquals("Can VilaFarm 2",farm.getName());
		}catch(Exception e) {
			fail("setName failed");
		}
		
		Exception ex = assertThrows(Exception.class, () ->	farm.setName("Lorem ipsum dolor sit amet, consectetur vestibulum."));
		assertEquals("[ERROR] Farm's name cannot be longer than 50 characters", ex.getMessage());
	}


	@Test
	void testGetStreet() {
		assertEquals("Av. Vilanova s/n", farm.getStreet());	
	}

	@Test
	void testSetStreet() {
		farm.setStreet("Av. vila");
		assertEquals("Av. vila",farm.getStreet());
	}
	
	@Test
	void testGetFarmer() {
		assertEquals("Foo",farm.getFarmer().getName());
		assertEquals("000000000",farm.getFarmer().getPhoneNumber());
	}
	
	@Test
	void testSetFarmer() {
		try{
			Farmer farmer = new Farmer("Fran", "111222333");
			farm.setFarmer(farmer);
			assertEquals("Fran",farm.getFarmer().getName());
			assertEquals("111222333",farm.getFarmer().getPhoneNumber());
		}catch(Exception e) {
			fail("setLatitude failed");
		}
		
		Exception ex = assertThrows(Exception.class, () ->	farm.getFarmer().setPhoneNumber("-23456789"));
		assertEquals("[ERROR] Farmer's phone number is not a numeric value", ex.getMessage());
	}


	@Test
	void testGetCapacity() {
		assertEquals(CAPACITY,farm.getCapacity());
	}

	@Test
	void testSetCapacity() {
		try{
			farm.setCapacity(15);
			assertEquals(15,farm.getCapacity());
		}catch(Exception e) {
			fail("setCapacity failed");
		}
		
		Exception ex = assertThrows(Exception.class, () ->	farm.setCapacity(0));
		assertEquals("[ERROR] Farm's capacity must be greater than 0", ex.getMessage());
			
		
		ex = assertThrows(Exception.class, () ->	farm.setCapacity(-10));
		assertEquals("[ERROR] Farm's capacity must be greater than 0", ex.getMessage());		
	}

	
	/******************
	 *   RELATIONS 
	 *****************/
	@Test
	void testGetAnimals() {
		assertArrayEquals(new Animal[CAPACITY],farm.getAnimals());
	}

	@Test
	void testGetFirstFreeGap() {
		assertEquals(0,farm.getFirstFreeGap());
	}

	@Test
	void testIsFull() {
		assertFalse(farm.isFull());
	}

	@Test
	void testIsEmpty() {
		assertTrue(farm.isEmpty());
	}

	@Test
	void testGetNumFreeGaps() {
		assertEquals(CAPACITY,farm.getNumFreeGaps());
	}

	@Test
	void testGetGapByAnimal() {
		Animal animal;
		try{
			animal = new Animal();
			assertEquals(-1,farm.getGapByAnimal(animal));
		}catch(Exception e) {
			fail("testGetGapByAnimal failed");
		}		
	}
	
}
