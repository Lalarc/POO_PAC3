package edu.uoc.pac3.enums;

public enum Temperature {

	UNDEFINED(-1),
	LOW(0),
	MEDIUM(20),
	HIGH(30);
	
	private Temperature(int degrees) {
		//TODO
	}
	
	public int getDegrees() {
		//TODO
	}
	
	@Override
	public String toString() {
		//TODO
	}
	
	public static Temperature getTemperature(int degrees) { 
		//TODO
	}
	
	public static Temperature[] getTemperatures(int[] degrees) {
		//TODO
	}
	
	public static double[] valuesInitializationIn(String scale){
		//TODO
	}
}
