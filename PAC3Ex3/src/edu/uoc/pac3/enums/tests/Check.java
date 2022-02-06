package edu.uoc.pac3.enums.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.uoc.pac3.enums.Temperature;

class Check {

	private Temperature t;
	
	@Test
	void testGetDegrees() {
		t = Temperature.LOW;		
		assertEquals(0,t.getDegrees());
		
		t = Temperature.MEDIUM;		
		assertEquals(20,t.getDegrees());
		
		t = Temperature.HIGH;		
		assertEquals(30,t.getDegrees());
		
	}
	
	@Test
	void testToString() {
		t = Temperature.LOW;		
		assertEquals("Temperature: LOW",t.toString());
		
		t = Temperature.MEDIUM;		
		assertEquals("Temperature: MEDIUM",t.toString());
		
		t = Temperature.HIGH;		
		assertEquals("Temperature: HIGH",t.toString());
	}
	
	@Test
	void testGetTemperature() {
		
		assertEquals(Temperature.UNDEFINED,Temperature.getTemperature(-1));
		assertEquals(Temperature.UNDEFINED,Temperature.getTemperature(40));
		assertEquals(Temperature.UNDEFINED,Temperature.getTemperature(45));
		
		assertEquals(Temperature.LOW,Temperature.getTemperature(0));
		assertEquals(Temperature.LOW,Temperature.getTemperature(10));
		assertEquals(Temperature.LOW,Temperature.getTemperature(19));
		
		assertEquals(Temperature.MEDIUM,Temperature.getTemperature(20));
		assertEquals(Temperature.MEDIUM,Temperature.getTemperature(25));
		assertEquals(Temperature.MEDIUM,Temperature.getTemperature(29));
		
		assertEquals(Temperature.HIGH,Temperature.getTemperature(30));
		assertEquals(Temperature.HIGH,Temperature.getTemperature(35));
		assertEquals(Temperature.HIGH,Temperature.getTemperature(39));
		
		assertEquals(Temperature.UNDEFINED,Temperature.getTemperature(200));
		assertEquals(Temperature.UNDEFINED,Temperature.getTemperature(-200));
	}
	
	@Test
	void testGetTemperatures() {		
		assertArrayEquals(new Temperature [] {Temperature.LOW, Temperature.MEDIUM, Temperature.UNDEFINED, Temperature.HIGH}, Temperature.getTemperatures(new int[] {0,20,-1,30}));
		assertArrayEquals(new Temperature [] {Temperature.UNDEFINED, Temperature.LOW, Temperature.MEDIUM, Temperature.HIGH,  Temperature.UNDEFINED}, Temperature.getTemperatures(new int[] {-2,2,22,32,42}));
		assertArrayEquals(new Temperature [] {Temperature.UNDEFINED, Temperature.UNDEFINED, Temperature.UNDEFINED, Temperature.UNDEFINED,  Temperature.LOW,  Temperature.MEDIUM, Temperature.HIGH}, Temperature.getTemperatures(new int[] {-7,-1,90,40,19,29,39}));
	}
	
	@Test
	void testValuesInitializationIn() {		
		
		assertArrayEquals(new double [] {272.15, 273.15, 293.15, 303.15}, Temperature.valuesInitializationIn("k"));
		assertArrayEquals(new double [] {272.15, 273.15, 293.15, 303.15}, Temperature.valuesInitializationIn("K"));
		assertArrayEquals(new double [] {30.2, 32.0, 68.0, 86.0}, Temperature.valuesInitializationIn("f"));
		assertArrayEquals(new double [] {30.2, 32.0, 68.0, 86.0}, Temperature.valuesInitializationIn("F"));
		assertArrayEquals(new double [] {} , Temperature.valuesInitializationIn("fk"));
	}			
			
}
