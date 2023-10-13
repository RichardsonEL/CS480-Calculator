/* Eland Richardson
 * Lab 2
 * CS 480
 * 10/13/2023
 * Calculator Operand Class
 */

package main;

public class Operand implements Token{

	private double value;
	
	public Operand(String s) {
		try {
			this.value = Double.valueOf(s);
		}
		catch(Exception e) {
			System.out.println("ERROR: Number " + s + " is not formatted correctly. Please try again.");
			System.exit(0);
		}
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double x) {
		this.value = x;
	}
	
	public boolean isOperator() {
		return false;
	}

	public String getType() {
		return null;
	}

	public int getPrecidence() {
		return 0;
	}

	public double operate(double x) {
		return 0;
	}

	public double operate(double x, double y) {
		return 0;
	}
	
	public String toString() {
		return ("" + this.value);
	}
}
