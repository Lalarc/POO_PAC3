package edu.uoc.pac3.enums;

public enum Temperature {

	UNDEFINED(-1),
	LOW(0),
	MEDIUM(20),
	HIGH(30);
	
	private int degrees;
	
	private Temperature(int degrees) {
		this.degrees=degrees;
		
	}
	
	public int getDegrees() {
		return degrees;
	}
	
	@Override
	public String toString() {
		return "Temperature: "+super.toString();
	}
	
	public static Temperature getTemperature(int degrees) { 
		if(degrees<Temperature.LOW.getDegrees()||degrees>=Temperature.HIGH.getDegrees()+10) {
			return Temperature.UNDEFINED;
		} else if (degrees>=30) {
			return Temperature.HIGH;
		} else if (degrees>=20) {
			return Temperature.MEDIUM;
		} else {
			return Temperature.LOW;
		}
	}
	
	public static Temperature[] getTemperatures(int[] degrees) {
		Temperature[] temperature = new Temperature[degrees.length];
			for(int i=0;i<degrees.length;i++) {
				temperature[i]=getTemperature(degrees[i]);
			}
			return temperature;
	}
	
	public static double[] valuesInitializationIn(String scale){
		
		double[] array = new double [Temperature.values().length];
		int i = 0;
		
		if(scale.toUpperCase().equals("K")) {
			for (Temperature t : values()) {
				double d = t.getDegrees();
				array[i] = d + 273.15;
				i++;
			}
		}else if(scale.toUpperCase().equals("F")) {
			for (Temperature t : values()) {
				double d = t.getDegrees();
				array[i] =  (d * 9 / 5) + 32;
				i++;
			}
		}else {
			array = new double[0];
		}
		
		return array;
	}
}
