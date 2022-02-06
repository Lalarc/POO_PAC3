package edu.uoc.pac3.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import edu.uoc.pac3.Animal;
import edu.uoc.pac3.Farm;
import edu.uoc.pac3.Farmer;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class AdvancedCheck {
	Farm farm1, farm2;
	Animal animal1, animal2;
	
	@BeforeAll
	void init(){
		try {
			farm1 = new Farm();
			farm2 = new Farm("Farm 2", "Street 2", 15, new Farmer("Fran", "111222333"));			
			animal1 = new Animal();
			animal2 = new Animal("Bar", 55);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Init failed");
		}
	}

	@Test
	@Order(1)
	void testIntegration1() {
		try {
			farm1.addAnimal(animal1);		
			assertEquals(0,farm1.getGapByAnimal(animal1));
			assertEquals(29,farm1.getNumFreeGaps());
			assertEquals(1,farm1.getFirstFreeGap());
			assertEquals(animal1,farm1.getAnimals()[farm1.getGapByAnimal(animal1)]);
			assertEquals(farm1,animal1.getFarm());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration1 failed");
		}
	}
	
	@Test
	@Order(2)
	void testIntegration2()  {		
		try{
			animal1.setFarm(farm1);
			Exception ex = assertThrows( Exception.class,() -> farm1.addAnimal(animal1));
			assertEquals("[ERROR] This animal is already in this farm", ex.getMessage());			
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration2 failed");
		}		
	}
	
	
	@Test
	@Order(3)
	void testIntegration3() {
		try {
			animal1.setFarm(null);
			assertTrue(farm1.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration3 failed");
		}		
	}
	
	@Test
	@Order(4)
	void testIntegration4() {	
		try {
			animal1.setFarm(farm1);
			assertEquals(0,farm1.getGapByAnimal(animal1));
			assertEquals(29,farm1.getNumFreeGaps());
			assertEquals(1,farm1.getFirstFreeGap());
			assertEquals(animal1,farm1.getAnimals()[farm1.getGapByAnimal(animal1)]);
			assertEquals(farm1,animal1.getFarm());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration4 failed");
		}		
	}
	
	
	@Test
	@Order(5)
	void testIntegration5() {		
		try {
			farm1.removeAnimal(animal1);
			assertTrue(farm1.isEmpty());
			assertEquals(null,animal1.getFarm());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration5 failed");
		}		
	}
	
	
	
	@Test
	@Order(6)
	void testIntegration6() {		
		try {
			farm1.addAnimal(animal2);
			assertEquals(-1,farm1.getGapByAnimal(animal1));
			assertEquals(0,farm1.getGapByAnimal(animal2));
			assertEquals(29,farm1.getNumFreeGaps());
			assertEquals(1,farm1.getFirstFreeGap());
			assertEquals(animal2,farm1.getAnimals()[farm1.getGapByAnimal(animal2)]);
			assertEquals(farm1,animal2.getFarm());
			assertEquals(null,animal1.getFarm());
			assertEquals(15,farm2.getNumFreeGaps());
			
			Exception ex = assertThrows( Exception.class,() -> farm1.addAnimal(null));
			assertEquals("[ERROR] The animal cannot be null", ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Integration6 failed");
		}		
	}
	
	
	
	@Test
	@Order(7)
	void testIntegration7() {
		try{
			animal1.setFarm(farm2);
			animal2.setFarm(farm2);
			assertEquals(0,farm2.getGapByAnimal(animal1));
			assertEquals(1,farm2.getGapByAnimal(animal2));
			assertEquals(30,farm1.getNumFreeGaps());
			assertEquals(13,farm2.getNumFreeGaps());
			assertEquals(0,farm1.getFirstFreeGap());
			assertTrue(farm1.isEmpty());
			assertFalse(farm2.isEmpty());
			assertEquals(2,farm2.getFirstFreeGap());
			assertEquals(animal1,farm2.getAnimals()[farm2.getGapByAnimal(animal1)]);
			assertEquals(animal2,farm2.getAnimals()[farm2.getGapByAnimal(animal2)]);
			assertEquals(farm2,animal1.getFarm());
			assertEquals(farm2,animal2.getFarm());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration7 failed");
		}	
	}
	
	
	@Test
	@Order(8)
	void testIntegration8() {
		try{
			farm2.removeAnimal(animal2);
			farm2.removeAnimal(animal1);
			assertNull(animal1.getFarm());
			assertNull(animal2.getFarm());
			assertTrue(farm1.isEmpty());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration8 failed");
		}	
	}
	
	@Test
	@Order(9)
	void testIntegration9() {
		try{
			for(int i = 0; i < farm2.getCapacity()-1; i++) {
				farm2.addAnimal(new Animal());
			}
			assertFalse(farm2.isEmpty());
			assertFalse(farm2.isFull());
			farm2.addAnimal(new Animal());
			assertFalse(farm2.isEmpty());
			assertTrue(farm2.isFull());
			Exception ex = assertThrows( Exception.class,() -> farm2.addAnimal(new Animal()));
			assertEquals("[ERROR] This farm is full", ex.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
			fail("Integration9 failed");
		}	
	}
	
}
