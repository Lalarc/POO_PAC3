package edu.uoc.pac3.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import edu.uoc.pac3.Animal;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class AnimalTest {

	Animal animal;
	
	@BeforeAll
	void testAnimal() {
		try {
			animal = new Animal();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testAnimal failed");
		}
	}

	@Test
	@Order(1)
	void testGetId() {
		assertEquals(0,animal.getId());
	}

	@Test
	@Order(2)
	void testGetNextId() {
		assertEquals(1,Animal.getNextId());
	}

	@Test
	@Order(3)
	void testGetName() {
		assertEquals("Foo",animal.getName());		
	}

	@Test
	@Order(4)
	void testSetName() {
		animal.setName("Bar");
		assertEquals("Bar",animal.getName());
		
	}
	
	@Test
	@Order(5)
	void testGetWeight() {
		assertEquals(0.1,animal.getWeight());		
	}
	
	@Test
	@Order(6)
	void testSetWeight() {
		Exception ex = assertThrows(Exception.class, () -> animal.setWeight(0));
		assertEquals("[ERROR] Animal's weight cannot be smaller than 0.1 kg.",ex.getMessage());
		
		ex = assertThrows(Exception.class, () -> animal.setWeight(-10));
		assertEquals("[ERROR] Animal's weight cannot be smaller than 0.1 kg.",ex.getMessage());
		
		try{
			animal.setWeight(123.4);
			assertEquals(123.4,animal.getWeight());
		}catch(Exception e) {
			fail("testLastReparation failed");
		}		
	}
	
	@Test
	@Order(7)
	void testGetSleeping() {
		assertFalse(animal.isSleeping());		
	}
	
	@Test
	@Order(8)
	void testSetSleeping() {
		animal.setSleeping(true);
		assertTrue(animal.isSleeping());
	}
	
	@Test
	@Order(9)
	void tesGetFarm() {
		animal.getFarm();
		assertNull(animal.getFarm());
	}
	
}
